import { AiOutlineUser, AiOutlineLayout, AiOutlineFundProjectionScreen } from "react-icons/ai";
import MenuItem from "./MenuItem";
import MenuGroupTitle from "./MenuGroupTitle";

const Sider = () => {
  return (
    <>
      <nav className="fixed left-0 top-0 h-full w-16 lg:w-64 xl:w-72 border-r bg-white pt-14">
        <ul className="pt-2">
          <li className="hidden lg:block">
            <MenuGroupTitle>
              Features
            </MenuGroupTitle>
          </li>
          <li>
            <MenuItem to="" Icon={AiOutlineLayout}>
              Dashboard
            </MenuItem>
          </li>
          <li>
            <MenuItem to="accounts" Icon={AiOutlineUser}>
              Accounts
            </MenuItem>
          </li>
          <li>
            <MenuItem to="projects" Icon={AiOutlineFundProjectionScreen}>
              Projects
            </MenuItem>
          </li>
        </ul>
      </nav>
    </>
  );
};

export default Sider;
