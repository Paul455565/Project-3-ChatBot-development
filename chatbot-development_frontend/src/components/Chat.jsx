import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { createMessage } from './../service/chatService.js';
import './Chat.css'; // Ensure to import the CSS file

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
        <div className="chat-layout">
            {/* Previous Chats Sidebar */}
            <div className="previous-chats">
                <header className="sidebar-header">
                    <h3>Previous Chats</h3>
                    <button onClick={handleLogout} className="nav-button">Sign out</button>
                </header>
                <div className="chat-list">
                    {/* Render previous chats here */}
                    <p onClick={handleViewPreviousChats}>Chat 1</p>
                    <p onClick={handleViewPreviousChats}>Chat 2</p>
                    {/* Add more chat items as needed */}
                </div>
            </div>

            {/* Messages Container */}
            <div className="messages-container">
                <div className="messages">
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
                        className="message-input"
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
        </div>
    );
}

export default Chat;
