import { Outlet } from "react-router-dom";
import HeaderWrapper from "./HeaderWrapper";
import Sider from "../../components/Sider";
import ViewTitle from "../../components/ViewTitle";

const Layout = () => {
  return (
    <>
      <div className="bg-slate-50">
        <HeaderWrapper />
        <Sider />
        <main className="min-h-screen pl-16 lg:pl-64 xl:pl-72 pt-14">
          <ViewTitle />
          <div>
            <Outlet />
          </div>
        </main>
      </div>
    </>
  );
};

export default Layout;
