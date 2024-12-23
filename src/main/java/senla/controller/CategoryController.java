package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Category;
import senla.service.CategoryService;
import senla.service.impl.CategoryServiceImpl;
import senla.util.parser.CategoryParser;

import java.io.IOException;

@WebServlet("/categories/*")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("categories", categoryService.getAllCategories());
            request.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(request, response);
        } else {
            int id = CategoryParser.parseIdFromPath(action);
            categoryService.getCategoryById(id).ifPresentOrElse(
                    category -> {
                        try {
                            request.setAttribute("category", category);
                            request.getRequestDispatcher("/WEB-INF/jsp/category-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category category = CategoryParser.createCategoryFromRequest(request);
        categoryService.createCategory(category);
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required");
            return;
        }

        int id = CategoryParser.parseIdFromPath(action);
        categoryService.deleteCategory(id);
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required");
            return;
        }

        int id = CategoryParser.parseIdFromPath(action);

        categoryService.getCategoryById(id).ifPresentOrElse(
                existingCategory -> {
                    Category updatedCategory = CategoryParser.updateCategoryFromRequest(request, existingCategory);
                    categoryService.updateCategory(updatedCategory);
                    try {
                        response.sendRedirect(request.getContextPath() + "/categories");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}