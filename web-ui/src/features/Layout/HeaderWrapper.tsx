import { useEffect } from "react";
import useSWR from "swr";
import Header from "../../components/Header";
import { useProfileStore, AccountData } from "../../store/account";
import { dataFetcher } from "../../utils/data-fetcher";

const HeaderWrapper = () => {
  const profile: AccountData = useProfileStore((state) => state.profile);
  const setProfile = useProfileStore((state) => state.setProfile);

  const fetchProfileData = async () => {
    const response = await dataFetcher.get(`accounts/once?email=${profile.email}`);
    setProfile(response.data);
  }

  useEffect(() => {
    fetchProfileData();
  }, []);
  return (
    <>
      <Header profile={profile} />
    </>
  );
};

export default HeaderWrapper;
