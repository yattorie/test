package senla.util.mapper;

import senla.model.CategoryTag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTagMapper{

    private CategoryTagMapper() {
    }

    public static CategoryTag buildModel(ResultSet resultSet) throws SQLException {
        return new CategoryTag.Builder()
                .categoryTagId(resultSet.getInt("category_tag_id"))
                .categoryId(resultSet.getInt("category_id"))
                .tagId(resultSet.getInt("tag_id"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}



