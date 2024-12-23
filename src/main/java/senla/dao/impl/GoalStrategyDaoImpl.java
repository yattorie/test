package senla.dao.impl;

import senla.dao.GoalStrategyDao;
import senla.exception.DaoException;
import senla.model.GoalStrategy;
import senla.util.ErrorMessages;
import senla.util.mapper.GoalStrategyMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalStrategyDaoImpl implements GoalStrategyDao {

    public static final String SQL_SELECT_BY_ID = """
            SELECT * FROM goal_strategies WHERE strategy_id = ?;
            """;

    public static final String SQL_SELECT_BY_FAMILY_ID = """
            SELECT * FROM goal_strategies WHERE family_id = ?;
            """;

    public static final String SQL_CREATE = """
            INSERT INTO goal_strategies (family_id, user_id, goal_name, strategy_description, deadline)
            VALUES (?, ?, ?, ?, ?);
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM goal_strategies;
            """;

    public static final String SQL_UPDATE = """
            UPDATE goal_strategies
            SET goal_name = ?, strategy_description = ?, deadline = ?, updated_at = CURRENT_TIMESTAMP
            WHERE strategy_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM goal_strategies WHERE strategy_id = ?;
            """;

    private final Connection connection;

    public GoalStrategyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(GoalStrategy strategy) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, strategy.getGoalId());
            preparedStatement.setObject(2, strategy.getDescription(), Types.INTEGER);
            preparedStatement.setString(3, strategy.getStep());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(strategy.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<GoalStrategy> findAllGoalStrategies() throws SQLException {
        List<GoalStrategy> goalStrategies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                goalStrategies.add(GoalStrategyMapper.buildModel(resultSet));
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return goalStrategies;
    }

    @Override
    public GoalStrategy findStrategyById(int strategyId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, strategyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return GoalStrategyMapper.buildModel(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<GoalStrategy> findStrategiesByFamilyId(int familyId) throws SQLException {
        List<GoalStrategy> strategies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FAMILY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    strategies.add(GoalStrategyMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return strategies;
    }

    @Override
    public void update(GoalStrategy strategy) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, strategy.getGoalId());
            preparedStatement.setString(2, strategy.getDescription());
            preparedStatement.setString(3, strategy.getStep());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(strategy.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int strategyId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, strategyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}