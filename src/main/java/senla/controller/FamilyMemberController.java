package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.FamilyMember;
import senla.service.FamilyMemberService;
import senla.service.impl.FamilyMemberServiceImpl;
import senla.util.parser.FamilyMemberParser;

import java.io.IOException;

@WebServlet("/family-members/*")
public class FamilyMemberController extends HttpServlet {
    private final FamilyMemberService familyMemberService = FamilyMemberServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("familyMembers", familyMemberService.getAllFamilyMembers());
            request.getRequestDispatcher("/WEB-INF/jsp/family-members.jsp").forward(request, response);
        } else {
            int id = FamilyMemberParser.parseIdFromPath(action);
            familyMemberService.getFamilyMemberById(id).ifPresentOrElse(
                    familyMember -> {
                        try {
                            request.setAttribute("familyMember", familyMember);
                            request.getRequestDispatcher("/WEB-INF/jsp/family-member-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family Member not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FamilyMember familyMember = FamilyMemberParser.createFamilyMemberFromRequest(request);
        familyMemberService.createFamilyMember(familyMember);
        response.sendRedirect(request.getContextPath() + "/family-members");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family Member ID is required");
            return;
        }

        int id = FamilyMemberParser.parseIdFromPath(action);
        familyMemberService.deleteFamilyMember(id);
        response.sendRedirect(request.getContextPath() + "/family-members");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Family Member ID is required");
            return;
        }

        int id = FamilyMemberParser.parseIdFromPath(action);

        familyMemberService.getFamilyMemberById(id).ifPresentOrElse(
                existingFamilyMember -> {
                    FamilyMember updatedFamilyMember = FamilyMemberParser.updateFamilyMemberFromRequest(request, existingFamilyMember);
                    familyMemberService.updateFamilyMember(updatedFamilyMember);
                    try {
                        response.sendRedirect(request.getContextPath() + "/family-members");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Family Member not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}