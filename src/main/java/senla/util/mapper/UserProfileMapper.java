package senla.util.mapper;

import senla.model.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileMapper {

    private UserProfileMapper(){}

    public static UserProfile buildModel(ResultSet resultSet) throws SQLException {
        return new UserProfile.Builder()
                .userProfileId(resultSet.getInt("user_profile_id"))
                .userId(resultSet.getInt("user_id"))
                .dateOfBirth(resultSet.getDate("date_of_birth") != null ? resultSet.getDate("date_of_birth").toLocalDate() : null)
                .familyStatus(resultSet.getString("family_status"))
                .gender(resultSet.getString("gender"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
