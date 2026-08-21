/**
 * Extracted from MRCS screenshots: src/helpers/lenders/lenderSelectorHelpers.jsx
 * Project: mortgage-record-change-system
 */

/**
 * Returns true for change types that require Old and New lenders
 * instead of a single Current Lender.
 * @param {string} changeType
 * @returns {boolean}
 */
export const requiresOldAndNewLenders = (changeType) => {
  return changeType === "100" || changeType === "114";
};

/**
 * Whether the required lender selection(s) are present for the given change type.
 * Old/New change types (100, 114) require both an old and a new lender;
 * other change type requires a single current lender.
 * @param {string} changeType
 * @param {Object|null} currentLender
 * @param {Object|null} oldLender
 * @param {Object|null} newLender
 * @returns {boolean} True when the required lender(s) are selected.
 */
export const hasRequiredLenders = (
  changeType,
  currentLender,
  oldLender,
  newLender
) => {
  if (requiresOldAndNewLenders(changeType)) {
    return Boolean(oldLender) && Boolean(newLender);
  }
  return Boolean(currentLender);
};

/**
 * Maps a lender-selector title to the navigation-state slot key used to
 * persist selections across the lender search round-trip.
 * @param {string} lenderType - "Current Lender" | "Old Lender" | "New Lender"
 * @returns {"currentLender"|"oldLender"|"newLender"}
 */
export const getLenderSlotKey = (lenderType) => {
  switch (lenderType) {
    case "Old Lender":
      return "oldLender";
    case "New Lender":
      return "newLender";
    case "Current Lender":
    default:
      return "currentLender";
  }
};
