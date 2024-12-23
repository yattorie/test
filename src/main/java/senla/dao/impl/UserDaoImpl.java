package senla.dao.impl;

import senla.dao.UserDao;
import senla.exception.DaoException;
import senla.model.User;
import senla.util.ErrorMessages;
import senla.util.mapper.UserMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public static final String SQL_SELECT_BY_ID = """
            SELECT * FROM users WHERE user_id = ?;
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM users;
            """;

    public static final String SQL_CREATE = """
            INSERT INTO users (username, email, password, created_at) 
            VALUES (?, ?, ?, ?);
            """;

    public static final String SQL_UPDATE = """
            UPDATE users 
            SET username = ?, email = ?, password = ?, created_at = ? 
            WHERE user_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM users WHERE user_id = ?;
            """;

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }

    @Override
    public User findUserById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return UserMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(UserMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int userId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}