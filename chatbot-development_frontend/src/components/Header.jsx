// Header.js
import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';

function Header() {
    const location = useLocation();
    const navigate = useNavigate();

    // Confirm the correct login page path
    const isLoginPage = location.pathname === '/login'; // Adjust the path if necessary

    // Handle logout
    const handleLogout = () => {
        navigate('/login');  // Navigate to login page after logout
    };

    return (
        <header className="header">
            <nav>
                <ul className="nav-links">
                    {/* Conditionally render links based on whether it's the login page */}
                    {!isLoginPage && (
                        <>
                            <li><Link to="/chat">Chat</Link></li>
                            <li><Link to="/home">Home</Link></li>
                            <li><Link to="/previous-chats">Previous Chats</Link></li>
                        </>
                    )}
                    {/* Always display login, registration, and sign-out links */}
                    <li><Link to="/login">Login</Link></li>
                    <li><Link to="/register">Registration</Link></li>
                    {/* Sign-out button */}
                    {!isLoginPage && (
                        <li>
                            <button onClick={handleLogout} className="nav-button">
                                Sign out
                            </button>
                        </li>
                    )}
                </ul>
            </nav>
        </header>
    );
}

export default Header;
