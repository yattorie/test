package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.UserProfile;
import senla.service.UserProfileService;
import senla.service.impl.UserProfileServiceImpl;
import senla.util.parser.UserProfileParser;

import java.io.IOException;

@WebServlet("/user-profiles/*")
public class UserProfileController extends HttpServlet {
    private final UserProfileService userProfileService = UserProfileServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("userProfiles", userProfileService.getAllUserProfiles());
            request.getRequestDispatcher("/WEB-INF/jsp/user-profiles.jsp").forward(request, response);
        } else {
            int id = UserProfileParser.parseIdFromPath(action);
            userProfileService.getUserProfileById(id).ifPresentOrElse(
                    userProfile -> {
                        try {
                            request.setAttribute("userProfile", userProfile);
                            request.getRequestDispatcher("/WEB-INF/jsp/user-profile-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User Profile not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserProfile userProfile = UserProfileParser.createUserProfileFromRequest(request);
        userProfileService.createUserProfile(userProfile);
        response.sendRedirect(request.getContextPath() + "/user-profiles");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User Profile ID is required");
            return;
        }

        int id = UserProfileParser.parseIdFromPath(action);
        userProfileService.deleteUserProfile(id);
        response.sendRedirect(request.getContextPath() + "/user-profiles");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User Profile ID is required");
            return;
        }

        int id = UserProfileParser.parseIdFromPath(action);

        userProfileService.getUserProfileById(id).ifPresentOrElse(
                existingUserProfile -> {
                    UserProfile updatedUserProfile = UserProfileParser.updateUserProfileFromRequest(request, existingUserProfile);
                    userProfileService.updateUserProfile(updatedUserProfile);
                    try {
                        response.sendRedirect(request.getContextPath() + "/user-profiles");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User Profile not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}