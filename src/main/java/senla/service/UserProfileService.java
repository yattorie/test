package senla.service;

import senla.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    List<UserProfile> getAllUserProfiles();

    Optional<UserProfile> getUserProfileById(int id);

    void createUserProfile(UserProfile userProfile);

    void updateUserProfile(UserProfile userProfile);

    void deleteUserProfile(int id);
}