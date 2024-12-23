package senla.model;

import java.time.LocalDateTime;

public class Tag {

    private final int tagId;
    private final String tagName;
    private final LocalDateTime createdAt;

    private Tag(Builder builder) {
        this.tagId = builder.tagId;
        this.tagName = builder.tagName;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int tagId;
        private String tagName;
        private LocalDateTime createdAt;

        public Builder tagId(int tagId) {
            this.tagId = tagId;
            return this;
        }

        public Builder tagName(String tagName) {
            this.tagName = tagName;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }

    public int getTagId() {
        return tagId;
    }
    public String getTagName() {
        return tagName;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
