package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.service.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl extends ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    @Override
    public List<ChatMessage> getMessagesBySession(ChatSession session) {
        return chatMessageRepository.findBySession(session);
    }

    @Override
    public void deleteMessageById(int messageId) { // Use int for consistency with repository method
        chatMessageRepository.deleteById(messageId);
    }
}

