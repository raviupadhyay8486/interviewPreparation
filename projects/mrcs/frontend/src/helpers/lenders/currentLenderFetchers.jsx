/**
 * Extracted from MRCS screenshots: src/helpers/lenders/currentLenderFetchers.jsx
 * Project: mortgage-record-change-system
 */
import { fetchAPI } from "@statefarm/lr-reusable-components-library";
// Local fallback if using extracted fetchClient:
// import { fetchAPI } from "../../lib/fetchClient";

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL;
const insertApiBaseUrl =
  import.meta.env.VITE_INSERT_API_BASE_URL || apiBaseUrl;

/**
 * Search lender by account number.
 * GET lender/{accountNumber}/F
 */
export const fetchLenderByAccountNumber = async (accountNumber) => {
  try {
    const endpoint = `lender/${accountNumber}/F`;
    const response = await fetchAPI(apiBaseUrl, "GET", endpoint, null);
    const lenderData = await response.json();
    return lenderData;
  } catch (error) {
    console.error(
      `Error fetching lender data for account number ${accountNumber}:`,
      error
    );
    throw error;
  }
};

/**
 * Search lender by name / address details.
 * GET lender/F?name=&address=&city=&state=
 */
export const fetchLenderDetailsByAddress = async (address) => {
  try {
    const endpoint = `lender/F`;
    const addressParams = new URLSearchParams();

    if (address.name && address.name.trim()) {
      addressParams.append("name", address.name.trim());
    } else {
      throw new Error("Name is required for address search");
    }

    if (address.address && address.address.trim()) {
      addressParams.append("address", address.address.trim());
    }
    if (address.city && address.city.trim()) {
      addressParams.append("city", address.city.trim());
    }
    if (address.state && address.state.trim()) {
      addressParams.append("state", address.state.trim());
    }

    const response = await fetchAPI(
      apiBaseUrl,
      "GET",
      endpoint,
      addressParams
    );
    const lenderData = await response.json();
    return lenderData;
  } catch (error) {
    console.error(
      `Error fetching lender data for address ${address.name || "unknown"}:`,
      error
    );
    throw error;
  }
};

/**
 * Submit mortgage record change.
 * POST linq-api/mrcs/record-change
 */
export const insertMortgageRecord = async (requestObject) => {
  const apiPath = `linq-api/mrcs/record-change`;
  try {
    const response = await fetchAPI(
      insertApiBaseUrl,
      "POST",
      apiPath,
      null,
      requestObject,
      null
    );
    const responseData = await response.json();

    if (response.status >= 400 || responseData?.status >= 400) {
      const messages = responseData?.message;
      const detail = Array.isArray(messages)
        ? messages.join("\n")
        : messages ||
          responseData?.error ||
          `Request failed with status ${
            response.status || responseData?.status
          }`;
      throw new Error(detail);
    }

    return responseData;
  } catch (error) {
    console.error("Error inserting mortgage record:", error);
    throw error;
  }
};
