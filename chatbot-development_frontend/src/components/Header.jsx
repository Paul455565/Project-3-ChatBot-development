// Header.js
import React from 'react';
import { Link, useLocation } from 'react-router-dom';

function Header() {
    const location = useLocation();

    // Confirm the correct login page path
    const isLoginPage = location.pathname === '/login'; // Adjust the path if necessary

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
                    {/* Always display login and registration links */}
                    <li><Link to="/login">Login</Link></li>
                    <li><Link to="/register">Registration</Link></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;
