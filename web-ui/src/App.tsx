import { Routes, Route } from "react-router-dom";
import { protectedRoutes, publicRoutes, renderRoutes } from "./routes";

function App() {
  return (
    <div>
      <Routes>
        {renderRoutes("public-route", publicRoutes)}
        {renderRoutes("protected-route", protectedRoutes)}
        {/* <Route
            // element={
            //   // <Auth.AuthWrapper>
            //   <LayoutWrapper />
            //   // </Auth.AuthWrapper>
            // }
          >
            {renderRoutes("protected-route", protectedRoutes)}
          </Route> */}
      </Routes>
    </div>
  );
}

export default App;
