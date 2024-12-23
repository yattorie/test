package senla.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserProfile {

    private final int userProfileId;
    private final int userId;
    private final LocalDate dateOfBirth;
    private final String familyStatus;
    private final String gender;
    private final LocalDateTime createdAt;

    private UserProfile(Builder builder) {
        this.userProfileId = builder.userProfileId;
        this.userId = builder.userId;
        this.dateOfBirth = builder.dateOfBirth;
        this.familyStatus = builder.familyStatus;
        this.gender = builder.gender;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int userProfileId;
        private int userId;
        private LocalDate dateOfBirth;
        private String familyStatus;
        private String gender;
        private LocalDateTime createdAt;

        public Builder userProfileId(int userProfileId) {
            this.userProfileId = userProfileId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder familyStatus(String familyStatus) {
            this.familyStatus = familyStatus;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    public int getUserProfileId() {
        return userProfileId;
    }
    public int getUserId() {
        return userId;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getFamilyStatus() {
        return familyStatus;
    }
    public String getGender() {
        return gender;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userProfileId=" + userProfileId +
                ", userId=" + userId +
                ", dateOfBirth=" + dateOfBirth +
                ", familyStatus='" + familyStatus + '\'' +
                ", gender='" + gender + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
