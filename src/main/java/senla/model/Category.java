package senla.model;

import java.time.LocalDateTime;

public class Category {

    private final int categoryId;
    private final String categoryName;
    private final String type;
    private final int parentCategoryId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int createdBy;

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.type = builder.type;
        this.parentCategoryId = builder.parentCategoryId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.createdBy = builder.createdBy;
    }

    public static class Builder {
        private int categoryId;
        private String categoryName;
        private String type;
        private Integer parentCategoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private int createdBy;

        public Builder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder parentCategoryId(Integer parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
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

        public Builder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    public int getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public String getType() {
        return type;
    }
    public int getParentCategoryId() {
        return parentCategoryId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public int getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", type='" + type + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createdBy=" + createdBy +
                '}';
    }
}

