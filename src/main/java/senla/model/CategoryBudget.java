package senla.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CategoryBudget {

    private final int categoryBudgetId;
    private final int familyId;
    private final int categoryId;
    private final BigDecimal budgetAmount;
    private final BigDecimal spentAmount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private CategoryBudget(Builder builder) {
        this.categoryBudgetId = builder.categoryBudgetId;
        this.familyId = builder.familyId;
        this.categoryId = builder.categoryId;
        this.budgetAmount = builder.budgetAmount;
        this.spentAmount = builder.spentAmount;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private int categoryBudgetId;
        private int familyId;
        private int categoryId;
        private BigDecimal budgetAmount;
        private BigDecimal spentAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder categoryBudgetId(int categoryBudgetId) {
            this.categoryBudgetId = categoryBudgetId;
            return this;
        }

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder budgetAmount(BigDecimal budgetAmount) {
            this.budgetAmount = budgetAmount;
            return this;
        }

        public Builder spentAmount(BigDecimal spentAmount) {
            this.spentAmount = spentAmount;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CategoryBudget build() {
            return new CategoryBudget(this);
        }
    }

    public int getCategoryBudgetId() {
        return categoryBudgetId;
    }
    public int getFamilyId() {
        return familyId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }
    public BigDecimal getSpentAmount() {
        return spentAmount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "CategoryBudget{" +
                "categoryBudgetId=" + categoryBudgetId +
                ", familyId=" + familyId +
                ", categoryId=" + categoryId +
                ", budgetAmount=" + budgetAmount +
                ", spentAmount=" + spentAmount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
