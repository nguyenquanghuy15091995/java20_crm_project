import { FC, useMemo, ReactNode, HTMLAttributes } from "react";

export interface MenuGroupTitleProps extends HTMLAttributes<HTMLDivElement> {}

const MenuGroupTitle: FC<MenuGroupTitleProps> = ({ children, ...props }) => {
  return (
    <>
      <div className="px-4 pt-2 pb-4 text-slate-400 font-bold text-xl overflow-hidden truncate">
        {children}
      </div>
    </>
  );
};

export default MenuGroupTitle;
