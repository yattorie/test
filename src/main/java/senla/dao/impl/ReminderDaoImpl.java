package senla.dao.impl;


import senla.dao.ReminderDao;
import senla.exception.DaoException;
import senla.model.Reminder;
import senla.util.ErrorMessages;
import senla.util.mapper.ReminderMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReminderDaoImpl implements ReminderDao {

    public static final String SQL_SELECT_BY_ID = """
            SELECT * FROM reminders WHERE reminder_id = ?;
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM reminders;
            """;

    public static final String SQL_CREATE = """
            INSERT INTO reminders (family_id, description, reminder_date, status, created_at) 
            VALUES (?, ?, ?, ?, ?);
            """;

    public static final String SQL_UPDATE = """
            UPDATE reminders 
            SET family_id = ?, description = ?, reminder_date = ?, status = ?, created_at = ?
            WHERE reminder_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM reminders WHERE reminder_id = ?;
            """;

    private final Connection connection;

    public ReminderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Reminder reminder) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, reminder.getFamilyId());
            preparedStatement.setString(2, reminder.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(reminder.getReminderDate()));
            preparedStatement.setString(4, reminder.getStatus());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reminder.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }

    @Override
    public Reminder findReminderById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return ReminderMapper.buildModel(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Reminder> findAllReminders() throws SQLException {
        List<Reminder> reminders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reminders.add(ReminderMapper.buildModel(resultSet));
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return reminders;
    }

    @Override
    public void update(Reminder reminder) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, reminder.getFamilyId());
            preparedStatement.setString(2, reminder.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(reminder.getReminderDate()));
            preparedStatement.setString(4, reminder.getStatus());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(reminder.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int reminderId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, reminderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}
