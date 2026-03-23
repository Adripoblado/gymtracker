import { Navigate, Outlet } from "react-router-dom";

const ProtectedRoute = () => {
    const token = localStorage.getItem('token');

    if (!token) {
        console.log("Error! No token on localStorage, returing to login page")
        return <Navigate to="/login" replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;