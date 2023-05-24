import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import React, { FC, Suspense, useEffect } from 'react';
import { publicRoutes } from './RoutesData';
import { RoutesPaths } from './Routes.types';

const PublicRoutes: FC<{
  isLoggedIn: boolean;
}> = ({ isLoggedIn }) => {
  const navigate = useNavigate();
  useEffect(() => {
    if (isLoggedIn) {
      navigate(`app/${RoutesPaths.DASHBOARD}`);
    }
  }, [isLoggedIn]);
  return (
    <Suspense fallback={null}>
      <Routes>
        <Route
          path="/"
          element={<Navigate replace to={publicRoutes[0].path} />}
        />
        {publicRoutes.map(({ path, component }) => {
          const Component = component;
          return <Route key={path} path={`${path}`} element={<Component />} />;
        })}
        <Route path="*" element={<Navigate to="/app/404" />} />
      </Routes>
    </Suspense>
  );
};

export default PublicRoutes;
