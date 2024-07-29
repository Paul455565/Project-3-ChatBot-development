package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ChatSession;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {

ChatSession findBySessionId(Long sessionId);
ChatSession deleteChatSessionBySessionId(Long sessionId);
}