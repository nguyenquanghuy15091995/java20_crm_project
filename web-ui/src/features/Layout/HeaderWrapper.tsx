import { useEffect } from "react";
import useSWR from "swr";
import Header from "../../components/Header";
import { useAccountStore, AccountData } from "../../store/account";
import { dataFetcher } from "../../utils/data-fetcher";

const HeaderWrapper = () => {
  const profile: AccountData = useAccountStore((state) => state.profile);
  const setProfile = useAccountStore((state) => state.setProfile);
//   const { data } = useSWR(`accounts/once?email=${profile.email}`, dataFetcher.get);
  const fetchProfileData = async () => {
    const response = await dataFetcher.get(`accounts/once?email=${profile.email}`);
    setProfile(response.data);
  }
  useEffect(() => {
    fetchProfileData()
  }, []);
  return (
    <>
      <Header profile={profile} />
    </>
  );
};

export default HeaderWrapper;
