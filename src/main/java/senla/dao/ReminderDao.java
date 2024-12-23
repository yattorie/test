package senla.dao;

import senla.model.Reminder;

import java.sql.SQLException;
import java.util.List;

public interface ReminderDao {

    void save(Reminder reminder) throws SQLException;

    Reminder findReminderById(int id) throws SQLException;

    List<Reminder> findAllReminders() throws SQLException;

    void update(Reminder reminder) throws SQLException;

    void delete(int reminderId) throws SQLException;
}
