# MRCS Frontend (React SPA)

Target: recreate Lender Relations **Mortgage Record Change System** UI.

## Planned routes

| Path | Screen |
|------|--------|
| `/` or `/mrcs/changeDetails` | Change Details |
| `/mrcs/lenderDetailsPage` | Select Current Lender (tabs) |

## Planned packages

- React 18+
- React Router
- Axios (or fetch) + B2E auth interceptor
- Form validation aligned with `src/validation/rules.ts`

## Environment (placeholders)

```env
REACT_APP_API_BASE_URL=http://localhost:8080
REACT_APP_B2E_CLIENT_ID=
REACT_APP_B2E_TENANT=
REACT_APP_B2E_SCOPE=
```

Do not commit secrets.

## Next implementation step

Scaffold Create React App / Vite React+TS app into this folder and implement screens per `docs/mrcs/ui-flows.md`.
