package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.chatMessages;
import za.ac.cput.repository.chatMessagesRepository;
import za.ac.cput.service.impl.ChatServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatMessagesServiceTest {

    @Mock
    private chatMessagesRepository repository;

    @InjectMocks
    private ChatServiceImpl service;

    private chatMessages message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        message = new chatMessages.Builder()
                .setMessageId(1) // Changed to Long
                .setSessionId(1)
                .setText("Hello")
                .setTimeStamp(2024,3,25)
                .build();
    }

    @Test
    void testSaveMessage() {
        when(repository.save(any(chatMessages.class))).thenReturn(message);
        chatMessages savedMessage = service.saveMessage(message);
        assertEquals(message, savedMessage);
        verify(repository, times(1)).save(message);
    }

    @Test
    void testGetMessagesBySessionId() {
        List<chatMessages> messages = new ArrayList<>();
        messages.add(message);
        when(repository.getMessagesByMessageIdId(1)).thenReturn(messages); // Corrected method call

        List<chatMessages> retrievedMessages = service.getMessagesBySessionId(1);
        assertEquals(1, retrievedMessages.size());
        assertEquals(message, retrievedMessages.get(0));
        verify(repository, times(1)).getMessagesByMessageIdId(1); // Corrected method call
    }

    @Test
    void testDeleteMessageById() {
        doNothing().when(repository).deleteById(String.valueOf(anyLong())); // Changed to anyLong()
        service.deleteMessageById(1L); // Changed to Long
        verify(repository, times(1)).deleteById(1); // Changed to Long
    }
}
