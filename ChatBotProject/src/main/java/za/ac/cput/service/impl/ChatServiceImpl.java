package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.chatMessages;
import za.ac.cput.repository.chatMessagesRepository;
import za.ac.cput.service.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final chatMessagesRepository chatMessagesRepository;

    @Autowired
    public ChatServiceImpl(chatMessagesRepository chatMessagesRepository) {
        this.chatMessagesRepository = chatMessagesRepository;
    }

    @Override
    public chatMessages saveMessage(chatMessages message) {
        return chatMessagesRepository.save(message);
    }

    @Override
    public List<chatMessages> getMessagesBySessionId(int sessionId) {
        return null;
    }

    //@Override
    //public List<chatMessages> getMessagesBymessageId(int messageId) {
        //return chatMessagesRepository.findBymessageId(messageId);
    //}

    @Override
    public void deleteMessageById(Long messageId) { // Match interface method signature
        chatMessagesRepository.deleteById(String.valueOf(messageId));
    }
}
