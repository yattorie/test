package senla.service.impl;

import senla.dao.UserProfileDao;
import senla.dao.impl.UserProfileDaoImpl;
import senla.model.UserProfile;
import senla.service.UserProfileService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserProfileServiceImpl implements UserProfileService {
    private static UserProfileService instance;
    private final UserProfileDao userProfileDao;

    private UserProfileServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.userProfileDao = new UserProfileDaoImpl(connection);
    }

    public static synchronized UserProfileService getInstance() {
        if (instance == null) {
            instance = new UserProfileServiceImpl();
        }
        return instance;
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        try {
            return userProfileDao.findAllUserProfiles();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all user profiles", e);
        }
    }

    @Override
    public Optional<UserProfile> getUserProfileById(int id) {
        try {
            return Optional.ofNullable(userProfileDao.findById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user profile by ID", e);
        }
    }

    @Override
    public void createUserProfile(UserProfile userProfile) {
        try {
            userProfileDao.create(userProfile);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user profile", e);
        }
    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        try {
            userProfileDao.update(userProfile);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user profile", e);
        }
    }

    @Override
    public void deleteUserProfile(int id) {
        try {
            userProfileDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user profile", e);
        }
    }
}