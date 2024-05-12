package za.ac.cput.domain;

import javax.persistence.Id;
import java.util.Date;

public class chatMessages {

    @Id
    private int messageId;
    private int sessionId;
    private String text;
    private Date timeStamp;

    public chatMessages(chatMessages builder) {
        this.messageId = builder.messageId;
        this.sessionId = builder.sessionId;
        this.text = builder.text;
        this.timeStamp = builder.timeStamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getText() {
        return text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "chatMessages{" +
                "messageId=" + messageId +
                ", sessionId=" + sessionId +
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
    public static class Builder {
        private int messageId;
        private int sessionId;
        private String text;
        private Date timeStamp;

        public chatMessages.Builder setMessageId(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public chatMessages.Builder setSessionId(int sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public chatMessages.Builder setText(String text) {
            this.text = text;
            return this;
        }

        public chatMessages.Builder setTimeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }
    }

}
