package senla.util.mapper;

import senla.model.Reminder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReminderMapper {

    private ReminderMapper() {}

    public static Reminder buildModel(ResultSet resultSet) throws SQLException {
        return new Reminder.Builder()
                .reminderId(resultSet.getInt("reminder_id"))
                .familyId(resultSet.getInt("family_id"))
                .description(resultSet.getString("description"))
                .reminderDate(resultSet.getTimestamp("reminder_date").toLocalDateTime())
                .status(resultSet.getString("status"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
