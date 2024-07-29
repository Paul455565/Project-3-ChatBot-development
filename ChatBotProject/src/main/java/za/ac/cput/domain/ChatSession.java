package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "C_sessions")
public class ChatSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    private int UserId;
    private Date startTime;
    private Date endTime;

    public ChatSession(ChatSession builder) {
        this.sessionId = builder.sessionId;
        this.UserId = builder.UserId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public ChatSession() {}

    public Long getSessionId() {
        return sessionId;
    }

    public int getUserId() {
        return UserId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "chatSession{" +
                "sessionId=" + sessionId +
                ", UserId=" + UserId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public static class Builder {

        private Long sessionId;
        private int UserId;
        private Date startTime;
        private Date endTime;

        public Builder setSessionId(Long sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder setUserId(int userId) {
            this.UserId = userId;
            return this;
        }

        public Builder setStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }
        public Builder copyChatSession(ChatSession session) {
            this.sessionId = session.sessionId;
            this.UserId = session.UserId;
            this.startTime = session.startTime;
            this.endTime = session.endTime;
            return this;
        }
        public ChatSession buildChatSession(){
            return new ChatSession(this.buildChatSession());

        }
    }

}
