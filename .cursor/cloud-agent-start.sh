#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT/demos/react-hello"

if [[ ! -d node_modules ]]; then
  npm ci
fi

echo "React demo dependencies are ready."
