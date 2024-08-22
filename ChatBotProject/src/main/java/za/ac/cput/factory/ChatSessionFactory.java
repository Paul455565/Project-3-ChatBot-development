package za.ac.cput.factory;

import za.ac.cput.domain.ChatSession;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class ChatSessionFactory {
    public static ChatSession createChatSession(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        if (Helper.isNull(userId)) {
            return null;
        }
        return new ChatSession.Builder()
                .setUserId(userId)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .build();
    }
}

