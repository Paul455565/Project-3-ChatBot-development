package za.ac.cput.factory;

import za.ac.cput.domain.ChatMessage;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChatMessageFactory {
    public static ChatMessage buildChatMessage(int messageId, String question, String answer, LocalDateTime timeStamp) {

        if (Helper.isIntNotValid(messageId)
        || question == null
        || answer == null
        ||timeStamp == null) {
            return null;
        }

        // Create and return a new ChatMessage using the builder
        return new ChatMessage.Builder()
                .setMessageId(messageId)
                .setQuestion(question)
                .setAnswer(answer)
                .setTimeStamp(LocalDate.from(timeStamp))
                .build();
    }
}
