# MRCS — AWS Architecture & Project Topology

Source: **MRCS – AWS Architecture** (Mortgage Record Change System) diagram for Lender Relations.

## High-level flow

```text
State Farm Employee (Internal)
        │
        │ Opens App
        ▼
Lender Relations (MRCS) React SPA
        │
        │ HTTPS REST (TLS 1.2+)
        │ Authorization: Bearer <User Bearer Entra B2E>
        ▼
AWS Edge (us-east-1)
  Route 53 → WAFv2 → CloudFront → S3 (SPA assets)
        │
        │ API calls (same Bearer)
        ▼
Lenders Internal Gateway (LIG)
  Spring Cloud Gateway on OpenShift
  Validates Entra Bearer → issues Downstream Bearer
        │
        ▼
Downstream Internal APIs
  Lender/MIS · Agreements · DVL · Address/SF Region · CV API WRITE
```

## 1. User & frontend

| Item | Detail |
|------|--------|
| End user | State Farm Employee (Internal) |
| App | Lender Relations (MRCS) React SPA |
| Protocol | HTTPS REST, TLS 1.2+ |
| Auth header | `Authorization: Bearer <User Bearer (Entra B2E)>` |

## 2. Identity — Microsoft Entra ID (B2E)

| Item | Detail |
|------|--------|
| Interactive user auth | Microsoft Entra ID — B2E |
| Token grant | OAuth 2.0 (client credentials / B2E auth pattern as used by app) |
| Common params | `B2EHeader`, `CLIENT_ID`, `GRAPH_SCOPE`, `TENANT` |
| API security | LIG validates User Bearer before routing downstream |

See [auth-b2e.md](./auth-b2e.md).

## 3. AWS account & region

| Item | Detail |
|------|--------|
| Account | AWS Account (Lender Relations — test OU) |
| Primary region | `us-east-1` |
| Failover | `us-west-2` failover disabled (per diagram) |

### Edge & content delivery

| Service | Role |
|---------|------|
| **Route 53** | Private hosted zone; A-record aliases |
| **AWS WAFv2** | Internal pattern; CloudFront scope |
| **CloudFront** | HTTPS, Geo: US, IPv6; TTL configured |
| **ACM** | TLSv1.2_2021, SNI-only certificate |
| **CloudFront OAI** | Origin Access Identity; SSL/TLS required to S3 |

### Storage

| Bucket | Purpose |
|--------|---------|
| **S3 (App)** | MRCS React SPA assets — AES256, versioning, object locking |
| **S3 (Logging)** | CloudFront logs — ~2-day expiry |

### Compliance

- **InfoSec Account** — central **KMS** for logging
- S3 replication for compliance

## 4. API Gateway layer — LIG

| Item | Detail |
|------|--------|
| Name | `lenders-internal-gateway` (LIG) |
| Stack | Spring Cloud Gateway |
| Hosting | OpenShift |
| Behavior | Validate Entra User Bearer → mint / attach **Downstream Bearer** for internal APIs |

## 5. Downstream service providers

| Provider | Example endpoints / notes |
|----------|---------------------------|
| **Lender / MIS** | `GET lender/(ad)/F`, `GET lender/P?name,addr` — search lender by account / name+address |
| **All – agreement info** | `POST agreements/` |
| **DVL – Data Virtualization** | `PnC-Policy/Retrieval/policies-amm`; uses `X-API-KEY` |
| **Address / SF Region (LOC)** | `POST login/address/Region` — address-based region lookup (third-party style call) |
| **CV API – WRITE** | `POST ling-spin-vea/record-change` — submit mortgage record change |

## 6. Topology summary (coding target)

When implementing in this repo, mirror this path:

1. React SPA (local `:3000` → later S3/CloudFront)
2. Auth helper attaches Entra B2E Bearer to every API request
3. BFF or direct calls to LIG-compatible gateway
4. Gateway routes to mock or real downstream providers:
   - Lender search (account / name-address)
   - Address → region
   - Policy retrieval (DVL)
   - Record-change write (CV API)

## Coding scaffold map

```text
projects/mrcs/
  frontend/     # React SPA (Change Details, lender search tabs)
  backend/      # Optional local gateway / mocks for LIG + third parties
  docs/         # Implementation notes linked to this architecture
```
