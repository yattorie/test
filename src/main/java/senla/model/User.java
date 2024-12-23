package senla.model;

import java.time.LocalDateTime;

public class User {

    private final int userId;
    private final String username;
    private final String email;
    private final String password;
    private final LocalDateTime createdAt;

    private User(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int userId;
        private String username;
        private String email;
        private String password;
        private LocalDateTime createdAt;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

