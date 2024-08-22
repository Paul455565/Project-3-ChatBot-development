package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.service.impl.ChatServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatMessagesServiceTest {

    @Mock
    private ChatMessageRepository repository;

    @InjectMocks
    private ChatServiceImpl service;

    private ChatMessage message;
    private ChatSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a ChatSession object
        session = new ChatSession.Builder()
                .setSessionId(1L)
                .setUserId(1)
                .setStartTime(LocalDateTime.of(2024, 3, 1, 10, 0))
                .setEndTime(LocalDateTime.of(2024, 3, 31, 18, 0))
                .build();

        // Create a ChatMessage object
        message = new ChatMessage.Builder()
                .setMessageId(1)
                .setSession(session) // Use ChatSession object
                .setText("Hello")
                .setTimeStamp(LocalDateTime.of(2024, 3, 25, 10, 0)) // Use LocalDateTime directly
                .build();
    }

    @Test
    void testSaveMessage() {
        when(repository.save(any(ChatMessage.class))).thenReturn(message);
        ChatMessage savedMessage = service.saveMessage(message);
        assertEquals(message, savedMessage);
        verify(repository, times(1)).save(message);
    }

    @Test
    void testGetMessagesBySessionId() {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(message);
        when(repository.findBySessionId(1L)).thenReturn(messages);
        //Test Change

        List<ChatMessage> retrievedMessages = service.getMessagesBySessionId(1L);
        assertEquals(1, retrievedMessages.size());
        assertEquals(message, retrievedMessages.get(0));
        verify(repository, times(1)).findBySessionId(1L);
    }

    @Test
    void testDeleteMessageById() {
        doNothing().when(repository).deleteById(1);
        service.deleteMessageById(1);
        verify(repository, times(1)).deleteById(1);
    }
}


