package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Conversation")
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "prompt")
    private ChatMessage question;

    @ManyToOne
    @JoinColumn(name = "response")
    private ChatMessage answer;

    private LocalDateTime date;

    // Default constructor
    public ChatSession() {}

    // Private constructor for Builder
    private ChatSession(Builder builder) {
        this.sessionId = builder.sessionId;
        this.userId = builder.userId;
        this.question = builder.question;
        this.answer = builder.answer;
        this.date = builder.date;
    }

    public int getSessionId() {
        return sessionId;
    }

    public User getUserId() {
        return userId;
    }

    public ChatMessage getQuestion() {
        return question;
    }

    public ChatMessage getAnswer() {
        return answer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatSession that)) return false;
        return sessionId == that.sessionId && Objects.equals(userId, that.userId) && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId, question, answer, date);
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "sessionId=" + sessionId +
                ", userId=" + userId +
                ", question=" + question +
                ", answer=" + answer +
                ", date=" + date +
                '}';
    }

    // Builder class
    public static class Builder {

        private int sessionId;
        private User userId;
        private ChatMessage question;
        private ChatMessage answer;
        private LocalDateTime date;

        public Builder setSessionId(int sessionId) {  // Corrected to int
            this.sessionId = sessionId;
            return this;
        }

        public Builder setUserId(User userId) {
            this.userId = userId;
            return this;
        }

        public Builder setQuestion(ChatMessage question) {
            this.question = question;
            return this;
        }

        public Builder setAnswer(ChatMessage answer) {
            this.answer = answer;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder copyChatSession(ChatSession session) {
            this.sessionId = session.sessionId;
            this.userId = session.userId;
            this.question = session.question;
            this.answer = session.answer;
            this.date = session.date;
            return this;
        }

        public ChatSession build() {
            return new ChatSession(this);
        }
    }
}
