package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    //List<ChatMessage> findBySession(ChatSession session);
    Optional<ChatMessage> findByMessageId(int messageId);

    List<ChatMessage> findByQuestion(String question);


    // Or, query by the sessionId field within ChatSession
    //List<ChatMessage> findBySession_SessionId(Long sessionId);

    //Object findBySessionId(long l);
}

