# MRCS — UI Flows & Validation Rules

Observed from local runs (`localhost:3000`) of the newest MRCS React SPA.

## App chrome

| Element | Detail |
|---------|--------|
| Brand | State Farm–style mark + **Mortgage Record Change System** |
| Subtitle | **Lender Relations** |
| Header actions | Help, user profile (e.g. Ravi) |
| Primary route example | `/mrcs/lenderDetailsPage` for lender selection |

---

## Screen 1 — Change Details

Main card after login / app open.

### Fields

| Field | Type | Rules / notes |
|-------|------|----------------|
| **Change Type** | Dropdown | Example value: `Audit` |
| **Current Lender** | Required action | Red **Select** button → opens lender search flow |
| **Policy Number** | Text | Required; help icon; counter `0/9` (max 9) |
| **Property Zip** | Text | Required; help icon; counter `0/9` (max 9) |
| **New Loan Number** | Text | Counter `0/10` (max 10) |
| **Billing Instructions** | Dropdown | Placeholder: Select |
| **Mortgage Order** | Dropdown | — |
| **+** | Icon button | Add another policy row |
| **Show additional policy details** | Checkbox | When policy number unavailable, use additional info |

### Instructional note

> If policy number is not available, then select checkbox below the record to enter additional information.

### Actions

| Button | State |
|--------|-------|
| **Submit** | Disabled until form valid / lender selected |
| **Reset** | Always available (destructive / clear) |

---

## Screen 2 — Select Current Lender (tabs)

Opened from **Select** on Current Lender.

### Tab A — Search by Name Address Details (default in one capture)

| Field | Validation |
|-------|------------|
| **Name** | Required; **3–90** characters; counter `0/90`; help icon |
| **Address** | **2–60** characters; **cannot contain commas**; counter `0/60` |
| **City** | Letters only; **2–18** characters; counter `0/18` |
| **State** | Dropdown |

Actions: **Search** (disabled until valid), **Reset**, **Back**

### Tab B — Search Lender by Account Number

Route context: `localhost:3000/mrcs/lenderDetailsPage`

| Field | Validation |
|-------|------------|
| **Account Number** | Exactly **10 alphanumeric** characters; counter `0/10` |

Actions: **Search** (disabled until 10 valid chars), **Reset**, **Back**

---

## Suggested React module map (for coding)

```text
projects/mrcs/frontend/src/
  pages/
    ChangeDetailsPage.tsx
    LenderDetailsPage.tsx          # tabs: name-address | account number
  components/
    ChangeDetailsForm.tsx
    PolicyRow.tsx
    LenderSearchByNameAddress.tsx
    LenderSearchByAccountNumber.tsx
    AppHeader.tsx
  auth/
    b2eAuth.ts
    authInterceptor.ts
  api/
    lenderApi.ts                   # account + name/address search
    addressRegionApi.ts            # third-party / LOC region by address
    recordChangeApi.ts
  validation/
    lenderSearchRules.ts
    policyFieldRules.ts
```

## UX behaviors to preserve

1. Character counters on constrained fields  
2. Inline validation messages under fields  
3. Search disabled until rules pass  
4. Back returns to Change Details without losing more state than product requires  
5. Multiple policy rows via **+**  
6. Additional policy details gated by checkbox  

## Address → third-party call (UI trigger)

When address (and related) fields are used for lender/region resolution, the SPA should call the Address / SF Region provider (see [api-integrations.md](./api-integrations.md)) after B2E auth headers are attached.
