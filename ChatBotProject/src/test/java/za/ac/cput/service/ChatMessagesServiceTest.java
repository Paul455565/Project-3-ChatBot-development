package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.ChatSessionRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Mock
    private ChatSessionRepository chatSessionRepository;

    private ChatMessage chatMessage;
    private ChatSession chatSession;

    //@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //chatSession = new ChatSession(); // Create a mock ChatSession
        chatMessage = new ChatMessage.Builder()
                //.setMessageId(1)
                //.setSession(chatSession)
                .setQuestion("What is your name?")
                .setAnswer("My name is ChatGPT.")
                .setTimeStamp(LocalDate.now())
                .build();
    }

    @Test
    public void createTest() {
        //when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        ChatMessage savedMessage = chatService.create("What is your name?", "My name is ChatGPT.");

        assertNotNull(savedMessage);
        assertEquals("What is your name?", savedMessage.getQuestion());
        assertEquals("My name is ChatGPT.", savedMessage.getAnswer());
        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
    public void readTest() {
        when(chatMessageRepository.findById(1)).thenReturn(Optional.of(chatMessage));

        ChatMessage foundMessage = chatService.read(1);

        assertNotNull(foundMessage);
        assertEquals(chatMessage.getMessageId(), foundMessage.getMessageId());
        verify(chatMessageRepository, times(1)).findById(1);
    }

    @Test
    public void read_ShouldReturnNull_WhenNotExists() {
        when(chatMessageRepository.findById(1)).thenReturn(Optional.empty());

        ChatMessage foundMessage = chatService.read(1);

        assertNull(foundMessage);
        verify(chatMessageRepository, times(1)).findById(1);
    }

    @Test
    public void deleteTest() {
        chatService.delete(1);
        verify(chatMessageRepository, times(1)).deleteById(1);
    }

    @Test
    public void getAll_ShouldReturnListOfChatMessages() {
        //when(chatMessageRepository.findAll()).thenReturn(Collections.singletonList(chatMessage));

        List<ChatMessage> messages = chatService.getAll();

        assertFalse(messages.isEmpty());
        assertNotNull(messages);
        System.out.println("All messages: "+ messages);

    }
}
