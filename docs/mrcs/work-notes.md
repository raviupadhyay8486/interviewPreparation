# MRCS — Work Notes (Jan 2025+)

Context for resume updates and future implementation in this repository.

## Project

- **Name:** Mortgage Record Change System (MRCS)
- **Division:** Lender Relations (State Farm)
- **Version:** Newest version of an existing product line
- **Approach:** Study older version → understand code & flows → implement in new version

## Contributions to capture

| Area | Detail |
|------|--------|
| React.js | SPA work: Change Details, lender search tabs, validation UX |
| B2E header auth | Microsoft Entra ID B2E Bearer on Authorization header for REST |
| Search lender by account number | 10-character alphanumeric search UI + API wiring |
| Address-based third party | Call Address/SF Region (and lender-by-address) integrations |
| Project topology | Document/implement SPA → edge → LIG → downstream providers |

## Narrative (resume-ready)

Analyzed the legacy Mortgage Record Change System to map UI flows, validation rules, and API contracts, then reimplemented them in the newest MRCS React.js application. Implemented Entra ID B2E authentication headers for secured REST calls through the Lenders Internal Gateway, built Search Lender by Account Number and Name/Address flows, and integrated address-based third-party region/lender lookups as part of the end-to-end project topology.

## Interview talking points

1. **Legacy → modern:** How you reverse-engineered the old app without breaking business rules.  
2. **Full stack seam:** React SPA + gateway auth + multiple downstream providers.  
3. **Auth:** Why Bearer/B2E at the SPA edge and token exchange at LIG matter.  
4. **Validation:** Exact field rules (account 10 alnum; name 3–90; address no commas; etc.).  
5. **Topology:** CloudFront/S3 for SPA; LIG on OpenShift; AWS edge controls.

## Coding backlog (suggested order)

1. Scaffold React app + App shell (header, routes)  
2. Change Details form + policy row + validation  
3. Lender Details page with two search tabs  
4. B2E auth interceptor (mock token in local/dev)  
5. Mock LIG + lender + address region APIs  
6. Wire Search + Select lender back into Change Details  
7. Submit → record-change mock  
8. Harden errors, loading states, empty states  

See implementation home: [`../../projects/mrcs/`](../../projects/mrcs/)
