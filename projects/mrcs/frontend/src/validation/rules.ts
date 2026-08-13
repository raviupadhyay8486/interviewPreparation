/**
 * MRCS field validation rules captured from the newest UI (Jan 2025+).
 * Use these constants when implementing React forms and backend request validation.
 */

export const policyRules = {
  policyNumber: { required: true, maxLength: 9 },
  propertyZip: { required: true, maxLength: 9 },
  newLoanNumber: { maxLength: 10 },
} as const;

export const lenderAccountSearchRules = {
  accountNumber: {
    required: true,
    exactLength: 10,
    pattern: /^[A-Za-z0-9]{10}$/,
    message: "Account Number must be exactly 10 alphanumeric characters.",
  },
} as const;

export const lenderNameAddressSearchRules = {
  name: {
    required: true,
    minLength: 3,
    maxLength: 90,
    message: "Required Name must be between 3 and 90 characters.",
  },
  address: {
    minLength: 2,
    maxLength: 60,
    // Address cannot contain commas
    pattern: /^[^,]{2,60}$/,
    message:
      "Address must be between 2 and 60 characters and cannot contain commas.",
  },
  city: {
    minLength: 2,
    maxLength: 18,
    pattern: /^[A-Za-z]{2,18}$/,
    message: "City must contain only letters and be between 2-18 characters.",
  },
  state: {
    required: true,
  },
} as const;

export function isValidAccountNumber(value: string): boolean {
  return lenderAccountSearchRules.accountNumber.pattern.test(value);
}

export function isValidLenderName(value: string): boolean {
  const len = value.trim().length;
  return (
    len >= lenderNameAddressSearchRules.name.minLength &&
    len <= lenderNameAddressSearchRules.name.maxLength
  );
}

export function isValidAddress(value: string): boolean {
  return lenderNameAddressSearchRules.address.pattern.test(value);
}

export function isValidCity(value: string): boolean {
  return lenderNameAddressSearchRules.city.pattern.test(value);
}
