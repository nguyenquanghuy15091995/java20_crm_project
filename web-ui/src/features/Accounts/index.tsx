import { FC, useEffect, useState } from "react";
import useSWR from "swr";
import { dataFetcher, AxiosResponseData } from "../../utils/data-fetcher";
import { AccountData, useAccountStore } from "../../store/account";
import Paper from "../../components/Paper";
import AccountForm from "./AccountForm";

export interface AccountProps { }

const Account: FC<AccountProps> = () => {
  const accountList = useAccountStore(state => state.accountList);
  const setAccountList = useAccountStore(state => state.setAccountList);

  const [curentAccount, setCurentAccount] = useState<AccountData | undefined>();

  const { isLoading, data } = useSWR<AxiosResponseData<AccountData[]>>("accounts", dataFetcher);

  return <>
    <div className="flex justify-between items-center py-4">
      <div></div>
      <div><label htmlFor="account-form-modal" className="btn btn-success btn-sm text-white min-w-[6rem]" onClick={() => setCurentAccount(undefined)}>Create</label></div>
    </div>
    <Paper>
      <div>
        <div className="overflow-x-auto">
          <table className="table w-full">
            <thead>
              <tr>
                <th>#</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {Array.isArray(data?.data) && data?.data.map((item, itemIdx) => (
                <tr key={item.id}>
                  <th>{itemIdx + 1}</th>
                  <td>{item.fullName}</td>
                  <td>{item.email}</td>
                  <td>{item.phoneNumber}</td>
                  <td>{item.address}</td>
                  <td><label htmlFor="account-form-modal" className="btn btn-primary btn-sm" onClick={() => setCurentAccount(item)}>Edit</label></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="flex justify-end px-4 py-2 border-t">
          <div className="flex rounded-lg">
            <button className="btn btn-sm btn-ghost">«</button>
            <div className="text-sm px-4 inline-flex items-center justify-center">Page 22</div>
            <button className="btn btn-sm btn-ghost">»</button>
          </div>
        </div>
      </div>
    </Paper>
    <input type="checkbox" id="account-form-modal" className="modal-toggle" />
    <div className="modal">
      <div className="modal-box relative w-11/12 max-w-3xl">
        <label htmlFor="account-form-modal" className="btn btn-sm btn-circle absolute right-2 top-2">✕</label>
        <h3 className="text-lg font-bold">Account Form</h3>
        <div className="py-4">
          <AccountForm accountData={curentAccount} />
        </div>
      </div>
    </div>
  </>;
};

export default Account;
