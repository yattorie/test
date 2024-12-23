package senla.dao;

import senla.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDao {

    void save(Tag tag) throws SQLException;

    Tag findTagById(int id) throws SQLException;

    List<Tag> findAllTags() throws SQLException;

    void update(Tag tag) throws SQLException;

    void delete(int tagId) throws SQLException;
}
