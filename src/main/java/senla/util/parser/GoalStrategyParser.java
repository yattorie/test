package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.GoalStrategy;

import java.time.LocalDateTime;

public class GoalStrategyParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static GoalStrategy createGoalStrategyFromRequest(HttpServletRequest request) {
        int goalId = Integer.parseInt(request.getParameter("goalId"));
        String description = request.getParameter("description");
        String step = request.getParameter("step");

        return new GoalStrategy.Builder()
                .goalId(goalId)
                .description(description)
                .step(step)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static GoalStrategy updateGoalStrategyFromRequest(HttpServletRequest request, GoalStrategy existingGoalStrategy) {
        String description = request.getParameter("description");
        String step = request.getParameter("step");

        return new GoalStrategy.Builder()
                .strategyId(existingGoalStrategy.getStrategyId())
                .goalId(existingGoalStrategy.getGoalId())
                .description(description)
                .step(step)
                .createdAt(existingGoalStrategy.getCreatedAt())
                .build();
    }
}

