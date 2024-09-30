import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import cputLogo from '../assets/cput-logo.jpeg';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [responseMessage, setResponseMessage] = useState('');

    const navigate = useNavigate(); // Initialize the useNavigate hook

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
                navigate('/chat'); // Redirect to chat page upon successful login
            } else {
                setResponseMessage(data.message || 'Invalid credentials, try again!');
            }
        } catch (error) {
            setResponseMessage('An error occurred. Please try again.');
        }
    };


    return (
        <div className="login-container">
            <img src={cputLogo} alt="CPUT Logo" className="cput-logo"/>
            <h1>Login</h1>
            <form className="login-form" onSubmit={handleSubmit}>
                <div className="input-container">
                    <input
                        type="email"
                        placeholder="Email..."
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="input-container">
                    <input
                        type="password"
                        placeholder="Password..."
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="login-button">Login</button>
                {responseMessage && <p>{responseMessage}</p>}
            </form>
            <Link to="/register" className="register-link">Register...</Link>
        </div>
    );
}

export default LoginPage;



