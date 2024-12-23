package senla.service.impl;

import senla.dao.ReportDao;
import senla.dao.impl.ReportDaoImpl;
import senla.model.Report;
import senla.service.ReportService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReportServiceImpl implements ReportService {
    private static ReportService instance;
    private final ReportDao reportDao;

    private ReportServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.reportDao = new ReportDaoImpl(connection);
    }

    public static synchronized ReportService getInstance() {
        if (instance == null) {
            instance = new ReportServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Report> getAllReports() {
        try {
            return reportDao.findAllReports();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all reports", e);
        }
    }

    @Override
    public Optional<Report> getReportById(int id) {
        try {
            return Optional.ofNullable(reportDao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting report by ID", e);
        }
    }

    @Override
    public void createReport(Report report) {
        try {
            reportDao.create(report);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating report", e);
        }
    }

    @Override
    public void updateReport(Report report) {
        try {
            reportDao.update(report);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating report", e);
        }
    }

    @Override
    public void deleteReport(int id) {
        try {
            reportDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting report", e);
        }
    }
}
