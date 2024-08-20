package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.chatMessages;

import java.util.List;

@Repository
public interface chatMessagesRepository extends JpaRepository<chatMessages, String> {
    chatMessages saveMessage(chatMessages message);

    List<chatMessages> getMessagesByMessageIdId(int messageId);

    void deleteMessageById(Long messageId);
}