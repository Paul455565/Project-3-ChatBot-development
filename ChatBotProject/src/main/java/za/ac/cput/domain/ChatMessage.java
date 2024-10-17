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
    @JoinColumn(name= "userID")
    private User user;

    private String question;
    private String answer;
    private LocalDate timeStamp;

    protected ChatMessage() {}

    private ChatMessage(Builder builder) {
        this.messageId = builder.messageId;
        this.question = builder.question;
        this.answer = builder.answer;
        this.timeStamp = builder.timeStamp;
        this.user = builder.user;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }
    public User user(){
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return messageId == that.messageId && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(user,that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, question, answer, timeStamp,user);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId=" + messageId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", timeStamp=" + timeStamp + '\''+
                ",user=" + user +
                '}';
    }

    public static class Builder {
        private int messageId;
        private String question;
        private String answer;
        private LocalDate timeStamp;
        private User user;

        public Builder setMessageId(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder setTimeStamp(LocalDate timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }
        public Builder setUser(User user){
            this.user = user;
            return this;
        }

        public Builder copy(ChatMessage chatMessage) {
            this.messageId = chatMessage.messageId;
            this.question = chatMessage.question;
            this.answer = chatMessage.answer;
            this.timeStamp = chatMessage.timeStamp;
            this.user = chatMessage.user;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }
    }
}
