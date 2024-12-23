package senla.util.mapper;

import senla.model.GoalStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalStrategyMapper {

    private GoalStrategyMapper() {}

    public static GoalStrategy buildModel(ResultSet resultSet) throws SQLException {
        return new GoalStrategy.Builder()
                .strategyId(resultSet.getInt("strategy_id"))
                .goalId(resultSet.getInt("goal_id"))
                .description(resultSet.getString("description"))
                .step(resultSet.getString("step"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
