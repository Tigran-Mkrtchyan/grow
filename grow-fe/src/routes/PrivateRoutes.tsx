import { Route, Routes, Navigate, useNavigate } from 'react-router-dom';
import React, { Suspense, useEffect, FC } from 'react';
import { privateRoutes } from './RoutesData';
import { RoutesPaths } from './Routes.types';

const PrivateRoutes: FC<{ isLoggedIn: boolean }> = ({ isLoggedIn }) => {
  const navigate = useNavigate();
  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/login');
    }
  }, [isLoggedIn]);
  return (
    <Suspense fallback={<div />}>
      <Routes>
        <Route element={<div />}>
          <Route
            path="*"
            element={<Navigate replace to={RoutesPaths.PAGE_NOT_FOUND} />}
          />
          {privateRoutes.map(({ path, component }) => {
            const Component = component;
            return (
              <Route key={path} path={`${path}/*`} element={<Component />} />
            );
          })}
        </Route>
      </Routes>
    </Suspense>
  );
};

export default PrivateRoutes;
