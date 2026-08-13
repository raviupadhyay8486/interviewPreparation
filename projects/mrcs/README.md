# MRCS Coding Scaffold

Implementation home for the **Mortgage Record Change System** (newest version).

Product docs (architecture, UI, auth, APIs): [`../../docs/mrcs/`](../../docs/mrcs/)

## Layout

```text
projects/mrcs/
  README.md                 ← you are here
  frontend/                 ← React SPA (to be implemented)
  backend/                  ← Local gateway + third-party mocks
  docs/                     ← Implementation ADRs / runbooks
```

## Dev goals

Recreate the Jan 2025+ MRCS behavior for practice and interview demos:

- React Change Details + lender search (account number / name-address)
- B2E-style Bearer header on API calls (mockable locally)
- Address → region third-party call
- Submit record-change through a local LIG-like gateway

## Quick start (planned)

```bash
# Frontend (after scaffold)
cd projects/mrcs/frontend
npm install
npm start   # http://localhost:3000

# Backend mocks (after scaffold)
cd projects/mrcs/backend
npm install   # or ./mvnw spring-boot:run
npm run dev   # http://localhost:8080
```

## Spec sources of truth

| Concern | Doc |
|---------|-----|
| Topology | `docs/mrcs/architecture.md` |
| Auth | `docs/mrcs/auth-b2e.md` |
| Screens / validation | `docs/mrcs/ui-flows.md` |
| APIs | `docs/mrcs/api-integrations.md` |
| Work scope | `docs/mrcs/work-notes.md` |

Shared validation constants for coding live in:

- `frontend/src/validation/rules.ts` (stub)
- `backend/mocks/openapi-sketch.yaml` (stub)
