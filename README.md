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
| [`resumes/`](./resumes/) | ATS-friendly Full Stack Developer resume (DOCX + PDF + TXT) |
| [`docs/mrcs/`](./docs/mrcs/) | MRCS product docs: architecture, B2E auth, UI flows, APIs, work notes |
| [`docs/study-plans/`](./docs/study-plans/) | Interview study plans (Java 8 + Spring Boot questions) |
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

Primary file: [`resumes/Ravi_Ranjan_Upadhyay_Full_Stack_Developer.docx`](./resumes/Ravi_Ranjan_Upadhyay_Full_Stack_Developer.docx)  
ATS plain text: [`resumes/Ravi_Ranjan_Upadhyay_Full_Stack_Developer_ATS.txt`](./resumes/Ravi_Ranjan_Upadhyay_Full_Stack_Developer_ATS.txt)

## Interview study plan

- **Java 1.8 interview Q&A (concepts + answers):** [`docs/study-plans/java8-interview-qa.md`](./docs/study-plans/java8-interview-qa.md)  
- **Java 8 interfaces (default/static/diamond) with examples:** [`docs/study-plans/java8-interfaces-default-static-diamond.md`](./docs/study-plans/java8-interfaces-default-static-diamond.md)  
- **Java 1.8 topics + interview Qs + hard challenges:** [`docs/study-plans/java8-topics-questions-challenges.md`](./docs/study-plans/java8-topics-questions-challenges.md)  
- **Java 1.8 day-by-day (21 days):** [`docs/study-plans/java8-day-by-day.md`](./docs/study-plans/java8-day-by-day.md)  
- **Java 1.8 + Spring Boot (full plan + question bank):** [`docs/study-plans/java8-springboot-study-plan.md`](./docs/study-plans/java8-springboot-study-plan.md)

## Next coding step

Implement the React screens and local mocks using the rules and OpenAPI sketch under `projects/mrcs/`, driven by the specs in `docs/mrcs/`.
