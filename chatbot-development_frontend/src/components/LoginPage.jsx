import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Assuming you're using React Router for navigation

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); // React Router hook for navigation

    const handleLogin = (event) => {
        event.preventDefault();

        // Directly navigate to the chatbot screen after login button is pressed
        navigate('/Chat');
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            <form className="login-form" onSubmit={handleLogin}>
                <div className="input-container">
                    <input
                        type="text"
                        placeholder="Username..."
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="input-container">
                    <input
                        type="password"
                        placeholder="Password..."
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit" className="login-button">Login</button>
            </form>
            <a href="/register" className="register-link">Register...</a>
        </div>
    );
};

export default LoginPage;


