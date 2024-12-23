package senla.dao;

import senla.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    void create(Category category) throws SQLException;

    Category findCategoryById(int id) throws SQLException;

    List<Category> findAllCategories() throws SQLException;

    void update(Category category) throws SQLException;

    void delete(int categoryId) throws SQLException;
}
