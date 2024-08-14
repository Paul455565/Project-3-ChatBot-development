package za.ac.cput.factory;
import jakarta.persistence.Entity;
import za.ac.cput.domain.chatMessages;
import za.ac.cput.util.Helper;

import java.time.LocalDate;


public class chatMessagesFactory {
    public static chatMessages createchatMessages(int messageId, long sessionId, String text, LocalDate timeStamp){
        if(Helper.isNull(messageId)){
            return null;
        }
        return new chatMessages.Builder()
                .setMessageId(messageId)
                .setSessionId(sessionId)
                .setText(text)
                .setTimeStamp(timeStamp)
                .buildchatMessages();
    }
}
