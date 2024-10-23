package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.ChatSessionRepository;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatSessionServiceTest {

    @Mock
    private ChatSessionRepository chatSessionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatSessionService chatSessionService;

    private ChatSession chatSession;
    private User user;
    private ChatMessage question;
    private ChatMessage answer;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);

        user = new User.Builder().setEmail("TestEmail@gmail.com").setName("Malesela").setLastName("Modiba").setPassword("Password?123").buildUser();// Assume this has been initialized with necessary fields
        question = new ChatMessage.Builder()
                .setQuestion("What is AI?")
                .setTimeStamp(LocalDateTime.now().toLocalDate())
                .setUser(user)
                .build();
        answer = new ChatMessage.Builder()
                .setAnswer("AI stands for Artificial Intelligence.")
                .setTimeStamp(LocalDateTime.now().toLocalDate())
                .setUser(user)
                .build();

        chatSession = new ChatSession.Builder()
                .setUserId(user)
                .setQuestion(question)
                .setAnswer(answer)
                .setDate(LocalDateTime.now())
                .build();
    }

    @Test
    void create() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(question, answer);
        when(chatSessionRepository.save(any(ChatSession.class))).thenReturn(chatSession);

        ChatSession createdSession = chatSessionService.create(email, "What is AI?", "AI stands for Artificial Intelligence.");

        assertNotNull(createdSession);
        assertEquals(chatSession.getUserId(), createdSession.getUserId());
        assertEquals(chatSession.getQuestion(), createdSession.getQuestion());
        assertEquals(chatSession.getAnswer(), createdSession.getAnswer());
        verify(chatSessionRepository, times(1)).save(any(ChatSession.class));
    }

    @Test
    void read() {
        int sessionId = 1;
        when(chatSessionRepository.findBySessionId(sessionId)).thenReturn(chatSession);

        ChatSession foundSession = chatSessionService.read(sessionId);

        assertNotNull(foundSession);
        assertEquals(chatSession.getSessionId(), foundSession.getSessionId());
        verify(chatSessionRepository, times(1)).findBySessionId(sessionId);
    }

    @Test
    void update() {
        when(chatSessionRepository.save(any(ChatSession.class))).thenReturn(chatSession);

        ChatSession updatedSession = chatSessionService.update(chatSession);

        assertNotNull(updatedSession);
        assertEquals(chatSession.getSessionId(), updatedSession.getSessionId());
        verify(chatSessionRepository, times(1)).save(any(ChatSession.class));
    }

    @Test
    void delete() {
        int sessionId = 1;
        doNothing().when(chatSessionRepository).deleteChatSessionBySessionId(sessionId);

        boolean isDeleted = chatSessionService.delete(sessionId);

        assertTrue(isDeleted);
        verify(chatSessionRepository, times(1)).deleteChatSessionBySessionId(sessionId);
    }

    @Test
    void getAll() {
        when(chatSessionRepository.findAll()).thenReturn(Arrays.asList(chatSession));

        List<ChatSession> allSessions = chatSessionService.getAll();

        assertNotNull(allSessions);
        assertEquals(1, allSessions.size());
        verify(chatSessionRepository, times(1)).findAll();
    }
}
