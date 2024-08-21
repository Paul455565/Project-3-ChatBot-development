// eslint-disable-next-line no-unused-vars
import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';

const RegistrationPage = () => {
    // State to manage form fields
    const [form, setForm] = useState({
        email: '',
        password: '',
        confirmPassword: ''
    });
    // State for response message
    const [responseMessage, setResponseMessage] = useState('');
    // States for password visibility
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    // Handle input changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({
            ...form,
            [name]: value
        });
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (form.password !== form.confirmPassword) {
            setResponseMessage('Passwords do not match');
            return;
        }
        try {
            await axios.post('http://localhost:8081/Project-3-ChatBot-development/user/create', {
                email: form.email,
                password: form.password,
                confirmPassword: form.confirmPassword
            }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            setResponseMessage('Form successfully sent');
            setForm({
                email: '',
                password: '',
                confirmPassword: ''
            });
        } catch (error) {
            console.error('There was an error!', error);
            setResponseMessage('An error occurred while submitting the form.');
        }
    };

    // Toggle password visibility
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const toggleConfirmPasswordVisibility = () => {
        setShowConfirmPassword(!showConfirmPassword);
    };

    return (
        <div className="registration-container">
            <h1>Register</h1>
            <form className="registration-form" onSubmit={handleSubmit}>
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
                        type={showPassword ? "text" : "password"}
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
                        type={showConfirmPassword ? "text" : "password"}
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
    );
}

export default RegistrationPage;