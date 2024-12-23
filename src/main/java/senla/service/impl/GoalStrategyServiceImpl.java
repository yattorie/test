package senla.service.impl;

import senla.dao.GoalStrategyDao;
import senla.dao.impl.GoalStrategyDaoImpl;
import senla.model.GoalStrategy;
import senla.service.GoalStrategyService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoalStrategyServiceImpl implements GoalStrategyService {
    private static GoalStrategyService instance;
    private final GoalStrategyDao goalStrategyDao;

    private GoalStrategyServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.goalStrategyDao = new GoalStrategyDaoImpl(connection);
    }

    public static synchronized GoalStrategyService getInstance() {
        if (instance == null) {
            instance = new GoalStrategyServiceImpl();
        }
        return instance;
    }

    @Override
    public List<GoalStrategy> getAllGoalStrategies() {
        try {
            return goalStrategyDao.findAllGoalStrategies();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all goal strategies", e);
        }
    }

    @Override
    public Optional<GoalStrategy> getGoalStrategyById(int id) {
        try {
            return Optional.ofNullable(goalStrategyDao.findStrategyById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting goal strategy by ID", e);
        }
    }

    @Override
    public void createGoalStrategy(GoalStrategy goalStrategy) {
        try {
            goalStrategyDao.save(goalStrategy);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating goal strategy", e);
        }
    }

    @Override
    public void updateGoalStrategy(GoalStrategy goalStrategy) {
        try {
            goalStrategyDao.update(goalStrategy);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating goal strategy", e);
        }
    }

    @Override
    public void deleteGoalStrategy(int id) {
        try {
            goalStrategyDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting goal strategy", e);
        }
    }
}