# MRCS — API Integrations & Third-Party Calls

All SPA calls go over **HTTPS REST (TLS 1.2+)** with Entra B2E Bearer unless noted.

## Gateway

| Service | Stack | Responsibility |
|---------|-------|----------------|
| `lenders-internal-gateway` (LIG) | Spring Cloud Gateway (OpenShift) | Validate User Bearer; issue Downstream Bearer; route to providers |

## Provider catalog

### 1. Lender / MIS — search lender

Used by **Search Lender by Account Number** and **Search by Name Address Details**.

| Method / path (as documented) | Use |
|-------------------------------|-----|
| `GET lender/(ad)/F` | Lender lookup by account-oriented pattern |
| `GET lender/P?name,addr` | Lender search by name and address |

**UI mapping**

- Account Number tab → account-oriented lender GET  
- Name/Address tab → `name` + `addr` (and city/state as product requires)

### 2. Address / SF Region (LOC) — address-based third party

| Method / path | Use |
|---------------|-----|
| `POST login/address/Region` | Resolve region / location from address |

This is the primary **“based on address, calling third party”** integration called out in work notes.

### 3. All — agreement info

| Method / path | Use |
|---------------|-----|
| `POST agreements/` | Agreement information |

### 4. DVL — Data Virtualization (policy)

| Method / path | Use | Extra auth |
|---------------|-----|------------|
| `PnC-Policy/Retrieval/policies-amm` | Policy retrieval | `X-API-KEY` |

### 5. CV API — WRITE (record change submit)

| Method / path | Use |
|---------------|-----|
| `POST ling-spin-vea/record-change` | Persist mortgage record change |

Maps to Change Details **Submit**.

## Suggested local mock contracts (for coding)

Keep OpenAPI or JSON fixtures under `projects/mrcs/backend/mocks/`.

### Lender by account

```http
GET /lender/account/{accountNumber}
Authorization: Bearer <token>
```

```json
{
  "accountNumber": "ABCDE12345",
  "lenderName": "Example Lender",
  "address": "123 Main St",
  "city": "Bloomington",
  "state": "IL"
}
```

### Lender by name/address

```http
GET /lender/search?name=...&address=...&city=...&state=...
Authorization: Bearer <token>
```

### Address → region

```http
POST /login/address/Region
Authorization: Bearer <token>
Content-Type: application/json

{
  "address": "123 Main St",
  "city": "Bloomington",
  "state": "IL",
  "zip": "61701"
}
```

```json
{
  "region": "IL-CENTRAL",
  "sfRegionCode": "XX"
}
```

### Record change submit

```http
POST /ling-spin-vea/record-change
Authorization: Bearer <token>
Content-Type: application/json

{
  "changeType": "Audit",
  "currentLender": { "accountNumber": "ABCDE12345" },
  "policies": [
    {
      "policyNumber": "123456789",
      "propertyZip": "61701",
      "newLoanNumber": "LN00000001",
      "billingInstructions": "...",
      "mortgageOrder": "..."
    }
  ]
}
```

## Error handling expectations

| Case | SPA behavior |
|------|----------------|
| 401 / invalid B2E token | Re-auth or session expired message |
| Validation 400 | Field-level or toast errors |
| Downstream timeout | Retry guidance / soft fail |
| Empty lender search | Empty-state message, allow refine search |

## Topology (SPA perspective)

```text
React SPA
  ├─ authInterceptor (B2E Bearer)
  ├─ lenderApi ──────────────► LIG ──► Lender / MIS
  ├─ addressRegionApi ───────► LIG ──► Address / SF Region (LOC)
  ├─ policyApi (optional) ───► LIG ──► DVL (+ X-API-KEY at gateway)
  └─ recordChangeApi ────────► LIG ──► CV API WRITE
```
