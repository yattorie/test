package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.User;

import java.time.LocalDateTime;

public class UserParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static User createUserFromRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return new User.Builder()
                .username(username)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User updateUserFromRequest(HttpServletRequest request, User existingUser) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return new User.Builder()
                .userId(existingUser.getUserId())
                .username(username)
                .email(email)
                .password(password)
                .createdAt(existingUser.getCreatedAt())
                .build();
    }
}