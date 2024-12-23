package senla.service;

import senla.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(int id);

    void createTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int id);
}