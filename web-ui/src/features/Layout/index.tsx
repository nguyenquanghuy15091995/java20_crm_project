import { Outlet } from "react-router-dom";
import HeaderWrapper from "./HeaderWrapper";
import Sider from "../../components/Sider";

const Layout = () => {
  return (
    <>
      <div className="bg-slate-50">
        <HeaderWrapper />
        <Sider />
        <main className="h-screen pl-16 lg:pl-64 xl:pl-72 pt-14">
          <div className="h-full">
            <Outlet />
          </div>
        </main>
      </div>
    </>
  );
};

export default Layout;
