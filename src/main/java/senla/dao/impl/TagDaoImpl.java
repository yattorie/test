package senla.dao.impl;

import senla.dao.TagDao;
import senla.exception.DaoException;
import senla.model.Tag;
import senla.util.ErrorMessages;
import senla.util.mapper.TagMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM tags WHERE tag_id = ?;
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM tags;
            """;

    private static final String SQL_CREATE = """
            INSERT INTO tags (name, created_at) VALUES (?,?);
            """;

    private static final String SQL_UPDATE = """
            UPDATE tags 
            SET name = ?, created_at = ?
            WHERE tag_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM tags WHERE tag_id = ?;
            """;


    private final Connection connection;

    public TagDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Tag tag) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, tag.getTagName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(tag.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }

    @Override
    public Tag findTagById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return TagMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Tag> findAllTags() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                tags.add(TagMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return tags;
    }

    @Override
    public void update(Tag tag) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, tag.getTagName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(tag.getCreatedAt()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int tagId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, tagId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}