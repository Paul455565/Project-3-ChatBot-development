package za.ac.cput.factory;

import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession; // Correct import
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChatMessageFactory {
    public static ChatMessage createChatMessage(int messageId, ChatSession session, String text, LocalDateTime timeStamp) {
        // Validate the messageId; if it's invalid, return null
        if (Helper.isNull(messageId)) {
            return null;
        }

        // Create and return a new ChatMessage using the builder
        return new ChatMessage.Builder()
                .setMessageId(messageId)
                .setSession(session) // Ensure this method name matches the ChatMessage Builder
                .setText(text)
                .setTimeStamp(LocalDate.from(timeStamp))
                .build();
    }
}

