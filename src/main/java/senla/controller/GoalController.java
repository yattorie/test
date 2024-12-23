package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Goal;
import senla.service.GoalService;
import senla.service.impl.GoalServiceImpl;
import senla.util.parser.GoalParser;

import java.io.IOException;

@WebServlet("/goals/*")
public class GoalController extends HttpServlet {
    private final GoalService goalService = GoalServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("goals", goalService.getAllGoals());
            request.getRequestDispatcher("/WEB-INF/jsp/goals.jsp").forward(request, response);
        } else {
            int id = GoalParser.parseIdFromPath(action);
            goalService.getGoalById(id).ifPresentOrElse(
                    goal -> {
                        try {
                            request.setAttribute("goal", goal);
                            request.getRequestDispatcher("/WEB-INF/jsp/goal-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Goal not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Goal goal = GoalParser.createGoalFromRequest(request);
        goalService.createGoal(goal);
        response.sendRedirect(request.getContextPath() + "/goals");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Goal ID is required");
            return;
        }

        int id = GoalParser.parseIdFromPath(action);
        goalService.deleteGoal(id);
        response.sendRedirect(request.getContextPath() + "/goals");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Goal ID is required");
            return;
        }

        int id = GoalParser.parseIdFromPath(action);

        goalService.getGoalById(id).ifPresentOrElse(
                existingGoal -> {
                    Goal updatedGoal = GoalParser.updateGoalFromRequest(request, existingGoal);
                    goalService.updateGoal(updatedGoal);
                    try {
                        response.sendRedirect(request.getContextPath() + "/goals");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Goal not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}