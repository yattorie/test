package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.CategoryBudget;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CategoryBudgetParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static CategoryBudget createCategoryBudgetFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        BigDecimal budgetAmount = new BigDecimal(request.getParameter("budgetAmount"));
        BigDecimal spentAmount = new BigDecimal(request.getParameter("spentAmount"));

        return new CategoryBudget.Builder()
                .familyId(familyId)
                .categoryId(categoryId)
                .budgetAmount(budgetAmount)
                .spentAmount(spentAmount)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CategoryBudget updateCategoryBudgetFromRequest(HttpServletRequest request, CategoryBudget existingCategoryBudget) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        BigDecimal budgetAmount = new BigDecimal(request.getParameter("budgetAmount"));
        BigDecimal spentAmount = new BigDecimal(request.getParameter("spentAmount"));

        return new CategoryBudget.Builder()
                .categoryBudgetId(existingCategoryBudget.getCategoryBudgetId())
                .familyId(familyId)
                .categoryId(categoryId)
                .budgetAmount(budgetAmount)
                .spentAmount(spentAmount)
                .createdAt(existingCategoryBudget.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}