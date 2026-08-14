/**
 * Stub helpers referenced by SelectLenderDetails (full file not fully visible in screenshots).
 * Align with your local changeDetailsHelpers.jsx when available.
 */

export const updateField = (e, formFields, setFormFields) => {
  const { name, value } = e?.target || {};
  if (!name) return;
  setFormFields({ ...formFields, [name]: value });
};

export const validateAddressFieldsHelper = (e, formFields, setFormFields) => {
  // Name 3-90, address 2-60 no commas, city letters 2-18, state required
  const nameOk = (formFields.name || "").trim().length >= 3;
  const address = formFields.address || "";
  const addressOk =
    address.length >= 2 && address.length <= 60 && !address.includes(",");
  const cityOk = /^[A-Za-z]{2,18}$/.test((formFields.city || "").trim());
  const stateOk = !!(formFields.state || "").trim();
  return nameOk && addressOk && cityOk && stateOk;
};

export const validateAccountFieldsHelper = (e, formFields, setFormFields) => {
  return /^[A-Za-z0-9]{10}$/.test(formFields.accountNumber || "");
};

export const getLenderSlotKey = (lenderType) => {
  if (!lenderType) return "currentLender";
  const key = String(lenderType).toLowerCase();
  if (key.includes("current")) return "currentLender";
  if (key.includes("new")) return "newLender";
  return "currentLender";
};
