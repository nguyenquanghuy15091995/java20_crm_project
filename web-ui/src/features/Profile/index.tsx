import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { useProfileStore, AccountData } from "../../store/account";
import { dataFetcher } from "../../utils/data-fetcher";
import Paper from "../../components/Paper";
import TextFieldWrapper from "../../components/TextFieldWrapper";

const Profile = () => {
  const profile = useProfileStore(state => state.profile);
  const setProfile = useProfileStore(state => state.setProfile);
  const { register, handleSubmit, setValue } = useForm<AccountData>();
  const [editable, setEditable] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(false);

  const onSubmit = async (data: AccountData) => {
    try {
      setLoading(true);
      const response = await dataFetcher.put("accounts/once", { ...data, email: profile.email });
      const resData = response.data as AccountData;
      if (resData && resData.email) {
        setProfile(resData);
        setEditable(false);
        toast.success("Update successful!");
      } else {
        toast.error("Update failed!");
      }
    } catch (error) {
      toast.error("Update failed!");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    if (profile) {
      setValue("fullName", profile.fullName);
      setValue("email", profile.email);
      setValue("address", profile.address);
      setValue("phoneNumber", profile.phoneNumber);
    }
  }, [profile]);

  return (
    <>
      <Paper>
        <form className="p-4" onSubmit={handleSubmit(onSubmit)}>
          <div>
            <TextFieldWrapper label="Email" className="max-w-md">
              <input
                type="text"
                placeholder="Type Email"
                className="input input-bordered w-full"
                disabled
                {...register("email", { required: true })}
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Full Name" className="max-w-md">
              <input
                type="text"
                placeholder="Type Full Name"
                className="input input-bordered w-full read-only:border-0 read-only:focus-within:outline-none"
                readOnly={!editable || loading}
                {...register("fullName", { required: true })}
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Address" className="max-w-md">
              <input
                type="text"
                placeholder="Type Address"
                className="input input-bordered w-full read-only:border-0 read-only:focus-within:outline-none"
                readOnly={!editable || loading}
                {...register("address", { required: true })}
              />
            </TextFieldWrapper>
          </div>
          <div>
            <TextFieldWrapper label="Phone number" className="max-w-md">
              <input
                type="text"
                placeholder="Type phone number"
                className="input input-bordered w-full read-only:border-0 read-only:focus-within:outline-none"
                readOnly={!editable || loading}
                {...register("phoneNumber", { required: true })}
              />
            </TextFieldWrapper>
          </div>
          <div className="pt-4">
            {editable ? <>
              <div className="flex justify-between items-center max-w-md">
                <button type="button" className="btn btn-outline px-7" onClick={() => setEditable(false)} disabled={loading}>
                  Cancel
                </button>
                <button type={loading ? "button" : "submit"} className={`btn btn-primary px-7 ${loading ? "loading" : ""}`}>
                  Save
                </button>
              </div>
            </> : <button type="button" className="btn btn-primary px-7" onClick={() => setEditable(true)} disabled={loading}>
              Edit
            </button>}
          </div>
        </form>
      </Paper>
    </>
  );
};

export default Profile;
