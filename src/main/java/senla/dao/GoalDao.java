package senla.dao;

import senla.model.Goal;

import java.sql.SQLException;
import java.util.List;

public interface GoalDao {

    void create(Goal goal) throws SQLException;

    void update(Goal goal) throws SQLException;

    List<Goal> findAllGoals() throws SQLException;

    Goal findById(int goalId) throws SQLException;

    List<Goal> findByFamilyId(int familyId) throws SQLException;

    void delete(int goalId) throws SQLException;
}
