import { FC } from "react";
import { Outlet, Navigate } from "react-router-dom";
import { getTokenFromLocalStorage } from "../utils/storage";

export interface NonAuthWrapperProps {}

const NonAuthWrapper: FC<NonAuthWrapperProps> = () => {
  const token = getTokenFromLocalStorage();

  if (token === "") {
    return (
      <>
        <Outlet />
      </>
    );
  } else {
    return <Navigate to="/" replace />;
  }
};

export default NonAuthWrapper;
