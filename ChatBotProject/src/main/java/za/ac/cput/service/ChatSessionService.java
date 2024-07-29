package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.repository.ChatSessionRepository;

import java.util.List;

@Service
public class ChatSessionService implements IService<ChatSession,Long>{

    private final ChatSessionRepository chatSessionRepository;

    public ChatSessionService(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }

    @Override
    public ChatSession create(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    @Override
    public ChatSession read(Long sessionId) {
        return chatSessionRepository.findBySessionId(sessionId);
    }

    @Override
    public ChatSession update(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    public void delete(Long sessionId) {
chatSessionRepository.deleteChatSessionBySessionId(sessionId);
    }

    @Override
    public List<ChatSession> getAll() {
        return chatSessionRepository.findAll();
    }
}
