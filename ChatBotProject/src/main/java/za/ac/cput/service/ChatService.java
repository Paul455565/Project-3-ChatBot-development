package za.ac.cput.service;

import za.ac.cput.domain.chatMessages;

import java.util.List;

public interface ChatService {

    chatMessages saveMessage(chatMessages message);

    List<chatMessages> getMessagesBySessionId(int sessionId);

    void deleteMessageById(Long messageId); // Change parameter to Long
}
