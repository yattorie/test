package senla.dao.impl;

import senla.dao.CategoryDao;
import senla.exception.DaoException;
import senla.model.Category;
import senla.util.ErrorMessages;
import senla.util.mapper.CategoryMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM categories WHERE category_id = ?;
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM categories;
            """;

    private static final String SQL_CREATE = """
            INSERT INTO categories (category_name, type, parent_category_id, created_at, updated_at, created_by) 
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private static final String SQL_UPDATE = """
            UPDATE categories 
            SET category_name = ?, type = ?, parent_category_id = ?, updated_at = CURRENT_TIMESTAMP 
            WHERE category_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM categories WHERE category_id = ?;
            """;

    private final Connection connection;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Category category) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getType());
            preparedStatement.setObject(3, category.getParentCategoryId(), Types.INTEGER);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(category.getCreatedAt()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(category.getUpdatedAt()));
            preparedStatement.setInt(6, category.getCreatedBy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public Category findCategoryById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return CategoryMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Category> findAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(CategoryMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categories;
    }

    @Override
    public void update(Category category) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getType());
            preparedStatement.setObject(3, category.getParentCategoryId(), Types.INTEGER);
            preparedStatement.setInt(4, category.getCategoryId());
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int categoryId) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE)) {
            prepareStatement.setInt(1, categoryId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}
