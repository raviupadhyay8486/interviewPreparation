/**
 * Extracted from MRCS screenshots: src/data/JS/policySearchErrorMessages.js
 * Project: mortgage-record-change-system
 *
 * Note: PLCC contact strings were truncated at the right edge of several photos.
 * Reconstructed using the visible phone (1-844-275-7522) and the repeated
 * "Personal Lines Call Center (PLCC) ... or Business ..." pattern.
 */

export const POLICY_NUMBER_NOT_FOUND_ERROR_MESSAGE =
  "The Policy Number you are looking for could not be located with the information you have given us. Please contact the Personal Lines Call Center (PLCC) 1-844-275-7522 or Business Insurance Support.";

export const ZIP_CODE_REGION_NOT_FOUND_ERROR_MESSAGE =
  "The regional code for the ZIP code you entered could not be located. Please contact the Personal Lines Call Center (PLCC) 1-844-275-7522 or Business Insurance Support.";

export const POLICY_DETAILS_NOT_FOUND_ERROR_MESSAGE =
  "The policy details for the Policy Number you entered could not be retrieved. Please contact the Personal Lines Call Center (PLCC) 1-844-275-7522 or Business Insurance Support.";

export const getPolicyNumberSearchErrorMessage = (rowsOrPolicyNumber) => {
  const rows = Array.isArray(rowsOrPolicyNumber)
    ? rowsOrPolicyNumber
    : [{ policyNumber: rowsOrPolicyNumber }];

  const details = rows
    .map((row) => String(row.policyNumber))
    .filter(Boolean)
    .map((policyNumberValue) => `Policy Number: ${policyNumberValue}`)
    .join(", ");

  return details
    ? `${POLICY_NUMBER_NOT_FOUND_ERROR_MESSAGE} (${details})`
    : POLICY_NUMBER_NOT_FOUND_ERROR_MESSAGE;
};

export const getZipCodeRegionSearchErrorMessage = (rowsOrZipCode) => {
  const rows = Array.isArray(rowsOrZipCode)
    ? rowsOrZipCode
    : [{ zipCode: rowsOrZipCode }];

  const details = rows
    .map((row) => String(row.zipCode))
    .filter(Boolean)
    .map((zipCodeValue) => `ZIP Code: ${zipCodeValue}`)
    .join(", ");

  return details
    ? `${ZIP_CODE_REGION_NOT_FOUND_ERROR_MESSAGE} (${details})`
    : ZIP_CODE_REGION_NOT_FOUND_ERROR_MESSAGE;
};

export const getPolicyDetailsErrorMessage = (rowsOrPolicyNumber) => {
  const rows = Array.isArray(rowsOrPolicyNumber)
    ? rowsOrPolicyNumber
    : [{ policyNumber: rowsOrPolicyNumber }];

  const details = rows
    .map((row) => String(row.policyNumber))
    .filter(Boolean)
    .map((policyNumberValue) => `Policy Number: ${policyNumberValue}`)
    .join(", ");

  return details
    ? `${POLICY_DETAILS_NOT_FOUND_ERROR_MESSAGE} (${details})`
    : POLICY_DETAILS_NOT_FOUND_ERROR_MESSAGE;
};
