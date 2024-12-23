package senla.model;

import java.math.BigDecimal;

public class FamilyBudget {

    private final int familyId;
    private final BigDecimal balance;

    private FamilyBudget(Builder builder) {
        this.familyId = builder.familyId;
        this.balance = builder.balance;
    }

    public static class Builder {
        private int familyId;
        private BigDecimal balance;

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public FamilyBudget build() {
            return new FamilyBudget(this);
        }
    }
    public int getFamilyId() {
        return familyId;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "FamilyBudget{" +
                "familyId=" + familyId +
                ", balance=" + balance +
                '}';
    }
}
