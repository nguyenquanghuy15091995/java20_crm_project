const tokenKey = "crmAccessToken";

export const getTokenFromLocalStorage = (): string => {
  return localStorage.getItem(tokenKey) || "";
};

export const removeTokenFromLocalStorage = (): void => {
  return localStorage.removeItem(tokenKey);
};

export const isHasAccessToken = (): boolean => {
  const token = getTokenFromLocalStorage();
  return token !== "";
};
