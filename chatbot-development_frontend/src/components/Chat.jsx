import React, { useState } from 'react';
import axios from 'axios';

function Chat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [error, setError] = useState(null);

    const handleSendMessage = async () => {
        try {
            if (input.trim() !== '') {
                // Create the message object
                const userMessage = { text: input };

                // Post the message to the backend
                await axios.post('/api/chat/messages', userMessage);

                // Immediately reflect the message on the UI
                setMessages([...messages, { sender: 'You', text: input }]);

                // Clear the input field
                setInput('');
            }
        } catch (error) {
            setError('Failed to send message.');
        }
    };

    return (
        <div className="chat-wrapper">
            <header>
                <nav>
                    <button onClick={() => console.log('Navigating to previous chats')}>Previous Chats</button>
                    <button onClick={() => console.log('Logging out')}>Logout</button>
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









