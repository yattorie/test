package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.FamilyBudget;
import senla.service.FamilyBudgetService;
import senla.service.impl.FamilyBudgetServiceImpl;
import senla.util.parser.FamilyBudgetParser;

import java.io.IOException;

@WebServlet("/family-budgets/*")
public class FamilyBudgetController extends HttpServlet {
    private final FamilyBudgetService familyBudgetService = FamilyBudgetServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("familyBudgets", familyBudgetService.getAllFamilyBudgets());
            request.getRequestDispatcher("/WEB-INF/jsp/family-budgets.jsp").forward(request, response);
        } else {
            int id = FamilyBudgetParser.parseIdFromPath(action);
            familyBudgetService.getFamilyBudgetById(id).ifPresentOrElse(
                    familyBudget -> {
                        try {
                            request.setAttribute("familyBudget", familyBudget);
                            request.getRequestDispatcher("/WEB-INF/jsp/family-budget-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family Budget not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FamilyBudget familyBudget = FamilyBudgetParser.createFamilyBudgetFromRequest(request);
        familyBudgetService.createFamilyBudget(familyBudget);
        response.sendRedirect(request.getContextPath() + "/family-budgets");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family ID is required");
            return;
        }

        int id = FamilyBudgetParser.parseIdFromPath(action);
        familyBudgetService.deleteFamilyBudget(id);
        response.sendRedirect(request.getContextPath() + "/family-budgets");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family ID is required");
            return;
        }

        int id = FamilyBudgetParser.parseIdFromPath(action);

        familyBudgetService.getFamilyBudgetById(id).ifPresentOrElse(
                existingFamilyBudget -> {
                    FamilyBudget updatedFamilyBudget = FamilyBudgetParser.updateFamilyBudgetFromRequest(request, existingFamilyBudget);
                    familyBudgetService.updateFamilyBudget(updatedFamilyBudget);
                    try {
                        response.sendRedirect(request.getContextPath() + "/family-budgets");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family Budget not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}