package senla.dao.impl;

import senla.dao.GoalDao;
import senla.exception.DaoException;
import senla.model.Goal;
import senla.util.ErrorMessages;
import senla.util.mapper.GoalMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalDaoImpl implements GoalDao {

    public static final String SQL_CREATE = """
            INSERT INTO goals (family_id, goal_name, description, target_amount, due_date)
            VALUES (?, ?, ?, ?, ?);
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM goals;
            """;

    public static final String SQL_FIND_BY_ID = """
            SELECT * FROM goals WHERE goal_id = ?;
            """;

    public static final String SQL_FIND_BY_FAMILY_ID = """
            SELECT * FROM goals WHERE family_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM goals WHERE goal_id = ?;
            """;

    private static final String SQL_UPDATE = """
            UPDATE goals 
            SET family_id = ?, goal_name = ?, description = ?, target_amount = ?, due_date = ?
            WHERE goal_id = ?;
            """;

    private final Connection connection;

    public GoalDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Goal goal) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, goal.getFamilyId());
            preparedStatement.setString(2, goal.getGoalName());
            preparedStatement.setString(3, goal.getDescription());
            preparedStatement.setBigDecimal(4, goal.getTargetAmount());
            preparedStatement.setDate(5, Date.valueOf(goal.getDueDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void update(Goal goal) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, goal.getFamilyId());
            preparedStatement.setString(2, goal.getGoalName());
            preparedStatement.setString(3, goal.getDescription());
            preparedStatement.setBigDecimal(4, goal.getTargetAmount());
            preparedStatement.setDate(5, Date.valueOf(goal.getDueDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<Goal> findAllGoals() throws SQLException {
        List<Goal> goals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                goals.add(GoalMapper.buildModel(resultSet));
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return goals;
    }


    @Override
    public Goal findById(int goalId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, goalId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return GoalMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Goal> findByFamilyId(int familyId) throws SQLException {
        List<Goal> goals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_FAMILY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    goals.add(GoalMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return goals;
    }

    @Override
    public void delete(int goalId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, goalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}


