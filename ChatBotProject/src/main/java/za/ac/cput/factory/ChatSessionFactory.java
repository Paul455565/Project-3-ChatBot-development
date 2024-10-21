package za.ac.cput.factory;

import za.ac.cput.domain.ChatMessage;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class ChatSessionFactory {
    public static ChatSession createChatSession(int sessionId,User userId, ChatMessage question, ChatMessage answer, LocalDateTime date) {
        if (Helper.isIntNotValid(sessionId)
                || userId == null
                || question == null
                || answer == null
                || date == null)
        {

            return null;
        }

        return new ChatSession.Builder()
                .setSessionId(sessionId)
                .setUserId(userId)
                .setQuestion(question)
                .setAnswer(answer)
                .setDate(date)
                .build();
    }
}
