import { FC, useMemo } from "react";
import { Link, LinkProps } from "react-router-dom";
import { IconType } from "react-icons";

export interface MenuItemProps extends LinkProps {
  Icon?: IconType;
}

const MenuItem: FC<MenuItemProps> = ({ Icon, children, to, ...props }) => {
  const isActive = useMemo<boolean>(() => {
    const path =
      location.pathname.length > 1 ? location.pathname.substring(1) : "";
    const comparePath =
      to.toString().length > 1 && to.toString()[0] === "/"
        ? to.toString().substring(1)
        : to.toString();
    const arrPath = path.split("/");
    const arrComparePath = comparePath.split("/");
    return arrPath[0] === arrComparePath[0];
  }, [to]);

  return (
    <>
    <div className="px-1 py-0.5 lg:px-2">
      <Link
        to={to}
        className={`flex justify-center items-center rounded-md py-2 lg:justify-start lg:py-3 lg:px-4 transition-colors duration-300 ${
          isActive ? "bg-primary text-white" : "lg:hover:bg-slate-200"
        }`}
        {...props}
      >
        <span className="lg:pr-2">
          {Icon && <Icon size={20} className="text-current" />}
        </span>
        <span className="hidden lg:inline-block font-semibold">{children}</span>
      </Link>
      </div>
    </>
  );
};

export default MenuItem;
