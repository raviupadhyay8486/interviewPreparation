# Mortgage Record Change System (MRCS)

**Client:** State Farm — Lender Relations  
**Role context:** Full Stack (React.js + B2E auth + third-party integrations)  
**Period:** Jan 2025 – Present (newest version of the product)  
**Environment example:** `localhost:3000` (local React SPA)

This folder captures architecture, UI flows, authentication, and API topology from the live MRCS work so we can rebuild and extend the application in this repository.

## What MRCS does

Internal State Farm employees use MRCS to submit **mortgage record changes**:

1. Select **Change Type** (e.g. Audit)
2. Select **Current Lender** (search by account number or name/address)
3. Enter **Policy Details** (policy number, property zip, loan number, billing, mortgage order)
4. Optionally add additional policy info / multiple policy rows
5. Submit the change request through secured APIs

## Responsibility focus (Ravi)

Work leaned toward:

1. Understanding the **old / previous version** of the same product
2. Reading and mapping existing code, screens, and API contracts
3. Re-implementing behavior in the **newest version**
4. Contributing **React.js** UI and **B2E header authentication**
5. Implementing **search lender by account number**
6. Calling **third-party APIs based on address**
7. Creating / documenting **project topology** and downstream integrations

## Doc index

| Doc | Purpose |
|-----|---------|
| [architecture.md](./architecture.md) | AWS edge + SPA + gateway + downstream topology |
| [auth-b2e.md](./auth-b2e.md) | Microsoft Entra ID B2E / Bearer header flow |
| [ui-flows.md](./ui-flows.md) | Screens, fields, validation rules |
| [api-integrations.md](./api-integrations.md) | LIG + third-party / internal provider endpoints |
| [work-notes.md](./work-notes.md) | Contribution notes for resume + future coding |

## Related repo paths

- Resume artifacts: [`../../resumes/`](../../resumes/)
- Coding scaffold (future implementation): [`../../projects/mrcs/`](../../projects/mrcs/)
