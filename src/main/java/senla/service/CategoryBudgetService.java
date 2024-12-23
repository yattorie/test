package senla.service;

import senla.model.CategoryBudget;

import java.util.List;
import java.util.Optional;

public interface CategoryBudgetService {
    List<CategoryBudget> getAllCategoryBudgets();

    Optional<CategoryBudget> getCategoryBudgetById(int id);

    void createCategoryBudget(CategoryBudget categoryBudget);

    void updateCategoryBudget(CategoryBudget categoryBudget);

    void deleteCategoryBudget(int id);
}