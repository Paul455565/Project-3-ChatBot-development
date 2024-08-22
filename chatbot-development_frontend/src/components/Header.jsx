// Header.js
import React from 'react';
import { Link } from 'react-router-dom';


function Header() {
    return (
        <header className="header">
            <nav>
                <ul className="nav-links">
                    <li><Link to="/chat">Chat</Link></li>
                    <li><Link to="/login">Login</Link></li>
                    <li><Link to="/register">Registration</Link></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;

