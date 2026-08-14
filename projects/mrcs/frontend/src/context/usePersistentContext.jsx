/**
 * Extracted from MRCS screenshots: src/context/usePersistentContext.jsx
 * Project: mortgage-record-change-system
 */
import { useState, useEffect } from "react";

const usePersistentContext = (
  context,
  defaultValue = context?._currentValue ?? {}
) => {
  const [state, setState] = useState(() => {
    const storedData = sessionStorage.getItem(context.displayName);

    if (storedData === null || storedData === "undefined") {
      return defaultValue;
    }

    try {
      return JSON.parse(storedData);
    } catch {
      return defaultValue;
    }
  });

  useEffect(() => {
    sessionStorage.setItem(context.displayName, JSON.stringify(state));
  }, [context.displayName, state]);

  return [state, setState];
};

export default usePersistentContext;
