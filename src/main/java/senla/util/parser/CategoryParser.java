package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Category;

import java.time.LocalDateTime;

public class CategoryParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Category createCategoryFromRequest(HttpServletRequest request) {
        String categoryName = request.getParameter("categoryName");
        String type = request.getParameter("type");
        Integer parentCategoryId = request.getParameter("parentCategoryId") == null ? null : Integer.parseInt(request.getParameter("parentCategoryId"));
        int createdBy = Integer.parseInt(request.getParameter("createdBy"));
        LocalDateTime now = LocalDateTime.now();

        return new Category.Builder()
                .categoryName(categoryName)
                .type(type)
                .parentCategoryId(parentCategoryId)
                .createdAt(now)
                .updatedAt(now)
                .createdBy(createdBy)
                .build();
    }

    public static Category updateCategoryFromRequest(HttpServletRequest request, Category existingCategory) {
        String categoryName = request.getParameter("categoryName");
        String type = request.getParameter("type");
        Integer parentCategoryId = request.getParameter("parentCategoryId") == null ? null : Integer.parseInt(request.getParameter("parentCategoryId"));

        return new Category.Builder()
                .categoryId(existingCategory.getCategoryId())
                .categoryName(categoryName)
                .type(type)
                .parentCategoryId(parentCategoryId)
                .createdAt(existingCategory.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .createdBy(existingCategory.getCreatedBy())
                .build();
    }
}