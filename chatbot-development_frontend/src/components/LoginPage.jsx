import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import cputLogo from '../assets/cput-logo.jpg';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [responseMessage, setResponseMessage] = useState('');

    const navigate = useNavigate(); // Initialize the useNavigate hook

    const handleSubmit = (e) => {
        e.preventDefault();

        // Hardcoded login details for demonstration
        const hardcodedEmail = 'paulmaja14@gmail.com';
        const hardcodedPassword = 'paulmaja14';

        if (email === hardcodedEmail && password === hardcodedPassword) {
            setResponseMessage('Login successful');
            // Redirect to chat page upon successful login
            navigate('/chat'); // Navigate to the chat page
        } else {
            setResponseMessage('Invalid credentials, try again!'); // Set error message
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


