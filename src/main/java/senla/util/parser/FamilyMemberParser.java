package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.FamilyMember;

import java.time.LocalDateTime;

public class FamilyMemberParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static FamilyMember createFamilyMemberFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String role = request.getParameter("role");

        return new FamilyMember.Builder()
                .familyId(familyId)
                .userId(userId)
                .role(role)
                .joinedAt(LocalDateTime.now())
                .build();
    }

    public static FamilyMember updateFamilyMemberFromRequest(HttpServletRequest request, FamilyMember existingFamilyMember) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String role = request.getParameter("role");

        return new FamilyMember.Builder()
                .memberId(existingFamilyMember.getMemberId())
                .familyId(familyId)
                .userId(userId)
                .role(role)
                .joinedAt(existingFamilyMember.getJoinedAt())
                .build();
    }
}