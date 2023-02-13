import axios from "axios";
import { getTokenFromLocalStorage } from "./storage";

const dataFetcherNotAuth = axios.create({
  baseURL: "http://localhost:8080/auth/",
  headers: {
    Accept: "application/json",
    // "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8;",
  },
});

const dataFetcher = axios.create({
  baseURL: "http://localhost:8080/api/",
  headers: {
    Accept: "application/json",
    Authorization: `Bearer ${getTokenFromLocalStorage()}`,
  },
  params: {
    Authorization: `Bearer ${getTokenFromLocalStorage()}`,
  },
});

dataFetcherNotAuth.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    if (error.response && error.response.data) {
      return Promise.reject(error.response.data);
    }
    return Promise.reject(error.message);
  }
);

dataFetcher.interceptors.request.use(
  (request) => {
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);

dataFetcher.interceptors.response.use(
  (response) => {
    if (response.status === 401) {
      alert("You are not authorized or expired!");
      location.href = "/login";
    }
    return response.data;
  },
  (error) => {
    if (error.response && error.response.data) {
      return Promise.reject(error.response.data);
    }
    return Promise.reject(error.message);
  }
);

export { dataFetcher, dataFetcherNotAuth };
