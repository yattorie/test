package senla.service;

import senla.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(int id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}