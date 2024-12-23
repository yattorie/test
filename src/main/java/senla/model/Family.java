package senla.model;

import java.time.LocalDateTime;

public class Family {

    private final int familyId;
    private final String familyName;
    private final String familyDescription;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Family(Builder builder) {
        this.familyId = builder.familyId;
        this.familyName = builder.familyName;
        this.familyDescription = builder.familyDescription;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private int familyId;
        private String familyName;
        private String familyDescription;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder familyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder familyDescription(String familyDescription) {
            this.familyDescription = familyDescription;
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

        public Family build() {
            return new Family(this);
        }
    }

    public int getFamilyId() { return familyId; }
    public String getFamilyName() { return familyName; }
    public String getFamilyDescription() { return familyDescription; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public String toString() {
        return "Family{" +
                "familyId=" + familyId +
                ", familyName='" + familyName + '\'' +
                ", familyDescription='" + familyDescription + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}

