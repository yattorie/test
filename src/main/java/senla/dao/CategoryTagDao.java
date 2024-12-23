package senla.dao;

import senla.model.CategoryTag;

import java.sql.SQLException;
import java.util.List;

public interface CategoryTagDao {

    void create(CategoryTag categoryTag) throws SQLException;

    void update(CategoryTag categoryTag) throws SQLException;

    List<CategoryTag> findAllCategoryTags() throws SQLException;

    List<CategoryTag> findByCategoryId(int categoryId) throws SQLException;

    List<CategoryTag> findByTagId(int tagId) throws SQLException;

    void delete(int categoryId, int tagId) throws SQLException;
}
