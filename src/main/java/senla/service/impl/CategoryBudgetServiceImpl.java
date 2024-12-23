package senla.service.impl;

import senla.dao.CategoryBudgetDao;
import senla.dao.impl.CategoryBudgetDaoImpl;
import senla.model.CategoryBudget;
import senla.service.CategoryBudgetService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryBudgetServiceImpl implements CategoryBudgetService {
    private static CategoryBudgetService instance;
    private final CategoryBudgetDao categoryBudgetDao;

    private CategoryBudgetServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.categoryBudgetDao = new CategoryBudgetDaoImpl(connection);
    }

    public static synchronized CategoryBudgetService getInstance() {
        if (instance == null) {
            instance = new CategoryBudgetServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CategoryBudget> getAllCategoryBudgets() {
        try {
            return categoryBudgetDao.findAllCategoryBudgets();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all category budgets", e);
        }
    }

    @Override
    public Optional<CategoryBudget> getCategoryBudgetById(int id) {
        try {
            return Optional.ofNullable(categoryBudgetDao.findCategoryById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting category budget by ID", e);
        }
    }

    @Override
    public void createCategoryBudget(CategoryBudget categoryBudget) {
        try {
            categoryBudgetDao.create(categoryBudget);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating category budget", e);
        }
    }

    @Override
    public void updateCategoryBudget(CategoryBudget categoryBudget) {
        try {
            categoryBudgetDao.update(categoryBudget.getCategoryBudgetId(), categoryBudget.getSpentAmount());
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category budget", e);
        }
    }

    @Override
    public void deleteCategoryBudget(int id) {
        try {
            categoryBudgetDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category budget", e);
        }
    }
}