package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.CategoryTag;

import java.time.LocalDateTime;

public class CategoryTagParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static CategoryTag createCategoryTagFromRequest(HttpServletRequest request) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int tagId = Integer.parseInt(request.getParameter("tagId"));

        return new CategoryTag.Builder()
                .categoryId(categoryId)
                .tagId(tagId)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CategoryTag updateCategoryTagFromRequest(HttpServletRequest request, CategoryTag existingCategoryTag) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int tagId = Integer.parseInt(request.getParameter("tagId"));

        return new CategoryTag.Builder()
                .categoryTagId(existingCategoryTag.getCategoryTagId())
                .categoryId(categoryId)
                .tagId(tagId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}