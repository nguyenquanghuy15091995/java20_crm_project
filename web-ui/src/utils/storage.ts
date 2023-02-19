const tokenKey = "crmAccessToken";
const profileKey = "crmProfileKey";

export const setTokenToLocalStorage = (token: string) => {
  localStorage.setItem(tokenKey, token);
};

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

export const setProfileToLocalStorage = (data: string) => {
  return localStorage.setItem(profileKey, data);
};

export const getProfileFromLocalStorage = (): string => {
  return localStorage.getItem(profileKey) || "";
};

export const removeProfileFromLocalStorage = (): void => {
  return localStorage.removeItem(profileKey);
};

