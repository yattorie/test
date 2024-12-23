package senla.model;

import java.time.LocalDateTime;

public class FamilyMember {

    private final int memberId;
    private final int familyId;
    private final int userId;
    private final Role role;
    private final LocalDateTime joinedAt;

    private FamilyMember(Builder builder) {
        this.memberId = builder.memberId;
        this.familyId = builder.familyId;
        this.userId = builder.userId;
        this.role = builder.role;
        this.joinedAt = builder.joinedAt;
    }

    public static class Builder {
        private int memberId;
        private int familyId;
        private int userId;
        private Role role;
        private LocalDateTime joinedAt;

        public Builder memberId(int memberId) {
            this.memberId = memberId;
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

        public Builder role(String role) {
            this.role = Role.valueOf(role.toUpperCase());
            return this;
        }

        public Builder joinedAt(LocalDateTime joinedAt) {
            this.joinedAt = joinedAt;
            return this;
        }

        public FamilyMember build() {
            return new FamilyMember(this);
        }
    }

    public int getMemberId() {
        return memberId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "memberId=" + memberId +
                ", familyId=" + familyId +
                ", userId=" + userId +
                ", role=" + role +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
