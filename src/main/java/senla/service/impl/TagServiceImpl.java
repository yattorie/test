package senla.service.impl;

import senla.dao.TagDao;
import senla.dao.impl.TagDaoImpl;
import senla.model.Tag;
import senla.service.TagService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {
    private static TagService instance;
    private final TagDao tagDao;

    private TagServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.tagDao = new TagDaoImpl(connection);
    }

    public static synchronized TagService getInstance() {
        if (instance == null) {
            instance = new TagServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Tag> getAllTags() {
        try {
            return tagDao.findAllTags();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all tags", e);
        }
    }

    @Override
    public Optional<Tag> getTagById(int id) {
        try {
            return Optional.ofNullable(tagDao.findTagById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting tag by ID", e);
        }
    }

    @Override
    public void createTag(Tag tag) {
        try {
            tagDao.save(tag);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating tag", e);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        try {
            tagDao.update(tag);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating tag", e);
        }
    }

    @Override
    public void deleteTag(int id) {
        try {
            tagDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting tag", e);
        }
    }
}