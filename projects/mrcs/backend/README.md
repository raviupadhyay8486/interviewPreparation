# MRCS Backend / Local LIG Mocks

Local stand-in for:

- Lenders Internal Gateway (token check stub)
- Lender / MIS search
- Address / SF Region (`POST /login/address/Region`)
- CV API write (`POST /ling-spin-vea/record-change`)

## Planned endpoints

See `mocks/openapi-sketch.yaml` and `../../../../docs/mrcs/api-integrations.md`.

## Next implementation step

Choose either:

1. **Node/Express** mock server (fast for interview demos), or  
2. **Spring Cloud Gateway** + stub controllers (closer to real LIG)

Start with Express mocks unless you need Java interview depth.
