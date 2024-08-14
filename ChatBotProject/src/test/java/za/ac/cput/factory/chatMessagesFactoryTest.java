package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.chatMessages;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

 class chatMessagesFactoryTest {
    @Test
    void BuildchatMessages(){
        chatMessages ChatMessages = chatMessagesFactory.createchatMessages(001,001,"Hello World", LocalDate.of(2024,3,25));
        assertNotNull(ChatMessages);
        System.out.println(ChatMessages);
    }
}
