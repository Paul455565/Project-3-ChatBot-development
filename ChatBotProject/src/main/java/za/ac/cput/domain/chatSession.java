package za.ac.cput.domain;

import java.util.Date;
import javax.persistence.*;

public class chatSession {

    @Id
    private int sessionId;
    private int UserId;
    private Date startTime;
    private Date endTime;

    public chatSession(chatSession builder) {
        this.sessionId = builder.sessionId;
        this.UserId = builder.UserId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public int getSessionId() {
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

        private int sessionId;
        private int UserId;
        private Date startTime;
        private Date endTime;

        public chatSession.Builder setSessionId(int sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public chatSession.Builder setUserId(int userId) {
            UserId = userId;
            return this;
        }

        public chatSession.Builder setStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public chatSession.Builder setEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }
    }
    public chatSession build(){
        return new chatSession(this);

    }
}
