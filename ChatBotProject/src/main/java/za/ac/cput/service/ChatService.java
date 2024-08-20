package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.chatMessages;
import za.ac.cput.repository.chatMessagesRepository;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private chatMessagesRepository chatMessagesRepository;

    public chatMessages saveMessage(chatMessages chatMessage) {
        return chatMessagesRepository.save(chatMessage);
    }

    public List<chatMessages> getMessagesBySessionId(int sessionId) {
        return chatMessagesRepository.findBySessionId(sessionId);
    }

    public void deleteMessageById(Long id) {
        chatMessagesRepository.deleteById(String.valueOf(id));
    }
}

