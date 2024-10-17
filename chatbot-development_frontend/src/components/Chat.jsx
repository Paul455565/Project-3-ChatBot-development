import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { createMessage } from './../service/chatService.js';

function Chat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [error, setError] = useState(null);

    const messagesEndRef = useRef(null);
    const navigate = useNavigate();

    const handleSendMessage = async () => {
        if (input.trim() === '') return;


        setMessages([...messages, { sender: 'You', text: input }]);
        const userMessage = input;
        setInput('');

        try {

            const messageData = { question: userMessage, answer: 'Testing', userId: 2 };
            const response = await createMessage(messageData);


            setMessages(prevMessages => [
                ...prevMessages,
                { sender: 'Chatbot', text: response.data.answer }
            ]);
        } catch (error) {
            setError('Failed to send message to the database.');
            console.error('Error sending message:', error);
        }

        messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
    };

    const handleLogout = () => {
        navigate('/');
    };

    const handleViewPreviousChats = () => {
        navigate('/previous-chats');
    };

    return (
        <div className="chat-wrapper">
            {/* Header */}
            <header className="chat-header">
                <nav className="chat-nav">
                    <button onClick={handleViewPreviousChats} className="nav-button">
                        Previous Chats
                    </button>
                    <button onClick={handleLogout} className="nav-button">
                        Sign out
                    </button>
                </nav>
            </header>

            {/* Messages */}
            <div className="messages-container">
                {messages.map((msg, index) => (
                    <div
                        key={index}
                        className={`message-wrapper ${msg.sender === 'You' ? 'user-message' : 'assistant-message'}`}
                    >
                        <div className="message-bubble">
                            <div className="message-sender">
                                {msg.sender}
                            </div>
                            <div className="message-text">
                                {msg.text}
                            </div>
                        </div>
                    </div>
                ))}
                <div ref={messagesEndRef} />
            </div>

            {/* Input Area */}
            <div className="input-container">
                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    onKeyDown={(e) => e.key === 'Enter' && handleSendMessage()}
                    placeholder="Message..."
                    className="message-input" // Ensure this class is styled as shown above
                />
                <button
                    onClick={handleSendMessage}
                    className="send-button"
                    disabled={!input.trim()}
                >
                    Send
                </button>
            </div>
        </div>
    );
}

export default Chat;
