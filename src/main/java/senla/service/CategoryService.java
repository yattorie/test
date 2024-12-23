package senla.service;

import senla.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryById(int categoryId);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int categoryId);
}