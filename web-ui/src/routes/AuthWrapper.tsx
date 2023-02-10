import { FC, ReactNode } from "react";
import { Navigate } from "react-router-dom";
import { getTokenFromLocalStorage } from "../utils/storage";

export interface AuthWrapperProps {
  children?: ReactNode;
}

const AuthWrapper: FC<AuthWrapperProps> = ({ children }) => {
  const token = getTokenFromLocalStorage();
  if (token === "") {
    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
};

export default AuthWrapper;
