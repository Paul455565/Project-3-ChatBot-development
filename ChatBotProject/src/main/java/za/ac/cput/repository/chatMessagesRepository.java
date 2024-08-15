package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.chatMessages;

@Repository
public interface chatMessagesRepository extends JpaRepository<chatMessages, String> {

}
