package senla.util.mapper;

import senla.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static Category buildModel(ResultSet resultSet) throws SQLException {
        return new Category.Builder()
                .categoryId(resultSet.getInt("category_id"))
                .categoryName(resultSet.getString("category_name"))
                .type(resultSet.getString("type"))
                .parentCategoryId(resultSet.getInt("parent_category_id"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                .createdBy(resultSet.getInt("created_by"))
                .build();
    }
}
