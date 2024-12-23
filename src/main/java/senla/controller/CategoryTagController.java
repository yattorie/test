package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.CategoryTag;
import senla.service.CategoryTagService;
import senla.service.impl.CategoryTagServiceImpl;
import senla.util.parser.CategoryTagParser;

import java.io.IOException;

@WebServlet("/category-tags/*")
public class CategoryTagController extends HttpServlet {
    private final CategoryTagService categoryTagService = CategoryTagServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("categoryTags", categoryTagService.getAllCategoryTags());
            request.getRequestDispatcher("/WEB-INF/jsp/category-tags.jsp").forward(request, response);
        } else {
            int id = CategoryTagParser.parseIdFromPath(action);
            categoryTagService.getCategoryTagById(id).ifPresentOrElse(
                    categoryTag -> {
                        try {
                            request.setAttribute("categoryTag", categoryTag);
                            request.getRequestDispatcher("/WEB-INF/jsp/category-tag-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "CategoryTag not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CategoryTag categoryTag = CategoryTagParser.createCategoryTagFromRequest(request);
        categoryTagService.createCategoryTag(categoryTag);
        response.sendRedirect(request.getContextPath() + "/category-tags");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryTag ID is required");
            return;
        }

        int categoryId = CategoryTagParser.parseIdFromPath(action);
        int tagId = Integer.parseInt(request.getParameter("tagId"));
        categoryTagService.deleteCategoryTag(categoryId, tagId);
        response.sendRedirect(request.getContextPath() + "/category-tags");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryTag ID is required");
            return;
        }

        int categoryId = CategoryTagParser.parseIdFromPath(action);

        categoryTagService.getCategoryTagById(categoryId).ifPresentOrElse(
                existingCategoryTag -> {
                    CategoryTag updatedCategoryTag = CategoryTagParser.updateCategoryTagFromRequest(request, existingCategoryTag);
                    categoryTagService.updateCategoryTag(updatedCategoryTag);
                    try {
                        response.sendRedirect(request.getContextPath() + "/category-tags");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "CategoryTag not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}