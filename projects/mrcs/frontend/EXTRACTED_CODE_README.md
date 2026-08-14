# MRCS Extracted Source (from screenshots)

Reconstructed from VS Code screenshots of **mortgage-record-change-system**.

> WhatsApp video paths on your Mac were **not accessible** from this environment:
> `/Users/raviranjanupadhyay/Downloads/WhatsApp Unknown 2026-08-14.../*.mp4`  
> Upload those videos into the chat if you want them reviewed too.

## Files extracted

| Local path in this repo | Original path |
|-------------------------|---------------|
| `src/pages/SelectLenderDetails.jsx` | `src/pages/SelectLenderDetails.jsx` |
| `src/helpers/lenders/currentLenderFetchers.jsx` | `src/helpers/lenders/currentLenderFetchers.jsx` |
| `src/helpers/lenders/lenderDataHelpers.jsx` | `src/helpers/lenders/lenderDataHelpers.jsx` |
| `src/lib/fetchClient.jsx` | `@statefarm/lr-reusable-components-library/.../fetchClient.jsx` |
| `src/helpers/changeDetailsHelpers.js` | stub (full helpers file not fully visible) |

## Flow (matches architecture)

```text
SelectLenderDetails
  ├─ handleAccountSearch  → fetchLenderByAccountNumber → GET lender/{account}/F
  ├─ handleNameAddressSearch → fetchLenderDetailsByAddress → GET lender/F?name&address&city&state
  ├─ transformApiResponse → normalize to { nameAddress: [...] }
  ├─ handleLenderSelection → formatLenderForNavigation → formatLenderForChangeScreen → Change Details
  └─ insertMortgageRecord (fetcher) → POST linq-api/mrcs/record-change

fetchClient
  └─ configureApi → attaches Authorization: Bearer <token> via setAccessTokenProvider (B2E)
```

## Env vars used

```env
VITE_API_BASE_URL=
VITE_INSERT_API_BASE_URL=
```

## Gaps / TODO from screenshots

- Full `SearchLender`, `LenderDetailsTable`, `LenderAddressDetailsTable` components (imported, not shown)
- Full `changeDetailsHelpers.jsx` / `ChangeDetails.jsx` (only stubs reconstructed)
- Exact navigation path after lender select (inferred)
- `configureApi` middle lines may vary slightly from library version

## Copy to your laptop

```bash
git pull origin main
# then copy:
# projects/mrcs/frontend/src/pages/SelectLenderDetails.jsx
# projects/mrcs/frontend/src/helpers/lenders/*
# projects/mrcs/frontend/src/lib/fetchClient.jsx
```
