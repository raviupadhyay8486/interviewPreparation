/**
 * Extracted from MRCS screenshots: src/pages/SelectLenderDetails.jsx
 * Project: mortgage-record-change-system
 *
 * NOTE: Reconstructed from editor screenshots. Imports / a few middle lines
 * may need alignment with your local repo if anything was off-screen.
 */
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import SearchLender from "../components/SearchLender";
import LenderDetailsTable from "../components/LenderDetailsTable";
import LenderAddressDetailsTable from "../components/LenderAddressDetailsTable";
import {
  fetchLenderByAccountNumber,
  fetchLenderDetailsByAddress,
} from "../helpers/lenders/currentLenderFetchers";
import {
  transformApiResponse,
  formatLenderForNavigation,
  formatLenderForChangeScreen,
} from "../helpers/lenders/lenderDataHelpers";
import {
  updateField,
  validateAddressFieldsHelper,
  validateAccountFieldsHelper,
  getLenderSlotKey,
} from "../helpers/changeDetailsHelpers";

const SelectLenderDetails = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { lenderType, changeType, lenders } = location.state || {
    lenderType: "Current",
  };

  const formDefaults = {
    name: "",
    address: "",
    city: "",
    state: "",
    accountNumber: "",
    activeTabId: "SearchNameAddress",
  };

  const [formFields, setFormFields] = useState(formDefaults);
  const [accountSearchData, setAccountSearchData] = useState(null);
  const [nameAddressSearchData, setNameAddressSearchData] = useState(null);
  const [activeSearchType, setActiveSearchType] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const [submitDisabled, setSubmitDisabled] = useState(true);

  const updateFields = (e) => updateField(e, formFields, setFormFields);

  const validateAddressFields = (e) => {
    const isValid = validateAddressFieldsHelper(e, formFields, setFormFields);
    setSubmitDisabled(!isValid);
  };

  const validateAccountFields = (e) => {
    const isValid = validateAccountFieldsHelper(e, formFields, setFormFields);
    setSubmitDisabled(!isValid);
  };

  const handleLenderSelection = (rowIndex) => {
    const currentData =
      activeSearchType === "SearchAccountNo"
        ? accountSearchData
        : nameAddressSearchData;
    const lender = currentData?.nameAddress?.[rowIndex];
    if (!lender) return;

    const formattedLender = formatLenderForNavigation(lender);
    const changeScreenData = formatLenderForChangeScreen(formattedLender);
    const slotKey = getLenderSlotKey(lenderType);

    // Navigate back to Change Details with selected lender in state
    navigate("/mrcs/changeDetails", {
      state: {
        lenderType,
        changeType,
        lenders: {
          ...(lenders || {}),
          [slotKey]: changeScreenData,
        },
        selectedLender: changeScreenData,
      },
    });
  };

  const handleReset = () => {
    setFormFields(formDefaults);
    setAccountSearchData(null);
    setNameAddressSearchData(null);
    setActiveSearchType(null);
    setErrorMessage("");
    setSubmitDisabled(true);
  };

  const handleAccountSearch = async (searchCriteria) => {
    try {
      setNameAddressSearchData(null);
      setErrorMessage("");
      setSubmitDisabled(true);
      if (!searchCriteria.criteria?.accountNumber) {
        return;
      }

      setActiveSearchType("SearchAccountNo");
      const accountNumber = searchCriteria.criteria.accountNumber;
      const searchResult = await fetchLenderByAccountNumber(accountNumber);

      // Check for error response before transforming
      if (searchResult?.status === 404 || searchResult?.exception) {
        setAccountSearchData({ nameAddress: [] });
        setErrorMessage(searchResult?.messages?.[0] || "No records found.");
        return;
      }

      const transformedAccountData = transformApiResponse(searchResult);
      setAccountSearchData(transformedAccountData || { nameAddress: [] });
    } catch (error) {
      console.error("Account search failed:", error);
      setAccountSearchData({ nameAddress: [] });
      setErrorMessage("Something went wrong. Please try again.");
    }
  };

  const handleNameAddressSearch = async (searchCriteria) => {
    try {
      setAccountSearchData(null);
      setErrorMessage("");
      setActiveSearchType("SearchNameAddress");

      const searchResult = await fetchLenderDetailsByAddress(
        searchCriteria.criteria
      );

      // Check for error response before transforming
      if (searchResult?.status === 404 || searchResult?.exception) {
        setNameAddressSearchData({ nameAddress: [] });
        setErrorMessage(searchResult?.messages?.[0] || "No records found.");
        return;
      }

      const transformedNameAddressData = transformApiResponse(searchResult);
      setNameAddressSearchData(
        transformedNameAddressData || { nameAddress: [] }
      );
    } catch (error) {
      console.error("Name/Address search failed:", error);
      setNameAddressSearchData({ nameAddress: [] });
      setErrorMessage("Something went wrong. Please try again.");
      setSubmitDisabled(true);
    }
  };

  return (
    <div className="sf-row rowPad">
      <div className="sf-col sf-all-12">
        <div>
          <div className="sf-row rowPad">
            <div className="sf-col sf-heading-md">Select {lenderType}</div>
          </div>
        </div>

        <div className="sf-row">
          <div className="sf-col">
            <SearchLender
              formFields={formFields}
              updateFields={updateFields}
              setFormFields={setFormFields}
              handleAccountSearch={handleAccountSearch}
              handleNameAddressSearch={handleNameAddressSearch}
              handleReset={handleReset}
              validateAddressFields={validateAddressFields}
              validateAccountFields={validateAccountFields}
              submitDisabled={submitDisabled}
              errorMessage={errorMessage}
            />
          </div>
        </div>

        {accountSearchData && formFields.activeTabId === "SearchAccountNo" && (
          <div className="sf-row">
            <div className="sf-col">
              {accountSearchData.nameAddress.length > 0 && (
                <div className="sf-text-center sf-text-sm sf-text-muted sf-pb-1x">
                  Results from Account Number Search (
                  {accountSearchData.nameAddress?.length || 0} results)
                </div>
              )}
              <LenderDetailsTable
                lenderData={accountSearchData}
                onLenderSelect={handleLenderSelection}
              />
            </div>
          </div>
        )}

        {nameAddressSearchData &&
          formFields.activeTabId === "SearchNameAddress" && (
            <div className="sf-row">
              <div className="sf-col">
                {nameAddressSearchData.nameAddress.length > 0 && (
                  <div className="sf-text-center sf-text-sm sf-text-muted sf-pb-1x">
                    Results from Name/Address Search (
                    {nameAddressSearchData.nameAddress.length} result(s))
                  </div>
                )}
                <LenderAddressDetailsTable
                  lenderData={nameAddressSearchData}
                  handleLenderSelection={handleLenderSelection}
                />
              </div>
            </div>
          )}
      </div>
    </div>
  );
};

export default SelectLenderDetails;
