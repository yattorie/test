package senla.service;

import senla.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAllTags();

    Optional<Tag> getTagById(int id);

    void createTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(int id);
}