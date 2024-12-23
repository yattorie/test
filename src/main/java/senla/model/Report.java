package senla.model;

import java.time.LocalDateTime;

public class Report {

    private final int reportId;
    private final int familyId;
    private final String reportName;
    private final String description;
    private final LocalDateTime createdAt;

    private Report(Builder builder) {
        this.reportId = builder.reportId;
        this.familyId = builder.familyId;
        this.reportName = builder.reportName;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private int reportId;
        private int familyId;
        private String reportName;
        private String description;
        private LocalDateTime createdAt;

        public Builder reportId(int reportId) {
            this.reportId = reportId;
            return this;
        }

        public Builder familyId(int familyId) {
            this.familyId = familyId;
            return this;
        }

        public Builder reportName(String reportName) {
            this.reportName = reportName;
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

        public Report build() {
            return new Report(this);
        }
    }

    public int getReportId() {
        return reportId;
    }
    public int getFamilyId() {
        return familyId;
    }
    public String getReportName() {
        return reportName;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", familyId=" + familyId +
                ", reportName='" + reportName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
