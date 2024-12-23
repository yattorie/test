package senla.service.impl;

import senla.dao.GoalDao;
import senla.dao.impl.GoalDaoImpl;
import senla.model.Goal;
import senla.service.GoalService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoalServiceImpl implements GoalService {
    private static GoalService instance;
    private final GoalDao goalDao;

    private GoalServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.goalDao = new GoalDaoImpl(connection);
    }

    public static synchronized GoalService getInstance() {
        if (instance == null) {
            instance = new GoalServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Goal> getAllGoals() {
        try {
            return goalDao.findAllGoals();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all goals", e);
        }
    }

    @Override
    public Optional<Goal> getGoalById(int id) {
        try {
            return Optional.ofNullable(goalDao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting goal by ID", e);
        }
    }

    @Override
    public void createGoal(Goal goal) {
        try {
            goalDao.create(goal);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating goal", e);
        }
    }

    @Override
    public void updateGoal(Goal goal) {
        try {
            goalDao.update(goal);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating goal", e);
        }
    }

    @Override
    public void deleteGoal(int id) {
        try {
            goalDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting goal", e);
        }
    }
}