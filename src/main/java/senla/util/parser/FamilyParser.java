package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Family;

import java.time.LocalDateTime;

public class FamilyParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Family createFamilyFromRequest(HttpServletRequest request) {
        String familyName = request.getParameter("familyName");
        String familyDescription = request.getParameter("familyDescription");

        return new Family.Builder()
                .familyName(familyName)
                .familyDescription(familyDescription)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Family updateFamilyFromRequest(HttpServletRequest request, Family existingFamily) {
        String familyDescription = request.getParameter("familyDescription");

        return new Family.Builder()
                .familyId(existingFamily.getFamilyId())
                .familyName(existingFamily.getFamilyName())
                .familyDescription(familyDescription)
                .createdAt(existingFamily.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}