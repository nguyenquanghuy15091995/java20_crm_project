import { lazy, FC, ReactNode, Suspense } from "react";
import { Route, RouteProps } from "react-router-dom";
import LoadingPanel from "../components/LoadingPanel";

const Home = lazy(() => import("../features/Home"));
const Login = lazy(() => import("../features/Login"));
const Profile = lazy(() => import("../features/Profile"));
const Accounts = lazy(() => import("../features/Accounts"));
const Projects = lazy(() => import("../features/Projects"));

export interface RouteTypes extends Omit<RouteProps, "element" | "children"> {
  Element: FC;
  children?: RouteTypes[];
}

export interface RouteAttributes {
  path: string;
  label: string;
}

export interface RouteCollection {
  [key: string]: RouteAttributes;
  HOME: RouteAttributes;
  PROFILE: RouteAttributes;
  ACCOUNTS: RouteAttributes;
  PROJECTS: RouteAttributes;
}

export const ROUTE_COLLECTION: RouteCollection = {
  HOME: { path: "", label: "Dashboard" },
  PROFILE: { path: "profile", label: "Profile" },
  ACCOUNTS: { path: "accounts", label: "Accounts" },
  PROJECTS: { path: "projects", label: "Projects" },
};

export const protectedRoutes: RouteTypes[] = [
  {
    Element: Home,
    path: `${ROUTE_COLLECTION.HOME.path}`,
    index: true,
  },
  {
    Element: Profile,
    path: `${ROUTE_COLLECTION.PROFILE.path}`,
  },
  {
    Element: Accounts,
    path: `${ROUTE_COLLECTION.ACCOUNTS.path}`,
  },
  {
    Element: Projects,
    path: `${ROUTE_COLLECTION.PROJECTS.path}`,
  },
];

export const publicRoutes: RouteTypes[] = [{ Element: Login, path: "/login" }];

export const renderRoutes = (
  id: string | number,
  routes: RouteTypes[]
): ReactNode => {
  return (
    <>
      {routes.map(({ Element, children, index, ...routeProps }, routeIdx) => {
        return (
          // @ts-ignore: Unreachable code error
          <Route
            key={`${id}-${routeIdx.toString()}`}
            index={index}
            element={
              <Suspense fallback={<LoadingPanel />}>
                <div className="py-4 px-5">
                  <Element />
                </div>
              </Suspense>
            }
            {...routeProps}
          >
            {Array.isArray(children) &&
              renderRoutes(`${id}-${routeIdx.toString()}`, children)}
          </Route>
        );
      })}
    </>
  );
};
