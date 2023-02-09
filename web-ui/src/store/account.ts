import { create } from "zustand";
import { devtools, persist } from "zustand/middleware";

export interface AccountData {
  id?: string;
  email?: string;
  fullName?: string;
  phoneNumber?: string;
  address?: string;
}

export interface ZusAccountState {
  profile: AccountData;
  setProfile: (newProfile: AccountData) => void;
}

export const useAccountStore = create<ZusAccountState>()(
  devtools(
    persist(
      (set, get) => ({
        profile: {},
        setProfile: (newProfile) => set((state) => ({ profile: newProfile })),
      }),
      {
        name: "account-storage",
      }
    )
  )
);
