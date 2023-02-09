import { useEffect } from "react";
import useSWR from "swr";
import Header from "../../components/Header";
import { useAccountStore, AccountData } from "../../store/account";
import { dataFetcher } from "../../utils/data-fetcher";

const HeaderWrapper = () => {
  const profile: AccountData = useAccountStore((state) => state.profile);
//   const { data } = useSWR(`accounts/once?email=${profile.email}`, dataFetcher.get);
  const testFetch = async () => {
    const response = await dataFetcher.get(`accounts/once?email=${profile.email}`);
    console.log(response)
  }
  useEffect(() => {
    // console.log(data);
    testFetch()
  }, []);
  return (
    <>
      <Header profile={profile} />
    </>
  );
};

export default HeaderWrapper;
