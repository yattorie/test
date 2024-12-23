package senla.util.mapper;

import senla.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private UserMapper(){}

    public static User buildModel(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .userId(resultSet.getInt("user_id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
