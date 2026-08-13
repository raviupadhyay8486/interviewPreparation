/**
 * Conceptual B2E auth helpers for local/dev.
 * Replace getB2EAccessToken() with real Entra MSAL / B2E integration later.
 */

const TOKEN_KEY = "mrcs_b2e_access_token";

export function getB2EAccessToken(): string | null {
  if (typeof window === "undefined") return null;
  return window.sessionStorage.getItem(TOKEN_KEY);
}

/** Dev-only: store a mock bearer for interceptor testing. */
export function setMockB2EAccessToken(token: string): void {
  window.sessionStorage.setItem(TOKEN_KEY, token);
}

export function clearB2EAccessToken(): void {
  window.sessionStorage.removeItem(TOKEN_KEY);
}

export function buildAuthHeaders(
  token: string | null = getB2EAccessToken()
): Record<string, string> {
  if (!token) return {};
  return { Authorization: `Bearer ${token}` };
}
