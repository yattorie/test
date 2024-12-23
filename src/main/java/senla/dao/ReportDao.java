package senla.dao;

import senla.model.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao {

    void create(Report report) throws SQLException;

    Report findById(int reportId) throws SQLException;

    List<Report> findAllReports() throws SQLException;

    List<Report> findByFamilyId(int familyId) throws SQLException;

    void update(Report report) throws SQLException;

    void delete(int reportId) throws SQLException;
}
