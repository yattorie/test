package senla.util.mapper;

import senla.model.CategoryBudget;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryBudgetMapper {

    private CategoryBudgetMapper() {
    }

    public static CategoryBudget buildModel(ResultSet resultSet) throws SQLException {
        return new CategoryBudget.Builder()
                .categoryBudgetId(resultSet.getInt("category_budget_id"))
                .familyId(resultSet.getInt("family_id"))
                .categoryId(resultSet.getInt("category_id"))
                .budgetAmount(resultSet.getBigDecimal("budget_amount"))
                .spentAmount(resultSet.getBigDecimal("spent_amount"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
                .build();
    }
}
