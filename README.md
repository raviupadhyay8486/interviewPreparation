# interviewPreparation

Repository for **Java / AWS / Terraform / React** interview prep — plus a documented rebuild path for the **Mortgage Record Change System (MRCS)** work.

## Contents

| Path | Purpose |
|------|---------|
| [`resumes/`](./resumes/) | ATS-friendly Full Stack Developer resume (DOCX + TXT) |
| [`docs/mrcs/`](./docs/mrcs/) | MRCS product docs: architecture, B2E auth, UI flows, APIs, work notes |
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

Java 1.8 + Spring Boot (questions mapped to resume projects):  
[`docs/study-plans/java8-springboot-study-plan.md`](./docs/study-plans/java8-springboot-study-plan.md)

## Next coding step

Implement the React screens and local mocks using the rules and OpenAPI sketch under `projects/mrcs/`, driven by the specs in `docs/mrcs/`.
