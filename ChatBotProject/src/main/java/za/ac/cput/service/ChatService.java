package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.ChatSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatSessionRepository chatSessionRepository;

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getMessagesBySessionId(Long sessionId) {
        Optional<ChatSession> session = chatSessionRepository.findById(sessionId);
        return session.map(chatMessageRepository::findBySession).orElse(null);
    }

    public List<ChatMessage> getMessagesBySession(ChatSession session) {
        return chatMessageRepository.findBySession(session);
    }

    public void deleteMessageById(int id) {
        chatMessageRepository.deleteById(id);
    }
}



