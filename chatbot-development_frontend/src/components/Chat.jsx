import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { createMessage } from './../service/chatService.js';
import axios from 'axios';
import Header from './Header';  // Import the Header component

function Chat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const [error, setError] = useState(null);

    const messagesEndRef = useRef(null);
    const navigate = useNavigate();

    const handleSendMessage = async () => {
        if (input.trim() === '') return;

        // Add user's message to the message list
        const userMessage = input;
        setMessages([...messages, { sender: 'You', text: userMessage }]);
        setInput('');

        try {
            // Send user message to the Express server to get chatbot response
            const response = await axios.post('http://localhost:5000/api/chat', {
                message: userMessage,
            });

            const chatbotReply = response.data.reply;

            // Append chatbot response to the message list
            setMessages((prevMessages) => [
                ...prevMessages,
                { sender: 'Chat', text: chatbotReply },
            ]);

            // Prepare data for storing in the database
            const messageData = {
                question: userMessage,
                answer: chatbotReply,
                userId: localStorage.getItem(userId),  // Replace this with the actual user ID
            };

            // Store the message and response in the database
            const dbResponse = await createMessage(messageData);
            console.log('Message stored in DB:', dbResponse.data);

        } catch (error) {
            setError('');
            console.error('Error:', error);
        }

        // Scroll to the latest message
        messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
    };

    const handleLogout = () => {
        navigate('/');
    };

    const handleViewPreviousChats = () => {
        navigate('/previous-chats');
    };

    return (
        <div className="chat-wrapper">
            {/* Integrate the Header component */}
            <Header />

            {/*<header className="chat-header">
                <nav className="chat-nav">
                    <button onClick={handleViewPreviousChats} className="nav-button">
                        Previous Chats
                    </button>
                    <button onClick={handleLogout} className="nav-button">
                        Sign out
                    </button>
                </nav>
            </header>*/}

            <div className="messages-container">
                {messages.map((msg, index) => (
                    <div
                        key={index}
                        className={`message-wrapper ${msg.sender === 'You' ? 'user-message' : 'assistant-message'}`}
                    >
                        <div className="message-bubble">
                            <div className="message-sender">{msg.sender}</div>
                            <div className="message-text">{msg.text}</div>
                        </div>
                    </div>
                ))}
                <div ref={messagesEndRef} />
            </div>

            <div className="input-container">
                {error && <div className="error-message">{error}</div>}
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
    );
}

export default Chat;
