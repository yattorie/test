package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Reminder;

import java.time.LocalDateTime;

public class ReminderParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Reminder createReminderFromRequest(HttpServletRequest request) {
        String description = request.getParameter("description");
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String status = request.getParameter("status");
        LocalDateTime reminderDate = LocalDateTime.parse(request.getParameter("reminderDate"));

        return new Reminder.Builder()
                .familyId(familyId)
                .description(description)
                .status(status)
                .reminderDate(reminderDate)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Reminder updateReminderFromRequest(HttpServletRequest request, Reminder existingReminder) {
        String description = request.getParameter("description");
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String status = request.getParameter("status");
        LocalDateTime reminderDate = LocalDateTime.parse(request.getParameter("reminderDate"));

        return new Reminder.Builder()
                .reminderId(existingReminder.getReminderId())
                .familyId(familyId)
                .description(description)
                .status(status)
                .reminderDate(reminderDate)
                .createdAt(existingReminder.getCreatedAt())
                .build();
    }
}