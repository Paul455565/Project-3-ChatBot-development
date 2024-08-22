package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findBySession(ChatSession session);

    // Or, query by the sessionId field within ChatSession
    List<ChatMessage> findBySession_SessionId(Long sessionId);
}

