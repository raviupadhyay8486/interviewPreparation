# MRCS — Microsoft Entra ID B2E Authentication

## Goal

Secure all MRCS React SPA → backend/gateway REST calls with an **Entra B2E User Bearer** token in the **Authorization** header (referred to in the project as the **B2E header** pattern).

## Actors

| Actor | Role |
|-------|------|
| State Farm Employee | Interactive user |
| Microsoft Entra ID — B2E | Issues Bearer token |
| MRCS React SPA | Requests token; sends on every API call |
| LIG (Spring Cloud Gateway) | Validates User Bearer; issues Downstream Bearer |
| Downstream APIs | Accept LIG Downstream Bearer (and/or API keys where required) |

## Request shape (SPA → LIG / APIs)

```http
GET /api/... HTTP/1.1
Host: <lig-or-api-host>
Authorization: Bearer <entra-b2e-user-token>
Content-Type: application/json
```

TLS: **1.2+**

## Configuration knobs (from architecture)

These names appear in the MRCS topology; treat as environment-driven config in code:

| Key | Purpose |
|-----|---------|
| `CLIENT_ID` | Entra application / client id |
| `TENANT` | Entra tenant |
| `GRAPH_SCOPE` | Token scope(s) |
| `B2EHeader` | Header/contract name used by B2E integration |

> Do **not** commit real secrets. Use `.env.local` / vault placeholders only.

## Sequence

```text
1. User opens MRCS React SPA
2. SPA performs B2E auth (OAuth 2.0) → receives User Bearer
3. SPA stores token in memory/session (implementation choice)
4. Every REST call adds: Authorization: Bearer <token>
5. LIG validates Entra token
6. LIG attaches Downstream Bearer for internal providers
7. Downstream APIs authorize and process business logic
```

## Frontend implementation notes (future coding)

Suggested React responsibilities:

- Auth bootstrap / silent renew (as allowed by B2E)
- Axios/fetch interceptor that injects `Authorization` header
- 401 handling → re-auth / friendly error
- Never log raw tokens

Example interceptor sketch:

```ts
// conceptual — implement under projects/mrcs/frontend
api.interceptors.request.use((config) => {
  const token = getB2EAccessToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
```

## Gateway notes

LIG is the trust boundary:

- Validates Entra User Bearer
- Does **not** forward raw user token blindly to all providers unless required
- Issues a **Downstream Bearer** for service-to-service calls
- Some providers (e.g. DVL) also require `X-API-KEY`
