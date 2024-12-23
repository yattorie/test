package senla.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import senla.model.Transaction;
import senla.service.TransactionService;
import senla.service.impl.TransactionServiceImpl;
import senla.util.parser.TransactionParser;

import java.io.IOException;

@WebServlet("/transactions/*")
public class TransactionController extends HttpServlet {
    private final TransactionService transactionService = TransactionServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || "/".equals(action)) {
            request.setAttribute("transactions", transactionService.getAllTransactions());
            request.getRequestDispatcher("/WEB-INF/jsp/transactions.jsp").forward(request, response);
        } else {
            int id = TransactionParser.parseIdFromPath(action);
            transactionService.getTransactionById(id).ifPresentOrElse(
                    transaction -> {
                        try {
                            request.setAttribute("transaction", transaction);
                            request.getRequestDispatcher("/WEB-INF/jsp/transaction-details.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Transaction not found");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Transaction transaction = TransactionParser.createTransactionFromRequest(request);
        transactionService.createTransaction(transaction);
        response.sendRedirect(request.getContextPath() + "/transactions");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Transaction ID is required");
            return;
        }

        int id = TransactionParser.parseIdFromPath(action);
        transactionService.deleteTransaction(id);
        response.sendRedirect(request.getContextPath() + "/transactions");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Transaction ID is required");
            return;
        }

        int id = TransactionParser.parseIdFromPath(action);

        transactionService.getTransactionById(id).ifPresentOrElse(
                existingTransaction -> {
                    Transaction updatedTransaction = TransactionParser.updateTransactionFromRequest(request, existingTransaction);
                    transactionService.updateTransaction(updatedTransaction);
                    try {
                        response.sendRedirect(request.getContextPath() + "/transactions");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Transaction not found");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}