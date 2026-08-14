/**
 * Extracted from MRCS screenshots: src/helpers/lenders/lenderDataHelpers.jsx
 * Project: mortgage-record-change-system
 */

/**
 * Normalize lender API response into { nameAddress: [...] } shape used by tables.
 */
export const transformApiResponse = (apiResponse) => {
  if (!apiResponse) return null;

  if (
    apiResponse.exception ||
    apiResponse.status === 404 ||
    apiResponse.messages
  ) {
    console.warn(
      "Error response detected in transformApiResponse:",
      apiResponse
    );
    return null;
  }

  // Already in expected shape
  if (apiResponse.nameAddress && Array.isArray(apiResponse.nameAddress)) {
    return apiResponse;
  }

  // Single lender object
  if (typeof apiResponse === "object" && !Array.isArray(apiResponse)) {
    const transformed = {
      nameAddress: [
        {
          accountNumber: apiResponse.accountNumber || "N/A",
          accountStatus: apiResponse.accountStatus || "ACTV",
          preferredIndicator: apiResponse.preferredIndicator || " ",
          name: apiResponse.name || "N/A",
          address: {
            streetAddressLines: apiResponse.address?.streetAddressLines || [
              apiResponse.address || "N/A",
            ],
          },
          city: apiResponse.city || "N/A",
          stateProvinceCode: apiResponse.stateProvinceCode || "N/A",
          postalCode: apiResponse.postalCode || "N/A",
          country: apiResponse.country || "USA",
          notes: apiResponse.comments || "No notes available",
        },
      ],
    };
    return transformed;
  }

  // Array of lenders
  if (Array.isArray(apiResponse)) {
    const transformed = {
      nameAddress: apiResponse.map((item) => ({
        accountNumber: item.accountNumber || "N/A",
        accountStatus: item.accountStatus || "ACTV",
        preferredIndicator: item.preferredIndicator || " ",
        name: item.name || "N/A",
        address: {
          streetAddressLines: item.address?.streetAddressLines || [
            item.address || "N/A",
          ],
        },
        city: item.city || "N/A",
        stateProvinceCode: item.stateProvinceCode || "N/A",
        postalCode: item.postalCode || "N/A",
        country: item.country || "USA",
        notes: item.comments || "No Notes available",
      })),
    };
    return transformed;
  }

  return null;
};

/**
 * Format a lender row for navigation / intermediate use.
 */
export const formatLenderForNavigation = (lender) => {
  if (!lender) return null;

  return {
    name: lender.name,
    address: {
      line1: lender.address?.streetAddressLines?.[0] || "N/A",
      line2: lender.address?.streetAddressLines?.[1] || null,
      city: lender.city,
      state: lender.stateProvinceCode,
      zip: lender.postalCode,
    },
    accountNumber: lender.accountNumber,
  };
};

/**
 * Format lender for Change Details screen state.
 */
export const formatLenderForChangeScreen = (formattedLender) => {
  if (!formattedLender) return null;

  return {
    action: "Update",
    name: formattedLender.name,
    addressLine1: formattedLender.address.line1,
    addressLine2: formattedLender.address.line2,
    city: formattedLender.address.city,
    state: formattedLender.address.state,
    zip: formattedLender.address.zip,
    accountNumber: formattedLender.accountNumber,
  };
};
