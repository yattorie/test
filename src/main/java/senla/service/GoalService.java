package senla.service;

import senla.model.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalService {
    List<Goal> getAllGoals();

    Optional<Goal> getGoalById(int id);

    void createGoal(Goal goal);

    void updateGoal(Goal goal);

    void deleteGoal(int id);
}