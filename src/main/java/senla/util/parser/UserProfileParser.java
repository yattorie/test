package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.UserProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserProfileParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static UserProfile createUserProfileFromRequest(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String familyStatus = request.getParameter("familyStatus");
        String gender = request.getParameter("gender");

        return new UserProfile.Builder()
                .userId(userId)
                .dateOfBirth(dateOfBirth)
                .familyStatus(familyStatus)
                .gender(gender)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserProfile updateUserProfileFromRequest(HttpServletRequest request, UserProfile existingUserProfile) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String familyStatus = request.getParameter("familyStatus");
        String gender = request.getParameter("gender");

        return new UserProfile.Builder()
                .userProfileId(existingUserProfile.getUserProfileId())
                .userId(userId)
                .dateOfBirth(dateOfBirth)
                .familyStatus(familyStatus)
                .gender(gender)
                .createdAt(existingUserProfile.getCreatedAt())
                .build();
    }
}