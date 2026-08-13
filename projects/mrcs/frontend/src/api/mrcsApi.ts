/**
 * API client stubs — wire to local LIG mocks at REACT_APP_API_BASE_URL.
 */

import { buildAuthHeaders } from "../auth/b2eAuth";

const baseUrl = () =>
  (typeof process !== "undefined" &&
    process.env?.REACT_APP_API_BASE_URL) ||
  "http://localhost:8080";

async function apiFetch<T>(path: string, init: RequestInit = {}): Promise<T> {
  const res = await fetch(`${baseUrl()}${path}`, {
    ...init,
    headers: {
      "Content-Type": "application/json",
      ...buildAuthHeaders(),
      ...(init.headers || {}),
    },
  });
  if (!res.ok) {
    throw new Error(`API ${path} failed: ${res.status}`);
  }
  return res.json() as Promise<T>;
}

export type LenderResult = {
  accountNumber: string;
  lenderName: string;
  address?: string;
  city?: string;
  state?: string;
};

export function searchLenderByAccount(accountNumber: string) {
  return apiFetch<LenderResult[]>(
    `/lender/account/${encodeURIComponent(accountNumber)}`
  );
}

export function searchLenderByNameAddress(params: {
  name: string;
  address?: string;
  city?: string;
  state?: string;
}) {
  const q = new URLSearchParams();
  Object.entries(params).forEach(([k, v]) => {
    if (v) q.set(k, v);
  });
  return apiFetch<LenderResult[]>(`/lender/search?${q.toString()}`);
}

export function resolveAddressRegion(body: {
  address: string;
  city?: string;
  state?: string;
  zip?: string;
}) {
  return apiFetch<{ region: string; sfRegionCode?: string }>(
    "/login/address/Region",
    { method: "POST", body: JSON.stringify(body) }
  );
}

export function submitRecordChange(payload: unknown) {
  return apiFetch<{ status: string }>("/ling-spin-vea/record-change", {
    method: "POST",
    body: JSON.stringify(payload),
  });
}
