import {} from "react-icons/ai";

import { FC } from "react";
import { AccountData } from "../../store/account";

export interface HeaderProps {
  profile?: AccountData;
}

const Header: FC<HeaderProps> = ({ profile }) => {
  return (
    <>
      <header className="h-12 lg:h-14 flex items-center justify-between bg-slate-900 fixed top-0 left-0 w-full z-30">
        <div className="w-16 lg:w-64 xl:w-72 font-bold text-xl lg:text-2xl flex items-center justify-center h-full border-r border-slate-700">
          <div className="text-center text-white">CRM</div>
        </div>
        <div className="px-4 flex gap-2">
          <div className="avatar">
            <div className="w-10 rounded-full">
              <span className="text-xl">{profile?.fullName?.charAt(0)}</span>
            </div>
          </div>
        </div>
      </header>
    </>
  );
};

export default Header;
