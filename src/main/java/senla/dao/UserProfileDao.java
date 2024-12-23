package senla.dao;

import senla.model.UserProfile;

import java.sql.SQLException;
import java.util.List;

public interface UserProfileDao {

    void create(UserProfile userProfile) throws SQLException;

    void update(UserProfile userProfile) throws SQLException;

    List<UserProfile> findAllUserProfiles() throws SQLException;

    UserProfile findById(int userProfileId) throws SQLException;

    UserProfile findByUserId(int userId) throws SQLException;

    void delete(int userId) throws SQLException;
}
