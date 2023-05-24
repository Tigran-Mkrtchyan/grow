import React from 'react';
import { Navigate, Route, Routes, Outlet } from 'react-router-dom';

const Dashboard = () => {
  return (
    <Routes>
      <Route path="/" element={<Outlet />}>
        <Route path="/" element={<div>Dashboard</div>} />
        <Route path="/dashboard/:id" element={<div />} />
        <Route path="*" element={<Navigate to="/app/404" />} />
      </Route>
    </Routes>
  );
};

export default Dashboard;
