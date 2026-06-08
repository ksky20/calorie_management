export let csrfToken = null;

//CSRFトークンをset
export const setCsrfToken = (token) => {
    csrfToken = token;
};

export const api = async (url, method = "GET", body = null) => {
  const options = {
    method,
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
      "X-CSRF-TOKEN": csrfToken  // ← headersの中に移動
    },
  };
  console.log("csrfToken:", csrfToken); //確認
  if (body) options.body = JSON.stringify(body);
  return fetch(`http://localhost:8080${url}`, options);
};