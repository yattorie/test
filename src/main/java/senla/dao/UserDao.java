package senla.dao;

import senla.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void save(User user) throws SQLException;

    User findUserById(int id) throws SQLException;

    List<User> findAllUsers() throws SQLException;

    void update(User user) throws SQLException;

    void delete(int userId) throws SQLException;
}
