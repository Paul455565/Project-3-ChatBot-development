 //src/chatService.js

const API_BASE_URL = 'http://localhost:8080/api/chat/messages'; // Spring Boot backend URL

export const saveMessage = async (message) => {
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(message),
        });

        if (!response.ok) {
            throw new Error('Failed to save message');
        }

        return await response.json();
    } catch (error) {
        console.error('Error saving message:', error);
        throw error;
    }
};

export const getMessagesBySessionId = async (sessionId) => {
    try {
        const response = await fetch(`${API_BASE_URL}/session/${sessionId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Failed to fetch messages');
        }

        return await response.json();
    } catch (error) {
        console.error('Error fetching messages:', error);
        throw error;
    }
};
