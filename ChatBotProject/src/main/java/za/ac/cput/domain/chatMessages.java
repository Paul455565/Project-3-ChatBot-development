package za.ac.cput.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
@Entity
public class chatMessages {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int messageId;
    @ManyToOne
    @JoinColumn(name = "sessionId")
    private long sessionId;
    private String text;
    private LocalDate timeStamp;

    public chatMessages(chatMessages builder) {
        this.messageId = builder.messageId;
        this.sessionId = builder.sessionId;
        this.text = builder.text;
        this.timeStamp = builder.timeStamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getText() {
        return text;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        chatMessages that = (chatMessages) o;
        return messageId == that.messageId && sessionId == that.sessionId && Objects.equals(text, that.text) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, sessionId, text, timeStamp);
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
        private long sessionId;
        private String text;
        private LocalDate timeStamp;

        public chatMessages.Builder setMessageId(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public chatMessages.Builder setSessionId(long sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public chatMessages.Builder setText(String text) {
            this.text = text;
            return this;
        }

        public chatMessages.Builder setTimeStamp(LocalDate timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }
        public Builder copychatMessages(chatMessages messages){
            this.messageId = messages.messageId;
            this.sessionId = messages.sessionId;
            this.text = messages.text;
            this.timeStamp = messages.timeStamp;
            return this;
        }
        public chatMessages buildchatMessages(){
            return new chatMessages(this.buildchatMessages());
        }
    }

}
