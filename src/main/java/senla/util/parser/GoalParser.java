package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Goal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GoalParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Goal createGoalFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String goalName = request.getParameter("goalName");
        String description = request.getParameter("description");
        BigDecimal targetAmount = new BigDecimal(request.getParameter("targetAmount"));
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));

        return new Goal.Builder()
                .familyId(familyId)
                .goalName(goalName)
                .description(description)
                .targetAmount(targetAmount)
                .dueDate(dueDate)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Goal updateGoalFromRequest(HttpServletRequest request, Goal existingGoal) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        String goalName = request.getParameter("goalName");
        String description = request.getParameter("description");
        BigDecimal targetAmount = new BigDecimal(request.getParameter("targetAmount"));
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));

        return new Goal.Builder()
                .goalId(existingGoal.getGoalId())
                .familyId(familyId)
                .goalName(goalName)
                .description(description)
                .targetAmount(targetAmount)
                .dueDate(dueDate)
                .createdAt(existingGoal.getCreatedAt())
                .build();
    }
}