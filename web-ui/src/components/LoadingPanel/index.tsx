import { FC } from "react";
import { AiOutlineLoading3Quarters } from "react-icons/ai";

export interface LoadingPanelProps {}

const LoadingPanel: FC<LoadingPanelProps> = () => {
  return (
    <>
      <div className="flex justify-center items-center h-full w-full bg-white bg-opacity-75">
        <AiOutlineLoading3Quarters size={36} className="text-primary animate-spin" />
      </div>
    </>
  );
};

export default LoadingPanel;
