package za.ac.cput.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.domain.User;
import za.ac.cput.repository.ChatMessageRepository;
import za.ac.cput.repository.ChatSessionRepository;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatSessionService(ChatSessionRepository chatSessionRepository, UserRepository userRepository, ChatMessageRepository chatMessageRepository) {
        this.chatSessionRepository = chatSessionRepository;
        this.userRepository = userRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @Transactional
    public ChatSession create(String email, String question, String answer) {
        // Find user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Invalid email: user not found");
        }

        // Validate input for chat messages
        if (Helper.isNullOrEmpty(question) || Helper.isNullOrEmpty(answer)) {
            throw new IllegalArgumentException("Question or answer cannot be empty");
        }

        // Create question and answer chat messages
        ChatMessage prompt = new ChatMessage.Builder()
                .setQuestion(question)
                .setTimeStamp(LocalDate.from(LocalDateTime.now()))
                .setUser(user)
                .build();
        chatMessageRepository.save(prompt);  // Save question to repository

        ChatMessage response = new ChatMessage.Builder()
                .setAnswer(answer)
                .setTimeStamp(LocalDate.from(LocalDateTime.now()))
                .setUser(user)
                .build();
        chatMessageRepository.save(response);  // Save answer to repository

        // Create and save chat session
        ChatSession chatSession = new ChatSession.Builder()
                .setUserId(user)
                .setQuestion(prompt)
                .setAnswer(response)
                .setDate(LocalDateTime.now())
                .build();

        return chatSessionRepository.save(chatSession);
    }

    public ChatSession read(int sessionId) {
        return chatSessionRepository.findBySessionId(sessionId);
    }

    public ChatSession update(ChatSession chatSession) {
        return chatSessionRepository.save(chatSession);
    }

    public boolean delete(int sessionId) {
        chatSessionRepository.deleteChatSessionBySessionId(sessionId);
        return true;
    }

    public List<ChatSession> getAll() {
        return chatSessionRepository.findAll();
    }
}

