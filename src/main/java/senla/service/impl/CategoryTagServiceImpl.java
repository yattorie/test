package senla.service.impl;

import senla.dao.CategoryTagDao;
import senla.dao.impl.CategoryTagDaoImpl;
import senla.model.CategoryTag;
import senla.service.CategoryTagService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryTagServiceImpl implements CategoryTagService {
    private static CategoryTagService instance;
    private final CategoryTagDao categoryTagDao;

    private CategoryTagServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.categoryTagDao = new CategoryTagDaoImpl(connection);
    }

    public static synchronized CategoryTagService getInstance() {
        if (instance == null) {
            instance = new CategoryTagServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CategoryTag> getAllCategoryTags() {
        try {
            return categoryTagDao.findAllCategoryTags();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all category tags", e);
        }
    }

    @Override
    public Optional<CategoryTag> getCategoryTagById(int categoryId) {
        try {
            return Optional.ofNullable(categoryTagDao.findByCategoryId(categoryId).stream().findFirst().orElse(null));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting category tag by ID", e);
        }
    }

    @Override
    public void createCategoryTag(CategoryTag categoryTag) {
        try {
            categoryTagDao.create(categoryTag);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating category tag", e);
        }
    }

    @Override
    public void updateCategoryTag(CategoryTag categoryTag) {
        try {
            categoryTagDao.update(categoryTag);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category tag", e);
        }
    }

    @Override
    public void deleteCategoryTag(int categoryId, int tagId) {
        try {
            categoryTagDao.delete(categoryId, tagId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category tag", e);
        }
    }
}