package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.FamilyBudget;

import java.math.BigDecimal;

public class FamilyBudgetParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static FamilyBudget createFamilyBudgetFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        BigDecimal balance = new BigDecimal(request.getParameter("balance"));

        return new FamilyBudget.Builder()
                .familyId(familyId)
                .balance(balance)
                .build();
    }

    public static FamilyBudget updateFamilyBudgetFromRequest(HttpServletRequest request, FamilyBudget existingFamilyBudget) {
        BigDecimal balance = new BigDecimal(request.getParameter("balance"));

        return new FamilyBudget.Builder()
                .familyId(existingFamilyBudget.getFamilyId())
                .balance(balance)
                .build();
    }
}

