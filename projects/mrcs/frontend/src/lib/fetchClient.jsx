/**
 * Extracted from MRCS screenshots:
 * node_modules/@statefarm/lr-reusable-components-library/src/helpers/fetchClient.jsx
 *
 * B2E / Bearer token aware fetch helpers used by lender APIs.
 */

let accessTokenProvider = null;

const setAccessTokenProvider = (provider) => {
  accessTokenProvider = typeof provider === "function" ? provider : null;
};

const hasAuthorizationHeader = (headers = {}) => {
  return Object.keys(headers).some(
    (key) => key.toLowerCase() === "authorization"
  );
};

const getBearerAuthorizationHeader = async () => {
  if (!accessTokenProvider) {
    return null;
  }

  try {
    const token = await accessTokenProvider();
    return token ? { Authorization: `Bearer ${token}` } : null;
  } catch (error) {
    console.error("Error getting access token:", error);
    return null;
  }
};

const fetchAPI = async (
  apiBaseUrl,
  method,
  apiCall,
  searchParams,
  body,
  headers,
  isSOAP
) => {
  const { options } = await configureApi(method, body, headers, isSOAP);

  return fetch(
    `${apiBaseUrl}/${apiCall}${
      searchParams && searchParams !== null ? `?${searchParams}` : ""
    }`,
    options
  );
};

const fetchAPIs = async (
  apiBaseUrl,
  method,
  apiCalls,
  searchParams,
  body,
  headers,
  isSOAP
) => {
  const { options } = await configureApi(method, body, headers, isSOAP);
  const urls = [];

  for (let apiCall of apiCalls) {
    urls.push(
      `${apiBaseUrl}/${apiCall}${
        searchParams && searchParams !== null ? `?${searchParams}` : ""
      }`
    );
  }

  try {
    const responses = await Promise.all(urls.map((uri) => fetch(uri, options)));
    return await Promise.all(responses.map((response) => response.json()));
  } catch (error) {
    console.error("Error:", error);
  }
};

const deleteEmptyKeys = (obj) => {
  for (let key in obj) {
    obj.hasOwnProperty(key) &&
      (obj[key] === "" ||
        obj[key] === null ||
        obj[key] === undefined ||
        (typeof obj[key] === "object" &&
          Object.keys(obj[key]).length === 0)) &&
      delete obj[key];
  }
};

async function configureApi(method, body, headers, isSOAP) {
  let requestHeaders = {
    ...(headers || {}),
  };

  // Attach B2E Bearer token if Authorization not already provided
  if (!hasAuthorizationHeader(requestHeaders)) {
    const authHeader = await getBearerAuthorizationHeader();
    if (authHeader) {
      requestHeaders = {
        ...requestHeaders,
        ...authHeader,
      };
    }
  }

  const options = {
    method: method.toUpperCase(),
    headers: isSOAP
      ? {
          "Content-Type": "text/xml; charset=utf-8",
          ...requestHeaders,
        }
      : {
          "Content-Type": "application/json",
          ...requestHeaders,
        },
  };

  if (body) {
    options.body = isSOAP ? body : JSON.stringify(body);
  }

  return { options };
}

export { fetchAPI, fetchAPIs, deleteEmptyKeys, setAccessTokenProvider };
