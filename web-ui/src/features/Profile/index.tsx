import { useState } from "react";
import { useForm } from "react-hook-form";
import Paper from "../../components/Paper";
import TextFieldWrapper from "../../components/TextFieldWrapper";

const Profile = () => {
  const { register, handleSubmit } = useForm();
  return (
    <>
      <Paper>
        <form className="p-4">
          <div>
            <TextFieldWrapper label="Full Name" className="max-w-md">
              <input
                type="text"
                placeholder="Type here"
                className="input input-bordered w-full"
                {...register("fullName")}
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Email" className="max-w-md">
              <input
                type="text"
                placeholder="Type here"
                className="input input-bordered w-full"
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Address" className="max-w-md">
              <input
                type="text"
                placeholder="Type here"
                className="input input-bordered w-full"
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Phone number" className="max-w-md">
              <input
                type="text"
                placeholder="Type here"
                className="input input-bordered w-full"
              />
            </TextFieldWrapper>
          </div>
          <div>
            <button type="button" className="btn btn-primary">
              Edit
            </button>
          </div>
        </form>
      </Paper>
    </>
  );
};

export default Profile;
