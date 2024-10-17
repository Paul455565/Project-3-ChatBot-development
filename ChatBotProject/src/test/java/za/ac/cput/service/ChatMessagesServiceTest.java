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
import za.ac.cput.domain.User;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.ChatSessionRepository;
import za.ac.cput.repository.UserRepository;

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


    @Autowired
    private UserRepository userRepository;

    private ChatMessage chatMessage;
    private User user;
    private ChatSession chatSession;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.openMocks(this);
        //chatSession = new ChatSession(); // Create a mock ChatSession
        user = new User.Builder().setName("Malesela")
                .setLastName("Modiba")
                .setEmail("Modiba@email.com")
                .setPassword("Password.123")
                .buildUser();
        user = userRepository.save(user);
        chatMessage = new ChatMessage.Builder()
                //.setMessageId(1)
                //.setSession(chatSession)
                .setQuestion("What is an API?")
                .setAnswer("An application programming interface is a connection between computers or between computer programs. It is a type of software interface, offering a service to other pieces of software.")
                .setTimeStamp(LocalDate.now()).setUser(user)
                .build();

        String question = "Hello, How are you", answer = "Hi, I am good and you.?";
        chatMessage = chatService.create(question,answer,user.getUserId());
        assertNotNull(chatMessage,"Chat should be created not null");
    }

    @Test
     void createTest() {
        //when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        //ChatMessage savedMessage = chatService.create();

        assertNotNull(chatMessage,"Should not be Null");
        System.out.println("Created Message: " + chatMessage);
        //assertEquals("What is an API?", savedMessage.getQuestion());
        //assertEquals("An application programming interface is a connection between computers or between computer programs. " +
                //"It is a type of software interface, offering a service to other pieces of software.", savedMessage.getAnswer());
        //verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
     void readTest() {
        when(chatMessageRepository.findById(1)).thenReturn(Optional.of(chatMessage));

        ChatMessage foundMessage = chatService.read(1);

        assertNotNull(foundMessage);
        assertEquals(chatMessage.getMessageId(), foundMessage.getMessageId());
        verify(chatMessageRepository, times(1)).findById(1);
    }

    @Test
     void read_ShouldReturnNull_WhenNotExists() {
        when(chatMessageRepository.findById(1)).thenReturn(Optional.empty());

        ChatMessage foundMessage = chatService.read(1);

        assertNull(foundMessage);
        verify(chatMessageRepository, times(1)).findById(1);
    }

    @Test
     void deleteTest() {
        chatService.delete(1);
        verify(chatMessageRepository, times(1)).deleteById(1);
    }

    @Test
     void getAll_ShouldReturnListOfChatMessages() {
        //when(chatMessageRepository.findAll()).thenReturn(Collections.singletonList(chatMessage));

        List<ChatMessage> messages = chatService.getAll();

        assertFalse(messages.isEmpty());
        assertNotNull(messages);
        System.out.println("All messages: "+ messages);

    }
    @Test
    void getMessageByUserId(){
        List<ChatMessage> userMessages = chatService.getMessageByUserId(user.getUserId());
        assertNotNull(userMessages,"User messages list should not be null");
        assertFalse(userMessages.isEmpty(),"Customer should have at least one message");
        System.out.println("Chats for User: " + userMessages);
    }
}
