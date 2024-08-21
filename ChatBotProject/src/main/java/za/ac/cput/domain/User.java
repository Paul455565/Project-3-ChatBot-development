package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private String confirmPassword;

    protected User() {
    }

    public User(Builder builder) {
        this.userId = builder.userId;
        this.email = builder.email;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, confirmPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    // Builder class
    public static class Builder {
        private int userId;
        private String email;
        private String password;
        private String confirmPassword;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder copyUser(User user) {
            this.userId = user.userId;
            this.email = user.email;
            this.password = user.password;
            this.confirmPassword = user.confirmPassword;
            return this;
        }

        public User buildUser() {
            return new User(this);
        }
    }
}
