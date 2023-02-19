import { create } from "zustand";
import { devtools, persist } from "zustand/middleware";

export interface AccountTypeData {
  id: number;
  name: string;
}

export interface AccountData {
  id?: string;
  email?: string;
  fullName?: string;
  phoneNumber?: string;
  address?: string;
  accountType?: AccountTypeData;
}

export interface ZusAccountState {
  accountList: AccountData[];
  setAccountList: (newAccountList: AccountData[]) => void;
}

export interface ZusProfileState {
  profile: AccountData;
  setProfile: (newProfile: AccountData) => void;
}

export const useProfileStore = create<ZusProfileState>()(
  devtools(
    persist(
      (set, get) => ({
        profile: {},
        setProfile: (newProfile) => set((state) => ({ profile: newProfile })),
      }),
      {
        name: "profile-storage",
      }
    )
  )
);

export const useAccountStore = create<ZusAccountState>((set) => ({
  accountList: [],
  setAccountList: (newAccountList) => set(() => ({ accountList: newAccountList }))
}));
