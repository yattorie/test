package senla.dao;

import senla.model.CategoryBudget;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CategoryBudgetDao {

    void create(CategoryBudget categoryBudget) throws SQLException;

    void update(CategoryBudget categoryBudget);

    List<CategoryBudget> findAllCategoryBudgets() throws SQLException;

    CategoryBudget findCategoryById(int categoryBudgetId) throws SQLException;

    List<CategoryBudget> findByFamilyId(int familyId) throws SQLException;

    void update(int categoryBudgetId, BigDecimal spentAmount) throws SQLException;

    void delete(int categoryBudgetId) throws SQLException;
}
