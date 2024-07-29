package za.ac.cput.factory;

import za.ac.cput.domain.ChatSession;
import za.ac.cput.util.Helper;

import java.util.Date;

public class ChatSessonFactory {
    public static ChatSession createChatSession(int userId, Date startTime, Date endTime){
                    if (Helper.isNull(userId)){
                        return null;
                    }
                    return new ChatSession.Builder()
                .setUserId(userId)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .buildChatSession();
    }
}
