/**
 * Extracted from MRCS screenshots: src/helpers/lenders/lenderDetailsTableHelpers.jsx
 * Project: mortgage-record-change-system
 */

export const getRowIndexFromSlot = (slotName) => {
  if (!slotName || typeof slotName !== "string") {
    return null;
  }

  const rowIndex = parseInt(slotName.split("-")[1], 10);
  return Number.isNaN(rowIndex) ? null : rowIndex;
};

export const shouldHideTable = (lenderData) => {
  return !lenderData;
};

export const hasNoResults = (lenderData) => {
  return lenderData?.nameAddress?.length === 0;
};

export const getTableHeaders = () => {
  return [
    { label: "Select", key: "selectOne" },
    { label: "Mortgage Clause", key: "mortgageClause" },
    { label: "Notes", key: "notes", justify: "center" },
    { label: "Account Number", key: "accountNumber" },
    { label: "Preferred Indicator", key: "preferredIndicator" },
    { label: "Preferred Account Number", key: "preferredAccountNumber" },
  ];
};

export const getTableCells = (nameAddress = []) => {
  return nameAddress.map((lender, index) => ({
    selectOne: { slot: `selectButton-${index}` },
    mortgageClause: { slot: `mortgageClause-${index}` },
    notes: lender.comments || "No notes available",
    accountNumber: lender.accountNumber,
    preferredIndicator: lender.preferredIndicator || "",
    preferredAccountNumber: lender.preferredAccountNumber || "",
  }));
};

export const getStreetAddressLines = (lender) => {
  return lender?.address?.streetAddressLines || [];
};
