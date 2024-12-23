package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.GoalStrategy;
import senla.service.GoalStrategyService;
import senla.service.impl.GoalStrategyServiceImpl;
import senla.util.parser.GoalStrategyParser;

import java.io.IOException;

@WebServlet("/goal-strategies/*")
public class GoalStrategyController extends HttpServlet {
    private final GoalStrategyService goalStrategyService = GoalStrategyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("goalStrategies", goalStrategyService.getAllGoalStrategies());
            request.getRequestDispatcher("/WEB-INF/jsp/goal-strategies.jsp").forward(request, response);
        } else {
            int id = GoalStrategyParser.parseIdFromPath(action);
            goalStrategyService.getGoalStrategyById(id).ifPresentOrElse(
                    goalStrategy -> {
                        try {
                            request.setAttribute("goalStrategy", goalStrategy);
                            request.getRequestDispatcher("/WEB-INF/jsp/goal-strategy-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "GoalStrategy not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GoalStrategy goalStrategy = GoalStrategyParser.createGoalStrategyFromRequest(request);
        goalStrategyService.createGoalStrategy(goalStrategy);
        response.sendRedirect(request.getContextPath() + "/goal-strategies");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "GoalStrategy ID is required");
            return;
        }

        int id = GoalStrategyParser.parseIdFromPath(action);
        goalStrategyService.deleteGoalStrategy(id);
        response.sendRedirect(request.getContextPath() + "/goal-strategies");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "GoalStrategy ID is required");
            return;
        }

        int id = GoalStrategyParser.parseIdFromPath(action);

        goalStrategyService.getGoalStrategyById(id).ifPresentOrElse(
                existingGoalStrategy -> {
                    GoalStrategy updatedGoalStrategy = GoalStrategyParser.updateGoalStrategyFromRequest(request, existingGoalStrategy);
                    goalStrategyService.updateGoalStrategy(updatedGoalStrategy);
                    try {
                        response.sendRedirect(request.getContextPath() + "/goal-strategies");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "GoalStrategy not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}