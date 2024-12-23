package senla.dao.impl;

import senla.dao.UserProfileDao;
import senla.exception.DaoException;
import senla.model.UserProfile;
import senla.util.ErrorMessages;
import senla.util.mapper.UserProfileMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDaoImpl implements UserProfileDao {

    public static final String SQL_CREATE = """
            INSERT INTO user_profiles (user_id, date_of_birth, family_status, gender)
            VALUES (?, ?, ?, ?);
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM user_profiles;
            """;

    public static final String SQL_FIND_BY_ID = """
            SELECT * FROM user_profiles WHERE user_profile_id = ?;
            """;

    public static final String SQL_FIND_BY_USER_ID = """
            SELECT * FROM user_profiles WHERE user_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM user_profiles WHERE user_profile_id = ?;
            """;

    public static final String SQL_UPDATE = """
            UPDATE transactions 
            SET user_id = ?, date_of_birth = ?, family_status = ?, gender = ?
            WHERE user_profile_id = ?;
            """;

    private final Connection connection;

    public UserProfileDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserProfile userProfile) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, userProfile.getUserId());
            preparedStatement.setDate(2, userProfile.getDateOfBirth() != null ? Date.valueOf(userProfile.getDateOfBirth()) : null);
            preparedStatement.setString(3, userProfile.getFamilyStatus());
            preparedStatement.setString(4, userProfile.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void update(UserProfile userProfile) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, userProfile.getUserId());
            preparedStatement.setDate(2, userProfile.getDateOfBirth() != null ? Date.valueOf(userProfile.getDateOfBirth()) : null);
            preparedStatement.setString(3, userProfile.getFamilyStatus());
            preparedStatement.setString(4, userProfile.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<UserProfile> findAllUserProfiles() throws SQLException {
        List<UserProfile> userProfiles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                userProfiles.add(UserProfileMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return userProfiles;
    }

    @Override
    public UserProfile findById(int userProfileId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, userProfileId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return UserProfileMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public UserProfile findByUserId(int userId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return UserProfileMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public void delete(int userProfileId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, userProfileId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}

