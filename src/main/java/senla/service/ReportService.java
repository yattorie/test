package senla.service;

import senla.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<Report> getAllReports();

    Optional<Report> getReportById(int id);

    void createReport(Report report);

    void updateReport(Report report);

    void deleteReport(int id);
}
