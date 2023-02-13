import { useMemo } from "react";
import { useLocation } from "react-router-dom";
import { ROUTE_COLLECTION } from "../../routes";

const ViewTitle = () => {
  const location = useLocation();
  const title = useMemo<string>(() => {
    const path =
      location.pathname.length > 1 ? location.pathname.substring(1) : "";
      const arrPath = path.split("/");
      const route = Object.values(ROUTE_COLLECTION).find(item => item.path === arrPath[0]);
    return route?.label || "Dashboard";
  }, [location.pathname]);
  return (
    <>
      <h1 id="view-title" className="px-5 pt-5 text-2xl lg:text-3xl font-bold">
        {title}
      </h1>
    </>
  );
};

export default ViewTitle;
