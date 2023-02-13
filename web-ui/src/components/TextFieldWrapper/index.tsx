import { FC, HTMLAttributes, ReactNode } from "react";

export interface TextFieldWrapperProps {
  children?: ReactNode;
  label?: ReactNode;
  errorMessage?: ReactNode;
  wrapperProps?: HTMLAttributes<HTMLDivElement>;
  className?: string;
}

const TextFieldWrapper: FC<TextFieldWrapperProps> = ({
  children,
  label,
  errorMessage,
  wrapperProps,
  className,
}) => {
  return (
    <>
      <div
        className={`form-control w-full${className ? ` ${className}` : ""}`}
        {...wrapperProps}
      >
        <label className="label">
          <span className="label-text">{label}</span>
        </label>
        {children}
        <label className="label">
          {errorMessage && (
            <span className="label-text-alt text-error">{errorMessage}</span>
          )}
        </label>
      </div>
    </>
  );
};

export default TextFieldWrapper;
