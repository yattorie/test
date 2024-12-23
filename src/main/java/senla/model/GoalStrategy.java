package senla.model;

import java.time.LocalDateTime;

public class GoalStrategy {

    private final int strategyId;
    private final int goalId;
    private final String description;
    private final String step;
    private final LocalDateTime createdAt;

    private GoalStrategy(Builder builder) {
        this.strategyId = builder.strategyId;
        this.goalId = builder.goalId;
        this.description = builder.description;
        this.step = builder.step;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int strategyId;
        private int goalId;
        private String description;
        private String step;
        private LocalDateTime createdAt;

        public Builder strategyId(int strategyId) {
            this.strategyId = strategyId;
            return this;
        }

        public Builder goalId(int goalId) {
            this.goalId = goalId;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder step(String step) {
            this.step = step;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public GoalStrategy build() {
            return new GoalStrategy(this);
        }
    }

    public int getStrategyId() {
        return strategyId;
    }
    public int getGoalId() {
        return goalId;
    }
    public String getDescription() {
        return description;
    }
    public String getStep() {
        return step;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "GoalStrategy{" +
                "strategyId=" + strategyId +
                ", goalId=" + goalId +
                ", description='" + description + '\'' +
                ", step='" + step + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
