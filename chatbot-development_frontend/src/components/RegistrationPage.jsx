import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import cputLogo from '../assets/cput-logo.jpeg';
import './Registration.css'; // Ensure this points to your CSS file

const RegistrationPage = () => {
    const [form, setForm] = useState({
        name: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: ''
    });
    const [responseMessage, setResponseMessage] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({
            ...form,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (form.password !== form.confirmPassword) {
            setResponseMessage('Passwords do not match');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8081/Chatbotdb/user/create', {
                name: form.name,
                lastName: form.lastName,
                email: form.email,
                password: form.password
            }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.status === 200 || response.status === 201) {
                setResponseMessage('Registration successful!');
                setForm({
                    name: '',
                    lastName: '',
                    email: '',
                    password: '',
                    confirmPassword: ''
                });
            } else {
                setResponseMessage('Registration failed. Please try again.');
            }
        } catch (error) {
            console.error('There was an error!', error);
            setResponseMessage('An error occurred while submitting the form.');
        }
    };

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const toggleConfirmPasswordVisibility = () => {
        setShowConfirmPassword(!showConfirmPassword);
    };

    return (
        <div className="registration-page">
            {/* Header Section */}
            <header className="header">
                <nav>
                    <ul>
                        <li><Link to="/">Home</Link></li>
                        <li><Link to="/login">Login</Link></li>
                    </ul>
                </nav>
            </header>

            <div className="content-wrapper">
                <div className="left-section">
                    <img src={cputLogo} alt="CPUT Logo" className="cput-logo" />
                </div>

                <div className="right-section">
                    <form className="registration-form" onSubmit={handleSubmit}>
                        <h2>Register</h2> {/* Header for Registration Form */}
                        <div className="input-container">
                            <input
                                type="text"
                                name="name"
                                value={form.name}
                                onChange={handleChange}
                                placeholder="First Name..."
                                required
                            />
                        </div>
                        <div className="input-container">
                            <input
                                type="text"
                                name="lastName"
                                value={form.lastName}
                                onChange={handleChange}
                                placeholder="Last Name..."
                                required
                            />
                        </div>
                        <div className="input-container">
                            <input
                                type="email"
                                name="email"
                                value={form.email}
                                onChange={handleChange}
                                placeholder="Email..."
                                required
                            />
                        </div>
                        <div className="input-container">
                            <input
                                type={showPassword ? 'text' : 'password'}
                                name="password"
                                value={form.password}
                                onChange={handleChange}
                                placeholder="Password..."
                                required
                            />
                            <FontAwesomeIcon
                                icon={showPassword ? faEyeSlash : faEye}
                                onClick={togglePasswordVisibility}
                                className="password-toggle-icon"
                            />
                        </div>
                        <div className="input-container">
                            <input
                                type={showConfirmPassword ? 'text' : 'password'}
                                name="confirmPassword"
                                value={form.confirmPassword}
                                onChange={handleChange}
                                placeholder="Confirm Password..."
                                required
                            />
                            <FontAwesomeIcon
                                icon={showConfirmPassword ? faEyeSlash : faEye}
                                onClick={toggleConfirmPasswordVisibility}
                                className="password-toggle-icon"
                            />
                        </div>
                        <button type="submit" className="register-button">Register</button>
                    </form>
                    {responseMessage && <p>{responseMessage}</p>}
                    <Link to="/login" className="login-link">Already have an account? Login...</Link>
                </div>
            </div>
        </div>
    );
};

export default RegistrationPage;
