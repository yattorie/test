package senla.model;

import java.time.LocalDateTime;

public class CategoryTag {

    private final int categoryTagId;
    private final int categoryId;
    private final int tagId;
    private final LocalDateTime createdAt;

    private CategoryTag(Builder builder) {
        this.categoryTagId = builder.categoryTagId;
        this.categoryId = builder.categoryId;
        this.tagId = builder.tagId;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int categoryTagId;
        private int categoryId;
        private int tagId;
        private LocalDateTime createdAt;

        public Builder categoryTagId(int categoryTagId) {
            this.categoryTagId = categoryTagId;
            return this;
        }

        public Builder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder tagId(int tagId) {
            this.tagId = tagId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CategoryTag build() {
            return new CategoryTag(this);
        }
    }

    public int getCategoryTagId() {
        return categoryTagId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public int getTagId() {
        return tagId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "CategoryTag{" +
                "categoryTagId=" + categoryTagId +
                ", categoryId=" + categoryId +
                ", tagId=" + tagId +
                ", createdAt=" + createdAt +
                '}';
    }
}
