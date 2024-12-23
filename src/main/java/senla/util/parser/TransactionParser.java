package senla.util.parser;

import jakarta.servlet.http.HttpServletRequest;
import senla.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionParser {

    public static int parseIdFromPath(String path) {
        return Integer.parseInt(path.substring(1));
    }

    public static Transaction createTransactionFromRequest(HttpServletRequest request) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");

        return new Transaction.Builder()
                .familyId(familyId)
                .userId(userId)
                .categoryId(categoryId)
                .amount(amount)
                .type(type)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Transaction updateTransactionFromRequest(HttpServletRequest request, Transaction existingTransaction) {
        int familyId = Integer.parseInt(request.getParameter("familyId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");

        return new Transaction.Builder()
                .transactionId(existingTransaction.getTransactionId())
                .familyId(familyId)
                .userId(userId)
                .categoryId(categoryId)
                .amount(amount)
                .type(type)
                .description(description)
                .createdAt(existingTransaction.getCreatedAt())
                .build();
    }
}