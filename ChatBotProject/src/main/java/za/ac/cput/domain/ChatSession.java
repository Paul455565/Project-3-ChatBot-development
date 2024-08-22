package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "C_sessions")
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    private int userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Default constructor
    public ChatSession() {}

    // Private constructor for Builder
    private ChatSession(Builder builder) {
        this.sessionId = builder.sessionId;
        this.userId = builder.userId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "sessionId=" + sessionId +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    // Builder class
    public static class Builder {

        private Long sessionId;
        private int userId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder setSessionId(Long sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder copyChatSession(ChatSession session) {
            this.sessionId = session.sessionId;
            this.userId = session.userId;
            this.startTime = session.startTime;
            this.endTime = session.endTime;
            return this;
        }

        public ChatSession build() {
            return new ChatSession(this);
        }
    }
}

