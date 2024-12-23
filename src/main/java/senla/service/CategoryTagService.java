package senla.service;

import senla.model.CategoryTag;

import java.util.List;
import java.util.Optional;

public interface CategoryTagService {
    List<CategoryTag> getAllCategoryTags();

    Optional<CategoryTag> getCategoryTagById(int categoryId);

    void createCategoryTag(CategoryTag categoryTag);

    void updateCategoryTag(CategoryTag categoryTag);

    void deleteCategoryTag(int categoryId, int tagId);
}