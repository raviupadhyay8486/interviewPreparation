# How to Set Up Your Work in This Repository

This repo is your **interview prep + MRCS coding workspace**. Follow the steps below on your laptop (VS Code / IntelliJ / Cursor).

---

## 1. Get the code on your machine

### Option A — Clone (first time)

```bash
git clone https://github.com/raviupadhyay8486/interviewPreparation.git
cd interviewPreparation
```

### Option B — Already have the folder

```bash
cd interviewPreparation
git fetch origin
```

### Use the working branch (all current work is here)

Right now everything (resume, study plans, MRCS docs) is on:

```bash
git checkout cursor/update-resume-mrcs-dfbf
git pull origin cursor/update-resume-mrcs-dfbf
```

> After the PR is merged, you can use `main` instead:
> ```bash
> git checkout main
> git pull origin main
> ```

**PR link:** https://github.com/raviupadhyay8486/interviewPreparation/pull/3

---

## 2. What is where (your workspace map)

```text
interviewPreparation/
├── resumes/                  ← Your resume (Word + PDF + ATS text)
├── docs/
│   ├── mrcs/                 ← MRCS project knowledge (architecture, UI, APIs, B2E)
│   └── study-plans/          ← Java 8 study plans + interview Q&A
├── projects/
│   └── mrcs/                 ← Place to build MRCS code (frontend + backend mocks)
├── README.md
└── SETUP.md                  ← This file
```

| You want to… | Go here |
|--------------|---------|
| Download / edit resume | `resumes/` |
| Study Java 1.8 Q&A | `docs/study-plans/java8-interview-qa.md` |
| Day-by-day Java plan | `docs/study-plans/java8-day-by-day.md` |
| Hard Java challenges | `docs/study-plans/java8-topics-questions-challenges.md` |
| Understand MRCS system | `docs/mrcs/README.md` |
| Start coding MRCS | `projects/mrcs/` |

---

## 3. Tools to install (recommended)

| Tool | Why |
|------|-----|
| **Git** | Clone/commit/push |
| **VS Code or Cursor or IntelliJ** | Edit docs + code |
| **JDK 8 or 11+** | Java practice / future Spring Boot |
| **Node.js 18+ & npm** | When you scaffold React MRCS frontend |
| **Maven or Gradle** (later) | When you add Spring Boot mocks |

Check versions:

```bash
git --version
java -version
node -v
npm -v
```

---

## 4. Daily workflow (simple)

### A) Interview study only (no coding)

1. Open `docs/study-plans/java8-interview-qa.md`
2. Answer 10–15 questions out loud
3. Optionally track progress in a personal notes file (e.g. `docs/study-plans/my-progress.md`)

### B) Java coding practice

1. Create a practice folder (first time):

```bash
mkdir -p projects/java8-practice/src
```

2. Write `.java` files there (Streams, Optional, challenges)
3. Compile/run:

```bash
cd projects/java8-practice/src
javac YourClass.java
java YourClass
```

Or open the folder in IntelliJ as a simple Java project.

### C) Build MRCS (React + APIs)

1. Read specs: `docs/mrcs/`
2. Code lives in: `projects/mrcs/`
3. Suggested order:
   - Scaffold React app in `projects/mrcs/frontend`
   - Keep using existing stubs: `validation/rules.ts`, `auth/b2eAuth.ts`, `api/mrcsApi.ts`
   - Add Express or Spring Boot mocks in `projects/mrcs/backend`

Frontend (after you scaffold Vite/CRA):

```bash
cd projects/mrcs/frontend
npm install
npm start
# http://localhost:3000
```

---

## 5. Git workflow for your changes

Always work on a feature branch (don’t commit directly to `main` if you share PRs):

```bash
# from latest working branch
git checkout cursor/update-resume-mrcs-dfbf
git pull

# create your own branch for new work
git checkout -b cursor/my-java-practice

# after edits
git add .
git status
git commit -m "Add Java Streams practice examples"
git push -u origin cursor/my-java-practice
```

Then open a Pull Request on GitHub into `main` (or into the existing working branch).

---

## 6. Download your resume

From GitHub folder:

https://github.com/raviupadhyay8486/interviewPreparation/tree/cursor/update-resume-mrcs-dfbf/resumes

Or locally after clone:

- PDF: `resumes/Ravi_Ranjan_Upadhyay_Java_Full_Stack_Architect.pdf`

---

## 7. Suggested first week setup plan

| Day | Action |
|-----|--------|
| Day 1 | Clone repo, checkout branch, open in VS Code/Cursor |
| Day 1 | Download resume PDF/DOCX; verify study-plan files open |
| Day 2–3 | Java Q&A: 20 questions/day from `java8-interview-qa.md` |
| Day 4 | Create `projects/java8-practice` and solve 2 Streams problems |
| Day 5 | Read `docs/mrcs/architecture.md` + `ui-flows.md` |
| Day 6–7 | Scaffold React app under `projects/mrcs/frontend` (optional) |

---

## 8. If PR #3 is not merged yet

Your latest work is only on branch `cursor/update-resume-mrcs-dfbf`.

- To **use** it: checkout that branch (step 1).
- To **merge** it into `main`: open PR #3 on GitHub → Review → Merge.
- After merge, everyone cloning `main` will get resume + docs + scaffolds automatically.

---

## 9. Common issues

| Problem | Fix |
|---------|-----|
| `main` looks empty / old | Checkout `cursor/update-resume-mrcs-dfbf` or merge the PR |
| Don’t see PDF/DOCX | `git pull` on the working branch |
| Want to edit resume | Edit DOCX locally, then `git add resumes/ && git commit && git push` |
| Node/Java not found | Install JDK / Node and restart terminal |

---

## 10. Quick start commands (copy-paste)

```bash
git clone https://github.com/raviupadhyay8486/interviewPreparation.git
cd interviewPreparation
git checkout cursor/update-resume-mrcs-dfbf
git pull origin cursor/update-resume-mrcs-dfbf

# open in VS Code
code .

# or open in Cursor
cursor .
```

Then start with:

1. `docs/study-plans/java8-interview-qa.md` (study)  
2. `docs/mrcs/README.md` (project knowledge)  
3. `projects/mrcs/` (coding)

---

**Need help next?** Ask to scaffold:
- React Vite app for MRCS, or  
- Java Maven project for Streams practice / Spring Boot lender API mocks.
