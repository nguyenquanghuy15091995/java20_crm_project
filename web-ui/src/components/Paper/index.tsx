import { FC, ReactNode } from "react";

export interface PaperProps {
  children?: ReactNode;
}

const Paper: FC<PaperProps> = ({ children }) => {
  return (
    <>
      <div className="bg-white shadow-md rounded">{children}</div>
    </>
  );
};

export default Paper;
