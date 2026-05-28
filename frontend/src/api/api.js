export const api = async (url, method = "GET", body = null) => {
  const options = {
    method,
    credentials: "include",
    headers: { "Content-Type": "application/json" },
  };
  if (body) options.body = JSON.stringify(body);
  return fetch(`http://localhost:8080${url}`, options);
};