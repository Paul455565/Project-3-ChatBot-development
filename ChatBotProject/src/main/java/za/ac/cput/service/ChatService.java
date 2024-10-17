package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository,UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }


    public ChatMessage create(String question, String answer,Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ChatMessage chatMessage = new ChatMessage.Builder()
                .setQuestion(question)
                .setAnswer(answer)
                .setTimeStamp(LocalDate.now()).setUser(user)
                .build();

        return chatMessageRepository.save(chatMessage);
    }


    public ChatMessage read(int messageId) {
        return chatMessageRepository.findByMessageId(messageId).orElse(null);
    }


    public ChatMessage update(int messageId, ChatMessage chatMessage) {
        ChatMessage existingMessage = read(messageId);

        if (existingMessage != null) {
            ChatMessage updatedMessage = new ChatMessage.Builder()
                    .copy(existingMessage)
                    .setQuestion(chatMessage.getQuestion())
                    .setAnswer(chatMessage.getAnswer())
                    .setTimeStamp(chatMessage.getTimeStamp())
                    .build();

            return chatMessageRepository.save(updatedMessage);
        }
        return null;
    }
    public List<ChatMessage> getMessageByUserId(int userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        chatMessageRepository.findByUser(user);
        return chatMessageRepository.findByUser(user);
    }


    public void delete(int messageId) {
        chatMessageRepository.deleteById(messageId);
    }


    public List<ChatMessage> getAll() {
        return chatMessageRepository.findAll();
    }
}

