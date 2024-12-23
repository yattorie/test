package senla.service.impl;

import senla.dao.UserDao;
import senla.dao.impl.UserDaoImpl;
import senla.model.User;
import senla.service.UserService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserService instance;
    private final UserDao userDao;

    private UserServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.userDao = new UserDaoImpl(connection);
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.findAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users", e);
        }
    }

    @Override
    public Optional<User> getUserById(int id) {
        try {
            return Optional.ofNullable(userDao.findUserById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by ID", e);
        }
    }

    @Override
    public void createUser(User user) {
        try {
            userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }
}