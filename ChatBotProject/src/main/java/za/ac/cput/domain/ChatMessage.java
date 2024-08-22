package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ChatSession session; // Changed to ChatSession

    private String text;
    private LocalDate timeStamp;

    protected ChatMessage() {}

    private ChatMessage(Builder builder) {
        this.messageId = builder.messageId;
        this.session = builder.session; // Changed to ChatSession
        this.text = builder.text;
        this.timeStamp = builder.timeStamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public ChatSession getSession() { // Changed return type to ChatSession
        return session;
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
        ChatMessage that = (ChatMessage) o;
        return messageId == that.messageId && Objects.equals(session, that.session) && Objects.equals(text, that.text) && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, session, text, timeStamp);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId=" + messageId +
                ", session=" + session + // Changed to ChatSession
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public static class Builder {
        private int messageId;
        private ChatSession session; // Changed to ChatSession
        private String text;
        private LocalDate timeStamp;

        public Builder setMessageId(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setSession(ChatSession session) { // Changed to ChatSession
            this.session = session;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setTimeStamp(LocalDate timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder copy(ChatMessage chatMessage) {
            this.messageId = chatMessage.messageId;
            this.session = chatMessage.session; // Changed to ChatSession
            this.text = chatMessage.text;
            this.timeStamp = chatMessage.timeStamp;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }
    }
}
