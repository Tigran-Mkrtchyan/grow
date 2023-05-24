import React, { Suspense, FC } from 'react';
import { Routes, Route } from 'react-router-dom';
// import { useAppSelector } from 'redux/store';
import PublicRoutes from './PublicRoutes';
import PrivateRoutes from './PrivateRoutes';

const RoutesPage: FC = () => {
  // const user = useAppSelector(state => state.userSlice.user);
  // const [reRetch] = useCurrentAuthUserAmplifyMutation();
  //
  // useEffect(() => {
  //   if (!user) {
  //     return;
  //   }
  //   reRetch(null);
  // }, [user]);

  return (
    <Suspense fallback={null}>
      <Routes>
        <Route path="/*" element={<PublicRoutes isLoggedIn={false} />} />
        <Route path="app/*" element={<PrivateRoutes isLoggedIn={false} />} />
      </Routes>
    </Suspense>
  );
};
export default RoutesPage;
