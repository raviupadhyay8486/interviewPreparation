/**
 * Extracted from MRCS screenshots: src/context/AliasContext.jsx
 * Project: mortgage-record-change-system
 */
import { createContext } from "react";
import usePersistentContext from "./usePersistentContext";

const AliasContext = createContext();
AliasContext.displayName = "AliasContext";

const AliasProvider = ({ children }) => {
  const [aliasId, setAliasId] = usePersistentContext(AliasContext, "");

  return (
    <AliasContext.Provider value={{ aliasId, setAliasId }}>
      {children}
    </AliasContext.Provider>
  );
};

export { AliasContext, AliasProvider };
