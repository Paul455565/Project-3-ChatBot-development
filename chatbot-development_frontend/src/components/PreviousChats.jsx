import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllMessages } from './../service/chatService.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import './PreviousChats.css'; // Import your custom CSS file

function PreviousChats() {
    const [messages, setMessages] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchMessages = async () => {
            try {
                const response = await getAllMessages();
                setMessages(response.data);
            } catch (error) {
                setError('Failed to fetch previous messages.');
                console.error('Error fetching previous messages:', error);
            }
        };
        fetchMessages();
    }, []);

    const handleBackToChat = () => {
        navigate('/chat');
    };

    return (

        <div className="container-fluid">

            <div className="chat-box">
                <header>
                    <nav>
                        <button className="btn btn-primary" onClick={handleBackToChat}>Back to Chat</button>
                    </nav>
                </header>

                <div className="chat-output">
                    {messages.length > 0 ? (
                        <table className="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Prompt</th>
                                <th scope="col">Response</th>
                            </tr>
                            </thead>
                            <tbody>
                            {messages.map((msg, index) => (
                                <tr key={index}>
                                    <td>{msg.question}</td>
                                    <td>{msg.answer}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    ) : (
                        <p>No previous messages found.</p>
                    )}
                </div>

                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    );
}

export default PreviousChats;
