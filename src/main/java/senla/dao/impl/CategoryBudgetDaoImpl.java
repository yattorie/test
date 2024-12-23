package senla.dao.impl;

import senla.dao.CategoryBudgetDao;
import senla.exception.DaoException;
import senla.model.CategoryBudget;
import senla.util.ErrorMessages;
import senla.util.mapper.CategoryBudgetMapper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryBudgetDaoImpl implements CategoryBudgetDao {


    private static final String SQL_CREATE = """
            INSERT INTO category_budgets (family_id, category_id, budget_amount, spent_amount, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT * FROM category_budgets WHERE category_budget_id = ?;
            """;

    private static final String SQL_FIND_BY_FAMILY_ID = """
            SELECT * FROM category_budgets WHERE family_id = ?;
            """;

    private static final String SQL_UPDATE_SPENT_AMOUNT = """
            UPDATE category_budgets
            SET spent_amount = ?, updated_at = CURRENT_TIMESTAMP
            WHERE category_budget_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM category_budgets WHERE category_budget_id = ?;
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM category_budgets;
            """;

    private static final String SQL_UPDATE = """
            UPDATE category_budgets 
            SET family_id = ?, category_id = ?, budget_amount = ?, spent_amount = ?, created_at = ?, updated_at = ?
            WHERE category_budget_id = ?;
            """;

    private final Connection connection;

    public CategoryBudgetDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CategoryBudget categoryBudget) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, categoryBudget.getFamilyId());
            preparedStatement.setInt(2, categoryBudget.getCategoryId());
            preparedStatement.setBigDecimal(3, categoryBudget.getBudgetAmount());
            preparedStatement.setBigDecimal(4, categoryBudget.getSpentAmount());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(categoryBudget.getCreatedAt()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(categoryBudget.getUpdatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void update(CategoryBudget categoryBudget) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, categoryBudget.getFamilyId());
            preparedStatement.setInt(2, categoryBudget.getCategoryId());
            preparedStatement.setBigDecimal(3, categoryBudget.getBudgetAmount());
            preparedStatement.setBigDecimal(4, categoryBudget.getSpentAmount());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(categoryBudget.getCreatedAt()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(categoryBudget.getUpdatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<CategoryBudget> findAllCategoryBudgets() throws SQLException {
        List<CategoryBudget> categoryBudgets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categoryBudgets.add(CategoryBudgetMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categoryBudgets;
    }

    @Override
    public CategoryBudget findCategoryById(int categoryBudgetId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, categoryBudgetId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return CategoryBudgetMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<CategoryBudget> findByFamilyId(int familyId) {
        List<CategoryBudget> categoryBudgets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_FAMILY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    categoryBudgets.add(CategoryBudgetMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categoryBudgets;
    }

    @Override
    public void update(int categoryBudgetId, BigDecimal spentAmount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SPENT_AMOUNT)) {
            preparedStatement.setBigDecimal(1, spentAmount);
            preparedStatement.setInt(2, categoryBudgetId);
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int categoryBudgetId) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE)) {
            prepareStatement.setInt(1, categoryBudgetId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}



