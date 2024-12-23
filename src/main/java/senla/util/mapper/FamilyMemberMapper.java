package senla.util.mapper;

import senla.model.FamilyMember;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMemberMapper {

    private FamilyMemberMapper() {
    }

    public static FamilyMember buildModel(ResultSet resultSet) throws SQLException {
        return new FamilyMember.Builder()
                .memberId(resultSet.getInt("family_member_id"))
                .familyId(resultSet.getInt("family_id"))
                .userId(resultSet.getInt("user_id"))
                .role(resultSet.getString("role").toUpperCase())
                .joinedAt(resultSet.getTimestamp("joined_at").toLocalDateTime())
                .build();
    }
}


