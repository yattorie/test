package senla.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final int transactionId;
    private final int familyId;
    private final int userId;
    private final int categoryId;
    private final BigDecimal amount;
    private final String type;
    private final String description;
    private final LocalDateTime createdAt;

    private Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.familyId = builder.familyId;
        this.userId = builder.userId;
        this.categoryId = builder.categoryId;
        this.amount = builder.amount;
        this.type = builder.type;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int transactionId;
        private int familyId;
        private int userId;
        private int categoryId;
        private BigDecimal amount;
        private String type;
        private String description;
        private LocalDateTime createdAt;

        public Builder transactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

    public int getTransactionId() { return transactionId; }
    public int getFamilyId() { return familyId; }
    public int getUserId() { return userId; }
    public int getCategoryId() { return categoryId; }
    public BigDecimal getAmount() { return amount; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", familyId=" + familyId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

