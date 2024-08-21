import React, { useState } from 'react';
// import './Chat.css';

function Chat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');
    const
        [error, setError] = useState(null);

    const handleSendMessage = () => {
        try {
            if (input.trim() !== '') {
                const userMessage = {sender: 'You', text: input};
                const botMessage = {sender: 'Bot', text: `Echo: ${input}`};

                setMessages([...messages, userMessage, botMessage]);
                setInput('');
            }
        }catch (error) {
            setError(error.message);
        }
    };
    const handlePreviousChats = () => {
        // Implement logic for navigating to previous chats
        console.log('Navigating to previous chats');
    };

    const handleLogout = () => {
        // Implement logout logic
        console.log('Logging out');
    };

    return (
        <div className="chat-wrapper">
            <header>
                <nav>
                    <button onClick={handlePreviousChats}>Previous Chats</button>
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




