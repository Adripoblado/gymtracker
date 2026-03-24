import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: ''
    });
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        try {
            await api.post('/auth/register', formData);

            alert('Account successfully registered! Please login.');
            navigate('/login')
        } catch (err) {
            console.error("Register Error: ", err.response?.data || err.message);
            setError(err.response?.data?.message || 'Error while creating account. Please try again.')
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={styles.container}>
            <h2>Create Account on GymTracker</h2>
            <form onSubmit={handleSubmit} style={styles.form}>
                <div style={styles.inputGroup}>
                    <label htmlFor="username">Username</label>
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
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
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

                <button type="submit" style={styles.button} disabled={loading}>
                    {loading ? 'Registering...' : 'Register'}
                </button>

                <div style={styles.footer}>
                    <span>Already have an account? </span>
                    <button 
                        type="button"
                        onClick={() => navigate('/login')}
                        style={styles.linkButton}
                        >
                            Login
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
    button: { padding: '10px', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' },
    error: { color: 'red', fontSize: '0.9rem' },
    footer: { marginTop: '10px', textAlign: 'center', fontSize: '0.9rem' },
    linkButton: { background: 'none', border: 'none', color: '#007bff', cursor: 'pointer', textDecoration: 'underline', padding: 0 }
};

export default RegisterPage;