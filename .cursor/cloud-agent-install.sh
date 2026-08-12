#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

echo "==> Building Java demo"
(cd demos/java-hello && mvn -q -DskipTests package)

echo "==> Installing React demo dependencies"
(cd demos/react-hello && npm ci)

echo "==> Initializing Terraform demo"
(cd demos/terraform-sample && terraform init -backend=false -input=false)

echo "==> Verifying toolchains"
java -version
mvn -version | head -1
node --version
npm --version
terraform version | head -1
aws --version

echo "Install complete."
