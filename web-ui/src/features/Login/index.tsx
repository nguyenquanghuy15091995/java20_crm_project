import { FC, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { dataFetcherNotAuth } from "../../utils/data-fetcher";
import { setTokenToLocalStorage } from "../../utils/storage";
import { useAccountStore } from "../../store/account";

export interface LoginForm {
  email: string;
  password: string;
}

export interface LoginDataResponse {
  data: string;
  statusCode: number;
}

export interface LoginProps {}

const Login: FC<LoginProps> = () => {
  const [loading, setLoading] = useState<boolean>();
  const navigate = useNavigate();
  const setProfile = useAccountStore((state) => state.setProfile);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginForm>();

  const onSubmit = async (data: LoginForm) => {
    try {
      setLoading(true);
      const response: LoginDataResponse = await dataFetcherNotAuth.post(
        "login",
        data
      );
      toast.success("Login successful!");
      setTokenToLocalStorage(response.data);
      setProfile({ email: data.email });
      navigate("/");
    } catch (e) {
      toast.error("Login failed!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div className="h-screen flex justify-center items-center container mx-auto">
        <div className="rounded bg-white p-5 md:shadow-xl w-full md:w-auto">
          <h1 className="text-center text-3xl font-bold">CRM APP</h1>
          <div className="py-4 md:w-96 max-w-full">
            <form onSubmit={handleSubmit(onSubmit)}>
              <div className="form-control w-full">
                <label className="label">
                  <span className="label-text">Email</span>
                </label>
                <input
                  type="email"
                  placeholder="Type here"
                  className="input input-bordered w-full focus-within:input-primary"
                  {...register("email", {
                    pattern: {
                      value: /[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$/,
                      message: "Email not valid!",
                    },
                    required: { value: true, message: "Required!" },
                  })}
                  disabled={loading}
                />
                <label className="label">
                  <span className="label-text-alt text-error">
                    {errors.email?.message}
                  </span>
                </label>
              </div>
              <div className="form-control w-full">
                <label className="label">
                  <span className="label-text">Password</span>
                </label>
                <input
                  type="password"
                  placeholder="Type here"
                  className="input input-bordered w-full focus-within:input-primary"
                  {...register("password", {
                    required: { value: true, message: "Required!" },
                  })}
                  disabled={loading}
                />
                <label className="label">
                  <span className="label-text-alt text-error">
                    {errors.password?.message}
                  </span>
                </label>
              </div>
              <div className="pt-5">
                <button
                  type="submit"
                  className="btn btn-primary w-full"
                  disabled={loading}
                >
                  Login
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
