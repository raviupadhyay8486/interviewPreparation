# MRCS Extracted Source (from screenshots)

Reconstructed from VS Code screenshots of **mortgage-record-change-system**.

> WhatsApp video paths on your Mac were **not accessible** from this environment.
> Upload those videos into the chat if you want them reviewed too.

## Files extracted

| Local path in this repo | Original path |
|-------------------------|---------------|
| `src/pages/SelectLenderDetails.jsx` | `src/pages/SelectLenderDetails.jsx` |
| `src/helpers/lenders/currentLenderFetchers.jsx` | `src/helpers/lenders/currentLenderFetchers.jsx` |
| `src/helpers/lenders/lenderDataHelpers.jsx` | `src/helpers/lenders/lenderDataHelpers.jsx` |
| `src/helpers/lenders/lenderSelectorHelpers.jsx` | `src/helpers/lenders/lenderSelectorHelpers.jsx` |
| `src/helpers/lenders/lenderDetailsTableHelpers.jsx` | `src/helpers/lenders/lenderDetailsTableHelpers.jsx` |
| `src/lib/fetchClient.jsx` | `@statefarm/lr-reusable-components-library/.../fetchClient.jsx` |
| `src/helpers/changeDetailsHelpers.js` | partial stub; `getLenderSlotKey` re-exported from selector helpers |
| `src/data/changeTypes.js` | `src/data/changeTypes.js` |
| `src/data/policyTypes.js` | `src/data/policyTypes.js` |
| `src/data/mortgageOrder.js` | `src/data/mortgageOrder.js` |
| `src/data/billingInstructions.js` | `src/data/billingInstructions.js` |
| `src/data/states.js` | `src/data/states.js` |
| `src/data/JS/policySearchErrorMessages.js` | `src/data/JS/policySearchErrorMessages.js` |
| `src/context/usePersistentContext.jsx` | `src/context/usePersistentContext.jsx` |
| `src/context/AliasContext.jsx` | `src/context/AliasContext.jsx` |

## Flow (matches architecture)

```text
SelectLenderDetails
  ├─ handleAccountSearch  → fetchLenderByAccountNumber → GET lender/{account}/F
  ├─ handleNameAddressSearch → fetchLenderDetailsByAddress → GET lender/F?name&address&city&state
  ├─ transformApiResponse → normalize to { nameAddress: [...] }
  ├─ lenderDetailsTableHelpers → table headers/cells / row slot index
  ├─ lenderSelectorHelpers → Old/New (100, 114) vs Current lender slots
  ├─ handleLenderSelection → formatLenderForNavigation → formatLenderForChangeScreen → Change Details
  └─ insertMortgageRecord (fetcher) → POST linq-api/mrcs/record-change

AliasProvider / usePersistentContext
  └─ sessionStorage-backed aliasId for the MRCS session

fetchClient
  └─ configureApi → attaches Authorization: Bearer <token> via setAccessTokenProvider (B2E)
```

## Env vars used

```env
VITE_API_BASE_URL=
VITE_INSERT_API_BASE_URL=
```

## Reconstruction notes

- **`states.js`**: screenshot cut off after `NH`; remaining US states + Canadian provinces completed in the same abbreviation shape.
- **`policyTypes.js`**: `"C"` label completed as `"Rental Condominium Unit Owners"` (truncated ended at `"Ow"`).
- **`policySearchErrorMessages.js`**: PLCC phone `1-844-275-7522` is from the photos; trailing “or Business …” wording was stitched where the right edge was cut off.
- **`getLenderSlotKey`**: completed `New Lender` / `Current Lender` cases from the switch pattern shown.

## Gaps / TODO from screenshots

- Full `SearchLender`, `LenderDetailsTable`, `LenderAddressDetailsTable` components (imported, not fully shown)
- Full `ChangeDetails.jsx` / complete `changeDetailsHelpers.jsx`
- Full `PolicyDetails.jsx` (only row add/remove / “Show additional policy details” fragment visible)
- Exact navigation path after lender select (inferred)
- `configureApi` middle lines may vary slightly from library version

## Copy to your laptop

```bash
git pull origin cursor/extract-mrcs-helpers-data-dfbf
# then copy under projects/mrcs/frontend/src/:
#   helpers/lenders/*
#   data/*
#   context/*
#   pages/SelectLenderDetails.jsx
#   lib/fetchClient.jsx
```
