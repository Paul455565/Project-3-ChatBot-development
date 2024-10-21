import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import cputLogo from '../assets/cput-logo.jpeg'; // Ensure this path is correct.
import './LoginPage.css'; // Ensure this points to the correct CSS file

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [responseMessage, setResponseMessage] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8081/Project-3-ChatBot-development/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();

            if (response.ok) {
                setResponseMessage('Login successful');
                navigate('/chat');
            } else {
                setResponseMessage(data.message || 'Invalid credentials, try again!');
            }
        } catch (error) {
            setResponseMessage('An error occurred. Please try again.');
        }
    };

    return (
        <div className="login-page">
            <div className="content-wrapper">
                <div className="left-section">
                    <img src={cputLogo} alt="CPUT Logo" className="logo" />
                </div>

                <div className="right-section">
                    <form className="login-form" onSubmit={handleSubmit}>
                        <div className="input-container">
                            <label htmlFor="email">Email:</label>
                            <input
                                type="text"
                                placeholder="Enter email"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>

                        <div className="input-container">
                            <label htmlFor="password">Password:</label>
                            <input
                                type="password"
                                placeholder="Enter password"
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>

                        <button type="submit" className="login-button">Login</button>

                        {responseMessage && <p>{responseMessage}</p>}
                    </form>

                    <div className="additional-options">
                        <button className="option-button">Forgot Password</button>
                        <button className="option-button">Change Password</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
