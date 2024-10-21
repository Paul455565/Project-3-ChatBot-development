package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.domain.User;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {
ChatSession findByUserId(User userId);
//ChatSession findByMessageId(ChatMessage message);
ChatSession findBySessionId(int sessionId);
ChatSession deleteChatSessionBySessionId(int sessionId);
}