package senla.util.mapper;

import senla.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper {

    private TagMapper() {}

    public static Tag buildModel(ResultSet resultSet) throws SQLException {
        return new Tag.Builder()
                .tagId(resultSet.getInt("tag_id"))
                .tagName(resultSet.getString("name"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
