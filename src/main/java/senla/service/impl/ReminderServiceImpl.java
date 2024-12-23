package senla.service.impl;

import senla.dao.ReminderDao;
import senla.dao.impl.ReminderDaoImpl;
import senla.model.Reminder;
import senla.service.ReminderService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReminderServiceImpl implements ReminderService {
    private static ReminderService instance;
    private final ReminderDao reminderDao;

    private ReminderServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.reminderDao = new ReminderDaoImpl(connection);
    }

    public static synchronized ReminderService getInstance() {
        if (instance == null) {
            instance = new ReminderServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Reminder> getAllReminders() {
        try {
            return reminderDao.findAllReminders();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all reminders", e);
        }
    }

    @Override
    public Optional<Reminder> getReminderById(int id) {
        try {
            return Optional.ofNullable(reminderDao.findReminderById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting reminder by ID", e);
        }
    }

    @Override
    public void createReminder(Reminder reminder) {
        try {
            reminderDao.save(reminder);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating reminder", e);
        }
    }

    @Override
    public void updateReminder(Reminder reminder) {
        try {
            reminderDao.update(reminder);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating reminder", e);
        }
    }

    @Override
    public void deleteReminder(int id) {
        try {
            reminderDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting reminder", e);
        }
    }
}
