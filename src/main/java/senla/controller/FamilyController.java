package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Family;
import senla.service.FamilyService;
import senla.service.impl.FamilyServiceImpl;
import senla.util.parser.FamilyParser;

import java.io.IOException;

@WebServlet("/families/*")
public class FamilyController extends HttpServlet {
    private final FamilyService familyService = FamilyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("families", familyService.getAllFamilies());
            request.getRequestDispatcher("/WEB-INF/jsp/families.jsp").forward(request, response);
        } else {
            int id = FamilyParser.parseIdFromPath(action);
            familyService.getFamilyById(id).ifPresentOrElse(
                    family -> {
                        try {
                            request.setAttribute("family", family);
                            request.getRequestDispatcher("/WEB-INF/jsp/family-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Family family = FamilyParser.createFamilyFromRequest(request);
        familyService.createFamily(family);
        response.sendRedirect(request.getContextPath() + "/families");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family ID is required");
            return;
        }

        int id = FamilyParser.parseIdFromPath(action);
        familyService.deleteFamily(id);
        response.sendRedirect(request.getContextPath() + "/families");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family ID is required");
            return;
        }

        int id = FamilyParser.parseIdFromPath(action);

        familyService.getFamilyById(id).ifPresentOrElse(
                existingFamily -> {
                    Family updatedFamily = FamilyParser.updateFamilyFromRequest(request, existingFamily);
                    familyService.updateFamily(updatedFamily);
                    try {
                        response.sendRedirect(request.getContextPath() + "/families");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}