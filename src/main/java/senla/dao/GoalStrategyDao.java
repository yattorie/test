package senla.dao;

import senla.model.GoalStrategy;

import java.sql.SQLException;
import java.util.List;

public interface GoalStrategyDao {

    void save(GoalStrategy strategy) throws SQLException;

    List<GoalStrategy> findAllGoalStrategies() throws SQLException;

    GoalStrategy findStrategyById(int strategyId) throws SQLException;

    List<GoalStrategy> findStrategiesByFamilyId(int familyId) throws SQLException;

    void update(GoalStrategy strategy) throws SQLException;

    void delete(int strategyId) throws SQLException;
}
