package senla.service;

import senla.model.Reminder;

import java.util.List;
import java.util.Optional;

public interface ReminderService {
    List<Reminder> getAllReminders();

    Optional<Reminder> getReminderById(int id);

    void createReminder(Reminder reminder);

    void updateReminder(Reminder reminder);

    void deleteReminder(int id);
}
