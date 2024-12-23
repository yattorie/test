package senla.dao.impl;

import senla.dao.CategoryTagDao;
import senla.exception.DaoException;
import senla.model.CategoryTag;
import senla.util.ErrorMessages;
import senla.util.mapper.CategoryTagMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryTagDaoImpl implements CategoryTagDao {

    private static final String SQL_CREATE = """
            INSERT INTO category_tags (category_id, tag_id, created_at)
            VALUES (?, ?, ?);
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM category_tags;
            """;

    private static final String SQL_UPDATE = """
            UPDATE category_tags 
            SET category_id = ?, tag_id = ?,created_at = ?
            WHERE family_id = ?;
            """;

    private static final String SQL_FIND_BY_CATEGORY_ID = """
            SELECT * FROM category_tags WHERE category_id = ?;
            """;

    private static final String SQL_FIND_BY_TAG_ID = """
            SELECT * FROM category_tags WHERE tag_id = ?;
            """;

    private static final String SQL_DELETE_BY_CATEGORY_ID_AND_TAG_ID = """
            DELETE FROM category_tags WHERE category_id = ? AND tag_id = ?;
            """;

    private final Connection connection;

    public CategoryTagDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CategoryTag categoryTag) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, categoryTag.getCategoryId());
            preparedStatement.setInt(2, categoryTag.getTagId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(categoryTag.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void update(CategoryTag categoryTag) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, categoryTag.getCategoryId());
            preparedStatement.setInt(2, categoryTag.getTagId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(categoryTag.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<CategoryTag> findAllCategoryTags() throws SQLException {
        List<CategoryTag> categoryTags = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categoryTags.add(CategoryTagMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categoryTags;
    }

    @Override
    public List<CategoryTag> findByCategoryId(int categoryId) throws SQLException {
        List<CategoryTag> categoryTags = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CATEGORY_ID)) {
            preparedStatement.setInt(1, categoryId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    categoryTags.add(CategoryTagMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categoryTags;
    }

    @Override
    public List<CategoryTag> findByTagId(int tagId) throws SQLException {
        List<CategoryTag> categoryTags = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TAG_ID)) {
            preparedStatement.setInt(1, tagId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    categoryTags.add(CategoryTagMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return categoryTags;
    }

    @Override
    public void delete(int categoryId, int tagId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_CATEGORY_ID_AND_TAG_ID)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, tagId);
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}
