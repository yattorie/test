package senla.dao.impl;

import senla.dao.ReportDao;
import senla.exception.DaoException;
import senla.model.Report;
import senla.util.ErrorMessages;
import senla.util.mapper.ReportMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {

    public static final String SQL_CREATE = """
            INSERT INTO reports (family_id, report_name, description, created_at)
            VALUES (?, ?, ?, ?);
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM reports;
            """;

    public static final String SQL_FIND_BY_ID = """
            SELECT * FROM reports WHERE report_id = ?;
            """;

    public static final String SQL_FIND_BY_FAMILY_ID = """
            SELECT * FROM reports WHERE family_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM reports WHERE report_id = ?;
            """;

    public static final String SQL_UPDATE = """
            UPDATE reports 
            SET family_id = ?, report_name = ?, description = ?, created_at = ?
            WHERE reminder_id = ?;
            """;

    private final Connection connection;

    public ReportDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Report report) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, report.getFamilyId());
            preparedStatement.setString(2, report.getReportName());
            preparedStatement.setString(3, report.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(report.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public Report findById(int reportId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, reportId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return ReportMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Report> findAllReports() throws SQLException {
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reports.add(ReportMapper.buildModel(resultSet));
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return reports;
    }

    @Override
    public List<Report> findByFamilyId(int familyId) throws SQLException {
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_FAMILY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    reports.add(ReportMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return reports;
    }

    @Override
    public void update(Report report) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, report.getFamilyId());
            preparedStatement.setString(2, report.getReportName());
            preparedStatement.setString(3, report.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(report.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int reportId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, reportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}