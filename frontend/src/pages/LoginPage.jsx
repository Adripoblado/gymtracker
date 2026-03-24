import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const LoginPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();

    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {            
            const response = await api.post('/auth/login', formData);
            
            localStorage.setItem('token', response.data.token);
            localStorage.setItem('username', response.data.username);

            navigate('/', {replace: true});
        } catch (err) {
            console.error("Login Error:", err.response?.data || err.message);
            setError('Invalid username or password. Please try again.');
        }
    };

    return (
        <div style={styles.container}>
            <h2>GymTracker Login</h2>
            <form onSubmit={handleSubmit} style={styles.form}>
                <div style={styles.inputGroup}>
                    <label htmlFor="username">Username or Email</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div style={styles.inputGroup}>
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>

                {error && <p style={styles.error}>{error}</p>}

                <button type="submit" style={styles.button}>Enter</button>

                <div style={styles.footer}>
                    <span>Don't have an account yet?</span>
                    <button
                        type="button"
                        onClick={() => navigate('/register')}
                        style={styles.linkButton}>
                            Register here
                    </button>
                </div>
            </form>
        </div>
    );
};

const styles = {
    container: { display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '50px', fontFamily: 'Arial' },
    form: { display: 'flex', flexDirection: 'column', width: '300px', gap: '15px' },
    inputGroup: { display: 'flex', flexDirection: 'column', gap: '5px' },
    button: { padding: '10px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' },
    error: { color: 'red', fontSize: '0.9rem' }
};

export default LoginPage;