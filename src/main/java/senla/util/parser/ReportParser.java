package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Report;

import java.time.LocalDateTime;

public class ReportParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Report createReportFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String reportName = request.getParameter("reportName");
        String description = request.getParameter("description");

        return new Report.Builder()
                .familyId(familyId)
                .reportName(reportName)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Report updateReportFromRequest(HttpServletRequest request, Report existingReport) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String reportName = request.getParameter("reportName");
        String description = request.getParameter("description");

        return new Report.Builder()
                .reportId(existingReport.getReportId())
                .familyId(familyId)
                .reportName(reportName)
                .description(description)
                .createdAt(existingReport.getCreatedAt())
                .build();
    }
}