package senla.model;

import java.time.LocalDateTime;

public class Reminder {

    private final int reminderId;
    private final int familyId;
    private final String description;
    private final LocalDateTime reminderDate;
    private final String status;
    private final LocalDateTime createdAt;

    private Reminder(Builder builder) {
        this.reminderId = builder.reminderId;
        this.familyId = builder.familyId;
        this.description = builder.description;
        this.reminderDate = builder.reminderDate;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int reminderId;
        private int familyId;
        private String description;
        private LocalDateTime reminderDate;
        private String status;
        private LocalDateTime createdAt;

        public Builder reminderId(int reminderId) {
            this.reminderId = reminderId;
            return this;
        }

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder reminderDate(LocalDateTime reminderDate) {
            this.reminderDate = reminderDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Reminder build() {
            return new Reminder(this);
        }
    }

    public int getReminderId() {
        return reminderId;
    }
    public int getFamilyId() {
        return familyId;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getReminderDate() {
        return reminderDate;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", familyId=" + familyId +
                ", description='" + description + '\'' +
                ", reminderDate=" + reminderDate +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
