package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatMessageFactoryTest {

    @Test
    void buildChatMessage() {
        // Create a ChatSession object with LocalDateTime
        ChatSession session = new ChatSession.Builder()
                .setSessionId(12345L)
                .setUserId(1)
                .setStartTime(LocalDateTime.of(2024, 3, 1, 10, 0)) // Use LocalDateTime
                .setEndTime(LocalDateTime.of(2024, 3, 31, 18, 0))   // Use LocalDateTime
                .build();

        // Create a ChatMessage using the factory
        ChatMessage chatMessage = ChatMessageFactory.createChatMessage(1, session, "Hello World", LocalDateTime.of(2024, 3, 25, 10, 0));

        // Assert that the created ChatMessage object is not null
        assertNotNull(chatMessage);

        // Assert that each field is set correctly
        assertEquals(1, chatMessage.getMessageId());
        assertEquals(session, chatMessage.getSession()); // Ensure the getter method name matches
        assertEquals("Hello World", chatMessage.getText());
        assertEquals(LocalDateTime.of(2024, 3, 25, 10, 0), chatMessage.getTimeStamp());

        // Output the ChatMessage object to the console
        System.out.println(chatMessage);
    }
}






