package senla.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goal {

    private final int goalId;
    private final int familyId;
    private final String goalName;
    private final String description;
    private final BigDecimal targetAmount;
    private final LocalDate dueDate;
    private final LocalDateTime createdAt;

    private Goal(Builder builder) {
        this.goalId = builder.goalId;
        this.familyId = builder.familyId;
        this.goalName = builder.goalName;
        this.description = builder.description;
        this.targetAmount = builder.targetAmount;
        this.dueDate = builder.dueDate;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int goalId;
        private int familyId;
        private String goalName;
        private String description;
        private BigDecimal targetAmount;
        private LocalDate dueDate;
        private LocalDateTime createdAt;

        public Builder goalId(int goalId) {
            this.goalId = goalId;
            return this;
        }

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder goalName(String goalName) {
            this.goalName = goalName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder targetAmount(BigDecimal targetAmount) {
            this.targetAmount = targetAmount;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Goal build() {
            return new Goal(this);
        }
    }

    public int getGoalId() {
        return goalId;
    }
    public int getFamilyId() {
        return familyId;
    }
    public String getGoalName() {
        return goalName;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", familyId=" + familyId +
                ", goalName='" + goalName + '\'' +
                ", description='" + description + '\'' +
                ", targetAmount=" + targetAmount +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                '}';
    }
}
