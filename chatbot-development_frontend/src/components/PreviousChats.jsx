import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllMessages} from './../service/chatService.js';
import 'bootstrap/dist/css/bootstrap.min.css';

function PreviousChats() {
    const [messages, setMessages] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchMessages = async () => {
            try {
                //const userId = 1; // Replace this with dynamic userId based on your authentication
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
        <div className="container-fluid d-flex flex-column align-items-center vh-100">
            <div className="chat-box w-500">
                <header className="mb-2">
                    <nav className="d-flex justify-content-between p-2 bg-light">
                        <button className="btn btn-primary" onClick={handleBackToChat}>Back to Chat</button>
                    </nav>
                </header>

                <div className="chat-output border rounded p-3 mb-3 flex-grow-1" style={{ height: '60vh', overflowY: 'auto' }}>
                    {messages.length > 0 ? (
                        messages.map((msg, index) => (
                            <div key={index} className="chat-message my-2 p-2 bg-light">
                                <strong>You:</strong> {msg.question}<br />
                                <strong>Chatbot:</strong> {msg.answer}
                            </div>
                        ))
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
