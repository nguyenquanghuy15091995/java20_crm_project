import { Routes, Route } from "react-router-dom";
import { protectedRoutes, publicRoutes, renderRoutes } from "./routes";
import AuthWrapper from "./routes/AuthWrapper";
import NonAuthWrapper from "./routes/NonAuthWrapper";
import Layout from "./features/Layout";

function App() {
  return (
    <div>
      <Routes>
        <Route element={<NonAuthWrapper />}>
          {renderRoutes("public-route", publicRoutes)}
        </Route>
        <Route
          element={
            <AuthWrapper>
              <Layout />
            </AuthWrapper>
          }
        >
          {renderRoutes("protected-route", protectedRoutes)}
        </Route>
      </Routes>
    </div>
  );
}

export default App;
