package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Tag;

import java.time.LocalDateTime;

public class TagParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Tag createTagFromRequest(HttpServletRequest request) {
        String tagName = request.getParameter("name");

        return new Tag.Builder()
                .tagName(tagName)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Tag updateTagFromRequest(HttpServletRequest request, Tag existingTag) {
        String tagName = request.getParameter("name");

        return new Tag.Builder()
                .tagId(existingTag.getTagId())
                .tagName(tagName)
                .createdAt(existingTag.getCreatedAt())
                .build();
    }
}