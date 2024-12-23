package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.User;
import senla.service.UserService;
import senla.service.impl.UserServiceImpl;
import senla.util.parser.UserParser;

import java.io.IOException;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("users", userService.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);
        } else {
            int id = UserParser.parseIdFromPath(action);
            userService.getUserById(id).ifPresentOrElse(
                    user -> {
                        try {
                            request.setAttribute("user", user);
                            request.getRequestDispatcher("/WEB-INF/jsp/user-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = UserParser.createUserFromRequest(request);
        userService.createUser(user);
        response.sendRedirect(request.getContextPath() + "/users");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        int id = UserParser.parseIdFromPath(action);
        userService.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/users");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        int id = UserParser.parseIdFromPath(action);

        userService.getUserById(id).ifPresentOrElse(
                existingUser -> {
                    User updatedUser = UserParser.updateUserFromRequest(request, existingUser);
                    userService.updateUser(updatedUser);
                    try {
                        response.sendRedirect(request.getContextPath() + "/users");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}