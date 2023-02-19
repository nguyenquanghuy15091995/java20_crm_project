import { FC, useEffect } from "react";
import { useForm } from "react-hook-form";
import { AccountData } from "../../store/account";
import TextFieldWrapper from "../../components/TextFieldWrapper";

export interface AccountFormProps {
    accountData?: AccountData;
}

const AccountForm: FC<AccountFormProps> = ({ accountData }) => {
    const {
        register,
        setValue,
        reset,
        handleSubmit,
        formState: { errors },
    } = useForm<AccountData>();
    const onSubmit = (data: AccountData) => { }

    useEffect(() => {
        if (accountData) {
            setValue("fullName", accountData.fullName);
            setValue("email", accountData.email);
            setValue("address", accountData.address);
            setValue("phoneNumber", accountData.phoneNumber);
            setValue("accountType.id", accountData.accountType?.id || 0);
        } else {
            reset();
        }
    }, [accountData]);

    return <>
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="grid gap-x-5 grid-cols-1 md:grid-cols-2">
                <div>
                    <TextFieldWrapper label="Full Name" errorMessage={errors.fullName?.message}>
                        <input type="text" placeholder="Enter full name" className="input input-bordered"
                            {...register("fullName", { required: { value: true, message: "Please enter your name" } })}
                        />
                    </TextFieldWrapper>
                </div>
                <div>
                    <TextFieldWrapper label="Email" errorMessage={errors.email?.message}>
                        <input type="text" placeholder="Enter email" className="input input-bordered"
                            {...register("email", {
                                required: { value: true, message: "Please enter your email" },
                                pattern: {
                                    value: /[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$/,
                                    message: "Email not valid!",
                                },
                            })}
                        />
                    </TextFieldWrapper>
                </div>
                <div>
                    <TextFieldWrapper label="Phone Number" errorMessage={errors.phoneNumber?.message}>
                        <input type="text" placeholder="Enter Phone number" className="input input-bordered"
                            {...register("phoneNumber", { required: { value: true, message: "Please enter your phone" } })}
                        />
                    </TextFieldWrapper>
                </div>
                <div>
                    <TextFieldWrapper label="Address" errorMessage={errors.address?.message}>
                        <input type="text" placeholder="Enter address" className="input input-bordered"
                            {...register("address", { required: { value: true, message: "Please enter your address" } })}
                        />
                    </TextFieldWrapper>
                </div>
                <div>
                    <TextFieldWrapper label="Role" errorMessage={errors.accountType?.message}>
                        <select className="select select-bordered w-full" defaultValue="1" {...register("accountType.id", { required: { value: true, message: "Please select your role" } })}>
                            <option value="1">Who shot first?</option>
                            <option value="2">Han Solo</option>
                            <option value="3">Greedo</option>
                        </select>
                    </TextFieldWrapper>
                </div>
            </div>
            <div className="flex justify-end items-center gap-4 pt-4">
                <label htmlFor="account-form-modal" className="btn btn-outline min-w-[6rem]">Cancel</label>
                <button type="submit" className="btn btn-primary min-w-[6rem]">Save</button>
            </div>
        </form>
    </>
}

export default AccountForm;
