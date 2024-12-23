package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.CategoryBudget;
import senla.service.CategoryBudgetService;
import senla.service.impl.CategoryBudgetServiceImpl;
import senla.util.parser.CategoryBudgetParser;

import java.io.IOException;

@WebServlet("/category-budgets/*")
public class CategoryBudgetController extends HttpServlet {
    private final CategoryBudgetService categoryBudgetService = CategoryBudgetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("categoryBudgets", categoryBudgetService.getAllCategoryBudgets());
            request.getRequestDispatcher("/WEB-INF/jsp/category-budgets.jsp").forward(request, response);
        } else {
            int id = CategoryBudgetParser.parseIdFromPath(action);
            categoryBudgetService.getCategoryBudgetById(id).ifPresentOrElse(
                    categoryBudget -> {
                        try {
                            request.setAttribute("categoryBudget", categoryBudget);
                            request.getRequestDispatcher("/WEB-INF/jsp/category-budget-details.jsp").forward(request, response); // Updated path
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "CategoryBudget not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CategoryBudget categoryBudget = CategoryBudgetParser.createCategoryBudgetFromRequest(request);
        categoryBudgetService.createCategoryBudget(categoryBudget);
        response.sendRedirect(request.getContextPath() + "/category-budgets");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryBudget ID is required");
            return;
        }

        int id = CategoryBudgetParser.parseIdFromPath(action);
        categoryBudgetService.deleteCategoryBudget(id);
        response.sendRedirect(request.getContextPath() + "/category-budgets");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryBudget ID is required");
            return;
        }

        int id = CategoryBudgetParser.parseIdFromPath(action);

        categoryBudgetService.getCategoryBudgetById(id).ifPresentOrElse(
                existingCategoryBudget -> {
                    CategoryBudget updatedCategoryBudget = CategoryBudgetParser.updateCategoryBudgetFromRequest(request, existingCategoryBudget);
                    categoryBudgetService.updateCategoryBudget(updatedCategoryBudget);
                    try {
                        response.sendRedirect(request.getContextPath() + "/category-budgets");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "CategoryBudget not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}