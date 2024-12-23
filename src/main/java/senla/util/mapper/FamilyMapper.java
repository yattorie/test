package senla.util.mapper;

import senla.model.Family;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMapper {

    private FamilyMapper() {
    }

    public static Family buildModel(ResultSet resultSet) throws SQLException {
        return new Family.Builder()
                .familyId(resultSet.getInt("family_id"))
                .familyName(resultSet.getString("family_name"))
                .familyDescription(resultSet.getString("family_description"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                .build();
    }
}
