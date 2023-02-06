import axios from "axios";
import { getTokenFromLocalStorage } from "./storage";

const dataFetcherNotAuth = axios.create({
  baseURL: "http://localhost:8080/auth/",
  headers: {
    Accept: "application/json",
  },
});


const dataFetcher = axios.create({
  baseURL: "http://localhost:8080/api/",
  headers: {
    Accept: "application/json",
    Authorization: `Bearer ${getTokenFromLocalStorage()}`,
  },
});

dataFetcher.interceptors.response.use(
  (response) => {
    if (response.status === 401) {
      alert("You are not authorized or expired!");
      location.href = "/login";
    }
    return response;
  },
  (error) => {
    if (error.response && error.response.data) {
      return Promise.reject(error.response.data);
    }
    return Promise.reject(error.message);
  }
);

export { dataFetcher, dataFetcherNotAuth };
