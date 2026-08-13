# Java 8 — Interfaces: Default Methods, Static Methods, Diamond Problem & Functional Interfaces

**Topic focus (Day 3 style)**  
- Default methods in interfaces  
- Static methods in interfaces  
- Diamond problem with default methods  
- Functional interface preview (`@FunctionalInterface`)  

**Practice covered**  
- Interface with default logging helper + static factory-style helper  
- Resolve diamond conflict with override  

---

## 1. Why were default methods added in Java 8?

### Answer
Default methods were added so existing interfaces could gain **new methods without breaking** all implementing classes.

Before Java 8, adding a method to an interface forced every implementor to write that method (compile errors across libraries).  
With `default`, the interface provides a **ready implementation**. Classes can use it as-is or override it.

**Real example:** `List` got `sort(Comparator)` and `spliterator()` as default methods — millions of `List` implementations did not all need rewriting.

### Sample example

```java
interface PaymentService {
    void pay(double amount);

    // New capability without forcing every class to implement logging
    default void log(String message) {
        System.out.println("[PaymentService] " + message);
    }
}

class CardPayment implements PaymentService {
    @Override
    public void pay(double amount) {
        log("Paying " + amount + " by card"); // uses default log()
        // payment logic...
    }
}

public class DefaultMethodWhyDemo {
    public static void main(String[] args) {
        PaymentService service = new CardPayment();
        service.pay(1500.0);
    }
}
```

**Output idea:**
```text
[PaymentService] Paying 1500.0 by card
```

---

## 2. Can default methods be overridden?

### Answer
**Yes.** A class (or sub-interface) can override a default method and provide its own implementation.

- If not overridden → default body runs.  
- If overridden → class version runs (runtime polymorphism via interface reference).

### Sample example

```java
interface Notifier {
    default void notifyUser(String msg) {
        System.out.println("Default notify: " + msg);
    }
}

class EmailNotifier implements Notifier {
    @Override
    public void notifyUser(String msg) {
        System.out.println("Email sent: " + msg); // overridden
    }
}

class SmsNotifier implements Notifier {
    // no override → uses default
}

public class OverrideDefaultDemo {
    public static void main(String[] args) {
        Notifier email = new EmailNotifier();
        Notifier sms = new SmsNotifier();

        email.notifyUser("Loan approved"); // Email sent: Loan approved
        sms.notifyUser("OTP 1234");        // Default notify: OTP 1234
    }
}
```

---

## 3. Interface static method — how to call? Can it be overridden?

### Answer

**How to call**  
Call with the **interface name**, not via instance/implementing class polymorphism:

```text
InterfaceName.staticMethod(...)
```

**Can it be overridden?**  
**No.** Interface `static` methods are **not inherited for overriding**.  
They belong to the interface. A class may declare a method with the same signature, but that is **not overriding** (no polymorphic dispatch through the interface).

### Sample example

```java
interface AccountValidator {
    // static factory-style / utility helper
    static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches("[A-Za-z0-9]{10}");
    }

    static AccountValidator simple() {
        return accountNumber -> isValidAccountNumber(accountNumber);
    }

    boolean validate(String accountNumber);
}

public class StaticMethodDemo {
    public static void main(String[] args) {
        // Correct call: InterfaceName.method
        System.out.println(AccountValidator.isValidAccountNumber("ABCDE12345")); // true
        System.out.println(AccountValidator.isValidAccountNumber("123"));        // false

        AccountValidator validator = AccountValidator.simple();
        System.out.println(validator.validate("ABCDE12345")); // true

        // WRONG mental model:
        // validator.isValidAccountNumber("..."); // compile error — static not on instance
    }
}
```

**Interview one-liner:**  
`default` → instance-level overridable helper; `static` → interface-level utility/factory, called as `Interface.method()`, not overridable.

---

## 4. What happens if two interfaces have the same default method? (Diamond problem)

### Answer
If a class implements two interfaces that both declare the **same default method signature**, Java cannot choose automatically.

**Compile-time error** unless the class:
1. **Overrides** the method, and  
2. Optionally calls one parent with `InterfaceName.super.method(...)`.

This is the **diamond problem** with default methods.

### Sample example — conflict + resolution

```java
interface LoggerA {
    default void log(String msg) {
        System.out.println("A-LOG: " + msg);
    }
}

interface LoggerB {
    default void log(String msg) {
        System.out.println("B-LOG: " + msg);
    }
}

// WITHOUT override → compilation error:
// class BadService implements LoggerA, LoggerB {}

class AuditService implements LoggerA, LoggerB {
    @Override
    public void log(String msg) {
        // Resolve diamond: pick one, or combine both
        LoggerA.super.log(msg);
        LoggerB.super.log(msg);
        System.out.println("AuditService: " + msg);
    }
}

public class DiamondProblemDemo {
    public static void main(String[] args) {
        AuditService service = new AuditService();
        service.log("Lender search started");
    }
}
```

**Output:**
```text
A-LOG: Lender search started
B-LOG: Lender search started
AuditService: Lender search started
```

### Special cases (also asked)
- If only **one** interface has `default` and the other has the method as **abstract**, the class must implement it (or use the default if rules allow — typically implement/override).  
- If a **superclass** method conflicts with interface default, the **class method wins** (class preferred over interface default).

---

## 5. Functional interface preview (`@FunctionalInterface`)

### Answer
A **functional interface** has **exactly one abstract method** (SAM — Single Abstract Method).  
It may still have:
- multiple `default` methods  
- multiple `static` methods  

`@FunctionalInterface` is optional but recommended: compiler verifies the “only one abstract method” rule.

Lambdas and method references target functional interfaces.

### Sample example

```java
@FunctionalInterface
interface FeeCalculator {
    double calculate(double loanAmount); // only abstract method

    default void log(String msg) {
        System.out.println("[FeeCalculator] " + msg);
    }

    static FeeCalculator flatPercent(double percent) {
        return amount -> amount * percent / 100.0; // factory-style static helper
    }
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        FeeCalculator calc = FeeCalculator.flatPercent(2.5);
        double fee = calc.calculate(100000);
        calc.log("Fee = " + fee); // 2500.0
    }
}
```

If you add another abstract method, `@FunctionalInterface` causes a **compile error**.

---

## Practice lab (complete working-style example)

Combines:
- default logging helper  
- static factory-style helper  
- diamond resolution  
- `@FunctionalInterface` preview  

```java
package java8.interfaces;

@FunctionalInterface
interface SearchRule {
    boolean matches(String input); // single abstract method

    default void log(String message) {
        System.out.println("[SearchRule] " + message);
    }

    static SearchRule accountNumberRule() {
        return input -> input != null && input.matches("[A-Za-z0-9]{10}");
    }
}

interface TraceLogger {
    default void log(String message) {
        System.out.println("[TraceLogger] " + message);
    }
}

interface AuditLogger {
    default void log(String message) {
        System.out.println("[AuditLogger] " + message);
    }
}

/**
 * Implements two interfaces that both have default log(String)
 * → must override to resolve diamond conflict.
 */
class LenderSearchService implements TraceLogger, AuditLogger {
    private final SearchRule rule;

    LenderSearchService(SearchRule rule) {
        this.rule = rule;
    }

    // static factory-style helper on the class (common pattern)
    static LenderSearchService withAccountRule() {
        return new LenderSearchService(SearchRule.accountNumberRule());
    }

    @Override
    public void log(String message) {
        // Resolve diamond problem
        TraceLogger.super.log(message);
        AuditLogger.super.log(message);
    }

    public boolean search(String accountNumber) {
        log("Searching account: " + accountNumber);
        boolean ok = rule.matches(accountNumber);
        rule.log("Validation result = " + ok); // default method from SearchRule
        return ok;
    }
}

public class InterfacePracticeDemo {
    public static void main(String[] args) {
        // static factory on functional interface
        SearchRule rule = SearchRule.accountNumberRule();
        System.out.println("Static helper valid? " + rule.matches("ABCDE12345"));

        // service with diamond resolution + default logging
        LenderSearchService service = LenderSearchService.withAccountRule();
        System.out.println("Search OK? " + service.search("ABCDE12345"));
        System.out.println("Search OK? " + service.search("123"));
    }
}
```

**Expected console flow:**
```text
Static helper valid? true
[TraceLogger] Searching account: ABCDE12345
[AuditLogger] Searching account: ABCDE12345
[SearchRule] Validation result = true
Search OK? true
[TraceLogger] Searching account: 123
[AuditLogger] Searching account: 123
[SearchRule] Validation result = false
Search OK? false
```

---

## Quick Q&A cheat sheet

| Question | Short answer |
|----------|--------------|
| Why default methods? | Evolve interfaces without breaking implementors |
| Can default be overridden? | Yes |
| How to call interface static method? | `InterfaceName.method(...)` |
| Can interface static be overridden? | No |
| Two interfaces, same default method? | Class must override (diamond); use `A.super.m()` / `B.super.m()` |
| What is `@FunctionalInterface`? | Marks SAM interface; enables lambdas; compiler-checked |

---

## Remember for interviews (30-second version)

“In Java 8, interfaces can have `default` and `static` methods. Default methods let APIs evolve safely and can be overridden. Static methods are utilities/factories called on the interface name and are not overridable. If two interfaces provide the same default method, the implementing class must override it and may call `Interface.super.method()` — that’s the diamond resolution. A functional interface has one abstract method and can still have default/static helpers; lambdas target it.”

---

## Related files
- Day-by-day plan: [`java8-day-by-day.md`](./java8-day-by-day.md)  
- Full concept Q&A: [`java8-interview-qa.md`](./java8-interview-qa.md)  
- Hard challenges: [`java8-topics-questions-challenges.md`](./java8-topics-questions-challenges.md)
