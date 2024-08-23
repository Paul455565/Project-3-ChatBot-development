// eslint-disable-next-line no-unused-vars
import React from 'react';
import { Link } from 'react-router-dom';
import cputLogo from '../assets/cput-logo.jpg';

function LandingPage() {
    return (
        <div style={{ textAlign: 'center', padding: '50px' }}>
            <img src={cputLogo} alt="CPUT Logo" style={{ width: '200px' }} />
            <h1>Welcome to the CPUT Student Chatbot</h1>
            <p>Get instant support and answers to your queries, 24/7.</p>
            <Link to="/login">
                <button style={{
                    padding: '10px 20px',
                    fontSize: '16px',
                    backgroundColor: '#007BFF',
                    color: 'white',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer'
                }}>
                    Login to Get Started
                </button>
            </Link>
        </div>
    );
}

export default LandingPage;