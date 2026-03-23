import React from 'react';
import { Outlet, Link, useNavigate } from 'react-router-dom';

const MainLayout = () => {
    const navigate = useNavigate();
    const username = localStorage.getItem('username') || 'User';

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('username');

        navigate('/login', { replace: true });
    };

    return (
        <div style={styles.container}>
            <nav style={styles.navbar}>
                <div style={styles.logo}>GymTracker Pro</div>
                <div style={styles.userSection}>
                    <span>Hello, <strong>{username}</strong></span>
                    <button onClick={handleLogout} style={styles.logoutBtn}>
                        Close session
                    </button>
                </div>
            </nav>
            
            <div style={styles.body}>
                <aside style={styles.sidebar}>
                    <ul style={styles.navList}>
                        <li><Link to="/" style={styles.link}>Dashboard</Link></li>
                        <li><Link to="/exercises" style={styles.link}>Exercises</Link></li>
                        <li><Link to="/workouts" style={styles.link}>Workouts</Link></li>
                        <li><Link to="/profile" style={styles.link}>Profile</Link></li>
                    </ul>
                </aside>

                <main style={styles.mainContent}>
                    <Outlet />
                </main>
            </div>
        </div>
    );
};

const styles = {
    container: { display: 'flex', flexDirection: 'column', height: '100vh', fontFamily: 'Segoe UI, Tahoma, Geneva, Verdana, sans-serif' },
    navbar: { height: '60px', backgroundColor: '#2c3e50', color: 'white', display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '0 20px', boxShadow: '0 2px 5px rgba(0,0,0,0.1)' },
    userSection: { display: 'flex', alignItems: 'center', gap: '15px' },
    logoutBtn: { backgroundColor: '#e74c3c', color: 'white', border: 'none', padding: '8px 12px', borderRadius: '4px', cursor: 'pointer' },
    body: { display: 'flex', flex: 1, overflow: 'hidden' },
    sidebar: { width: '240px', backgroundColor: '#f8f9fa', borderRight: '1px solid #dee2e6', padding: '20px' },
    navList: { listStyle: 'none', padding: 0, margin: 0, display: 'flex', flexDirection: 'column', gap: '10px' },
    link: { textDecoration: 'none', color: '#333', fontWeight: '500', display: 'block', padding: '10px', borderRadius: '5px', transition: 'background 0.2s' },
    mainContent: { flex: 1, padding: '30px', overflowY: 'auto', backgroundColor: '#fff' }
};

export default MainLayout;