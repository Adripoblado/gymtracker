import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Dashboard from './pages/Dashboard';
import ExercisesPage from './pages/ExercisesPage';
import WorkoutPage from './pages/WorkoutPage';
import Profile from './pages/Profile';
import ProtectedRoute from './components/ProtectedRoute';
import MainLayout from './pages/MainLayout';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import React from 'react';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      staleTime: 5 * 60 * 1000
    },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ReactQueryDevtools initialIsOpen={false} />
      <Router>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
    
          <Route element={<ProtectedRoute />}>
            <Route element={<MainLayout />}>
              <Route path="/" element={<Dashboard />} />
              <Route path="/exercises" element={<ExercisesPage />} />
              <Route path="/workouts" element={<WorkoutPage />} />
              <Route path="/profile" element={<Profile />} />
            </Route>
          </Route>
    
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
    </Router>
    </QueryClientProvider>
  );
}

export default App;