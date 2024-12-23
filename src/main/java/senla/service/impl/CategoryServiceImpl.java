package senla.service.impl;

import senla.dao.CategoryDao;
import senla.dao.impl.CategoryDaoImpl;
import senla.model.Category;
import senla.service.CategoryService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private static CategoryService instance;
    private final CategoryDao categoryDao;

    private CategoryServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.categoryDao = new CategoryDaoImpl(connection);
    }

    public static synchronized CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Category> getAllCategories() {
        try {
            return categoryDao.findAllCategories();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all categories", e);
        }
    }

    @Override
    public Optional<Category> getCategoryById(int categoryId) {
        try {
            return Optional.ofNullable(categoryDao.findCategoryById(categoryId));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting category by ID", e);
        }
    }

    @Override
    public void createCategory(Category category) {
        try {
            categoryDao.create(category);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating category", e);
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryDao.update(category);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        try {
            categoryDao.delete(categoryId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category", e);
        }
    }
}