package senla.service;

import senla.model.GoalStrategy;

import java.util.List;
import java.util.Optional;

public interface GoalStrategyService {
    List<GoalStrategy> getAllGoalStrategies();

    Optional<GoalStrategy> getGoalStrategyById(int id);

    void createGoalStrategy(GoalStrategy goalStrategy);

    void updateGoalStrategy(GoalStrategy goalStrategy);

    void deleteGoalStrategy(int id);
}