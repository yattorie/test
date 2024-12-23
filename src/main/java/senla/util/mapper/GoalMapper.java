package senla.util.mapper;

import senla.model.Goal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalMapper {

    private GoalMapper() {}

    public static Goal buildModel(ResultSet resultSet) throws SQLException {
        return new Goal.Builder()
                .goalId(resultSet.getInt("goal_id"))
                .familyId(resultSet.getInt("family_id"))
                .goalName(resultSet.getString("goal_name"))
                .description(resultSet.getString("description"))
                .targetAmount(resultSet.getBigDecimal("target_amount"))
                .dueDate(resultSet.getDate("due_date").toLocalDate())
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
