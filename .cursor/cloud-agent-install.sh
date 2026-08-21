#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

echo "==> Building Java demo"
(cd demos/java-hello && mvn -q -DskipTests package)

echo "==> Building Spring Boot learning project"
(cd projects/java8-springboot-learn && mvn -q -DskipTests package)

echo "==> Installing React demo dependencies"
(cd demos/react-hello && npm ci)

echo "==> Initializing Terraform demo"
(cd demos/terraform-sample && terraform init -backend=false -input=false)

echo "==> Verifying toolchains"
java -version
mvn -version
node --version
npm --version
terraform version
aws --version

echo "Install complete."
