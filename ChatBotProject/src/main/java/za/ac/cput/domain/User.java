package za.ac.cput.domain;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;;

    public User(User builder) {
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        private int userId;
        private String userName;
        private String email;
        private String password;

        public User.Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public User.Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public User.Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User.Builder setPassword(String password) {
            this.password = password;
            return this;
        }

    }

    public User build(){
        return new User(this);

    }

}
