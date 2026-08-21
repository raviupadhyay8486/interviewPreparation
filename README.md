# interviewPreparation

Repository for **Java / AWS / Terraform / React** interview prep — plus a documented rebuild path for the **Mortgage Record Change System (MRCS)** work.

## Setup (start here)

**Full setup guide:** [`SETUP.md`](./SETUP.md)

```bash
git clone https://github.com/raviupadhyay8486/interviewPreparation.git
cd interviewPreparation
git checkout cursor/update-resume-mrcs-dfbf
git pull origin cursor/update-resume-mrcs-dfbf
```

Open the folder in VS Code / Cursor / IntelliJ, then use the map below.

## Contents

| Path | Purpose |
|------|---------|
| [`resume/`](./resume/) | Updated resume (Java Architect PDF) |
| [`docs/mrcs/`](./docs/mrcs/) | MRCS product docs: architecture, B2E auth, UI flows, APIs, work notes |
| [`docs/study-plans/`](./docs/study-plans/) | Interview study plans (Java 8 + Spring Boot questions) |
| [`projects/java8-springboot-learn/`](./projects/java8-springboot-learn/) | **Runnable Java 1.8 + Spring Boot topic packages** (streams, collectors, etc.) |
| [`projects/java8-practice/`](./projects/java8-practice/) | Small plain-Java Streams practice |
| [`projects/mrcs/`](./projects/mrcs/) | Coding scaffold (React frontend stubs + backend OpenAPI mocks) |

## MRCS (State Farm — Lender Relations)

Newest-version project context (Jan 2025+):

- React.js SPA (Change Details, lender search)
- Microsoft Entra ID **B2E** Bearer / header authentication
- Search lender by **account number** and **name/address**
- **Address → third-party** region/lender calls
- Topology: SPA → AWS edge (CloudFront/WAF/S3) → LIG (Spring Cloud Gateway) → downstream APIs

Start here: **[docs/mrcs/README.md](./docs/mrcs/README.md)**  
Code scaffold: **[projects/mrcs/README.md](./projects/mrcs/README.md)**

## Resume

| File | Use for |
|------|---------|
| [`resume/Ravi_Ranjan_Upadhyay_Java_Architect.pdf`](./resume/Ravi_Ranjan_Upadhyay_Java_Architect.pdf) | General Java Architect applications |
| [`resume/Ravi_Ranjan_Upadhyay_HappiestMinds_Java_Architect.pdf`](./resume/Ravi_Ranjan_Upadhyay_HappiestMinds_Java_Architect.pdf) | Happiest Minds (HLD/LLD, Spring Boot microservices, AWS S3/ECS, Kafka) |
| [`resume/Ravi_Ranjan_Upadhyay_Coforge_Lead.pdf`](./resume/Ravi_Ranjan_Upadhyay_Coforge_Lead.pdf) | Coforge Technical Lead (MRCS + GitHub Copilot) |
| [`resume/Ravi_Ranjan_Upadhyay_Java_OpenShift_Lead.pdf`](./resume/Ravi_Ranjan_Upadhyay_Java_OpenShift_Lead.pdf) | Java / OpenShift Technical Lead JD (Java 8/11/17/21, Spring Boot 3.x, React in R&R, OpenShift, TDD/CI-CD) |

## Interview study plan

- **Java 1.8 interview Q&A (concepts + answers):** [`docs/study-plans/java8-interview-qa.md`](./docs/study-plans/java8-interview-qa.md)  
- **Java 8 flatMap + Collectors (examples & tricky problems):** [`docs/study-plans/java8-flatmap-collectors-guide.md`](./docs/study-plans/java8-flatmap-collectors-guide.md)  
- **Java 8 interfaces (default/static/diamond) with examples:** [`docs/study-plans/java8-interfaces-default-static-diamond.md`](./docs/study-plans/java8-interfaces-default-static-diamond.md)  
- **Java 1.8 topics + interview Qs + hard challenges:** [`docs/study-plans/java8-topics-questions-challenges.md`](./docs/study-plans/java8-topics-questions-challenges.md)  
- **Java 1.8 day-by-day (21 days):** [`docs/study-plans/java8-day-by-day.md`](./docs/study-plans/java8-day-by-day.md)  
- **Java 1.8 + Spring Boot (full plan + question bank):** [`docs/study-plans/java8-springboot-study-plan.md`](./docs/study-plans/java8-springboot-study-plan.md)

## Next coding step

Implement the React screens and local mocks using the rules and OpenAPI sketch under `projects/mrcs/`, driven by the specs in `docs/mrcs/`.
