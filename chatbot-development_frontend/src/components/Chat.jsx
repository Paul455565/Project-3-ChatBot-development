import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Chat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [error, setError] = useState(null);

    const navigate = useNavigate();
    const handleSendMessage = async () => {
        if (input.trim() === '') return; // Do nothing if the input is empty

        // Create the message object
        const userMessage = { text: input };


        // Immediately reflect the user's message on the UI
        setMessages([...messages, { sender: 'You', text: input }]);

        // Mirror the user's input as the chatbot's response
        setMessages(prevMessages => [
            ...prevMessages,
            { sender: 'Chatbot', text: input } // Mirror as chatbot response
        ]);

        // Clear the input field
        setInput('');

        try {
            // Attempt to post the message to the backend
            const response = await axios.post('/api/chat/messages', userMessage);
        } catch (error) {
            setError('Failed to send message to databse.');
            console.error('Error sending message:', error);
        }
    };
    const handleLogout = () => {
        // Clear any user-related data or perform logout operations here
        // Redirect to the landing page
        navigate('/'); // Navigate to the landing page
    };

    return (
        <div className="chat-wrapper">
            <header>
                <nav>
                    <button onClick={() => console.log('Navigating to previous chats')}>Previous Chats</button>
                    <button onClick={handleLogout}>Logout</button>
                </nav>
            </header>
            <div className="chat-output">
                {messages.map((msg, index) => (
                    <div
                        key={index}
                        className={`chat-message ${msg.sender === 'You' ? 'user-message' : 'bot-message'}`}
                    >
                        <strong>{msg.sender}:</strong> {msg.text}
                    </div>
                ))}
            </div>
            <div className="chat-input">
                <input
                    type="text"
                    value={input}
                    placeholder="Message our ChatBot..."
                    onChange={(e) => setInput(e.target.value)}
                    onKeyDown={(e) => e.key === 'Enter' && handleSendMessage()}
                />
                <button onClick={handleSendMessage}>Send</button>
            </div>
            {error && <div className="error-message">{error}</div>}
        </div>
    );
}

export default Chat;










