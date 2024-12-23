package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Reminder;
import senla.service.ReminderService;
import senla.service.impl.ReminderServiceImpl;
import senla.util.parser.ReminderParser;

import java.io.IOException;

@WebServlet("/reminders/*")
public class ReminderController extends HttpServlet {
    private final ReminderService reminderService = ReminderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("reminders", reminderService.getAllReminders());
            request.getRequestDispatcher("/WEB-INF/jsp/reminders.jsp").forward(request, response);
        } else {
            int id = ReminderParser.parseIdFromPath(action);
            reminderService.getReminderById(id).ifPresentOrElse(
                    reminder -> {
                        try {
                            request.setAttribute("reminder", reminder);
                            request.getRequestDispatcher("/WEB-INF/jsp/reminder-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Reminder not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Reminder reminder = ReminderParser.createReminderFromRequest(request);
        reminderService.createReminder(reminder);
        response.sendRedirect(request.getContextPath() + "/reminders");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Reminder ID is required");
            return;
        }

        int id = ReminderParser.parseIdFromPath(action);
        reminderService.deleteReminder(id);
        response.sendRedirect(request.getContextPath() + "/reminders");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Reminder ID is required");
            return;
        }

        int id = ReminderParser.parseIdFromPath(action);

        reminderService.getReminderById(id).ifPresentOrElse(
                existingReminder -> {
                    Reminder updatedReminder = ReminderParser.updateReminderFromRequest(request, existingReminder);
                    reminderService.updateReminder(updatedReminder);
                    try {
                        response.sendRedirect(request.getContextPath() + "/reminders");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Reminder not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}