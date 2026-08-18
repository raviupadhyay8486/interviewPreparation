# ATS Score Fix Guide — Why 55% and How to Reach 90%+

**Your HR ATS score:** 55%  
**Target for selection:** 90%+  
**Profile:** 14 years Java, Spring Boot, Microservices, AWS

---

## How ATS Scoring Works (What HR Tools Actually Check)

Most ATS tools (Naukri Resdex, Zoho Recruit, HirePro, Workday, SuccessFactors) score resumes by comparing your resume text against a **job description template** or **keyword list** the recruiter sets.

| Factor | Weight | Your likely issue |
|--------|--------|-------------------|
| **Keyword match** | 40-50% | Missing Java 17, SOLID, Design Patterns, Maven, Gradle, standard job titles |
| **Job title match** | 15-20% | Resume says "Technical Lead" but JD searches "Java Developer" / "Spring Boot Developer" |
| **Skills section parse** | 10-15% | Skills buried in paragraphs; ATS cannot extract them |
| **Format / parsing** | 10-15% | PDF tables, special bullets, columns, headers break text extraction |
| **Experience years** | 5-10% | Must appear clearly as "14 years" in summary and dates |
| **Education match** | 5% | MCA must be clearly labeled |

**A 55% score usually means:** your experience is strong, but **40-45% of JD keywords are missing or not parseable** — not that you are unqualified.

---

## Why Your Original Resume Scored ~55% (Specific Issues)

### 1. Wrong job title for ATS search
- **Resume title:** "Java & AWS Technical Lead"
- **ATS searches:** Senior Java Developer, Java Developer, Spring Boot Developer, Backend Developer, Microservices Developer
- **Fix:** Add all matching titles in headline and first line of summary

### 2. Missing high-value keywords (you have the skills but didn't list them)
| Missing keyword | You have this experience |
|-----------------|--------------------------|
| Java 17 | You stated proficiency |
| SOLID Principles | You stated proficiency |
| Design Patterns | You stated proficiency |
| Maven / Gradle | Standard for all your Java projects |
| Object Oriented Programming OOP | 14 years Java |
| SDLC | Every project |
| Microservices Architecture | Multiple migrations |
| RESTful Web Services | Every recent role |
| Amazon Web Services (spell out + AWS) | State Farm, Ancestry |
| Unit Testing / Integration Testing | JUnit Mockito |
| Agile / Scrum | Every role |

### 3. Keywords only in "Tech:" lines — ATS ignores these
ATS weights keywords in **Skills section** and **bullet points** more than trailing "Tech Stack:" lines at end of jobs.

### 4. Formatting broke ATS parsing
From your original PDF:
- Mixed bullet symbols (▪ and ●) — some ATS fail to parse
- "CORE COMPETENCIES" split awkwardly across lines
- React.js prominent — wrong keyword cluster for backend JD
- Niche terms (ROSA, Sclar typo) — zero JD match
- Possible multi-column layout in PDF export

### 5. Skills section structure
ATS prefers:
```
CORE SKILLS
Java, Spring Boot, Microservices, AWS, Kafka, Docker, Kubernetes...
```
Not categorized sub-headers with only 3-4 items each.

### 6. Duplicate employer entries
Napier listed 4 times — some ATS count as job hopping or fail to merge tenure correctly.

---

## Action Plan: 55% → 90%+ in 5 Steps

### Step 1 — Use the ATS-optimized resume file
File: `Ravi_Ranjan_Upadhyay_Resume_ATS_90.md`

This version includes:
- Standard ATS headers (PROFESSIONAL SUMMARY, CORE SKILLS, PROFESSIONAL EXPERIENCE, EDUCATION)
- Job title keywords in headline
- 80+ backend keywords repeated in skills AND experience bullets
- Java 17, SOLID, Design Patterns, Maven, OOP, SDLC
- Plain text structure (no tables, no columns)
- Consolidated Napier employment
- "January 2024 to Present" date format (ATS-friendly)

### Step 2 — Export correctly (critical)

| Do | Don't |
|----|-------|
| Copy to **Microsoft Word** | Export from Canva or fancy templates |
| Use **Arial or Calibri 10-11pt** | Use icons, graphics, skill bars |
| **Single column** layout | Two-column resume layouts |
| Save as **.docx** AND **.pdf** | Only upload scanned image PDF |
| Simple round bullets or hyphens | Special Unicode bullets ▪ ● ✓ |
| One page margin standard | Text boxes or tables for skills |

**Test your PDF:** open PDF → Select All → Copy → Paste into Notepad. If text is garbled or missing, ATS will score low.

### Step 3 — Tailor per job (adds 10-20% to score)

Before each application, open the JD and add **exact phrases** from JD into your summary (if true):

Example JD says: *"Spring Boot 3, Java 17, AWS EKS, PostgreSQL"*

Add to summary:
> ...experienced in Spring Boot 3, Java 17, AWS EKS Kubernetes, and PostgreSQL...

**Rule:** mirror JD language exactly — ATS is literal string matching.

### Step 4 — Fill platform skill matrices

On **Naukri**, **LinkedIn**, **Instahyre**:
- Add ALL skills from CORE SKILLS section manually
- Set experience level per skill (Expert for Java, Spring Boot, Microservices)
- Naukri resume score often differs from HR ATS — fill every field

### Step 5 — Match job title on application form

| Application field | Enter |
|-------------------|-------|
| Current designation | Senior Java Developer |
| Desired job title | Java Developer / Spring Boot Developer |
| Total experience | 14 years |

Do NOT enter only "Technical Lead" if applying for developer roles.

---

## Keyword Checklist — Verify Before Upload

Check each item appears **at least twice** (Skills + Experience):

- [ ] Java
- [ ] Java 8
- [ ] Java 11
- [ ] Java 17
- [ ] Spring Boot
- [ ] Spring Cloud
- [ ] Spring Security
- [ ] Spring Data JPA
- [ ] Hibernate
- [ ] JPA
- [ ] Microservices
- [ ] Microservices Architecture
- [ ] REST API
- [ ] RESTful Web Services
- [ ] AWS
- [ ] Amazon Web Services
- [ ] AWS EC2
- [ ] AWS S3
- [ ] AWS Lambda
- [ ] AWS API Gateway
- [ ] Docker
- [ ] Kubernetes
- [ ] Terraform
- [ ] Jenkins
- [ ] CI/CD
- [ ] Git
- [ ] Apache Kafka
- [ ] JUnit
- [ ] Mockito
- [ ] Unit Testing
- [ ] SQL
- [ ] SQL Server
- [ ] Maven
- [ ] Agile
- [ ] Scrum
- [ ] SOLID Principles
- [ ] Design Patterns
- [ ] Object Oriented Programming
- [ ] OOP
- [ ] SDLC
- [ ] OAuth2
- [ ] Technical Lead
- [ ] Senior Java Developer
- [ ] Backend Developer
- [ ] 14 years experience

---

## What NOT to Do (Lowers ATS Score)

1. **Keyword stuffing** invisible white text — HR systems detect this; instant rejection
2. **Fake certifications** — verify against databases
3. **Wrong file type** — .jpg resume, password-protected PDF
4. **Header/footer** with contact info only in header — ATS may skip headers
5. **Abbreviations only** — write "Amazon Web Services AWS" not just "AWS" once
6. **Creative section names** — use "Professional Experience" not "My Journey"

---

## Ask HR These Questions (Gets You Exact Keyword List)

Send your HR this message:

> "To improve my ATS score from 55% to 90%+, could you share:
> 1. The job description or keyword template used for scoring?
> 2. Preferred resume format (docx vs pdf)?
> 3. Target job title keywords they search for?
> 4. Any mandatory skills flagged as required vs optional?"

Many recruiters will share the JD template — then tailor resume to match **exact words**.

---

## Files in This Folder

| File | Use |
|------|-----|
| `Ravi_Ranjan_Upadhyay_Resume_ATS_90.md` | **Upload this version** — optimized for 90%+ ATS |
| `Ravi_Ranjan_Upadhyay_Resume_IMPROVED.md` | Human-readable version for networking |
| `Ravi_Ranjan_Upadhyay_Resume_ATS_Fix_Guide.md` | This guide |

---

## Quick Win Summary

| Change | Expected ATS impact |
|--------|---------------------|
| Add Java 17, SOLID, Design Patterns, Maven | +15-20% |
| Change title to Senior Java Developer | +10-15% |
| Dense CORE SKILLS section | +10-15% |
| Repeat keywords in every job bullet | +10% |
| Fix PDF format (single column, plain bullets) | +5-10% |
| Tailor summary to each JD | +10-20% |

**Realistic target after fixes: 85-95%** depending on JD match.
