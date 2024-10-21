/*package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ChatSession;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatSessionFactoryTest {

    @Test
    void buildChatSession() {
        // Create a ChatSession using the factory
        ChatSession chatSession = ChatSessionFactory.createChatSession(123,
                LocalDateTime.of(2024, 3, 1, 10, 0),
                LocalDateTime.of(2024, 3, 31, 18, 0));

        // Assert that the created ChatSession object is not null
        assertNotNull(chatSession);

        // Assert that each field is set correctly
        assertEquals(123, chatSession.getUserId());
        assertEquals(LocalDateTime.of(2024, 3, 1, 10, 0), chatSession.getStartTime());
        assertEquals(LocalDateTime.of(2024, 3, 31, 18, 0), chatSession.getEndTime());

        // Output the ChatSession object to the console
        System.out.println(chatSession);
    }
}*/
