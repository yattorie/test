package senla.service.impl;

import senla.dao.TransactionDao;
import senla.dao.impl.TransactionDaoImpl;
import senla.model.Transaction;
import senla.service.TransactionService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {
    private static TransactionService instance;
    private final TransactionDao transactionDao;

    private TransactionServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.transactionDao = new TransactionDaoImpl(connection);
    }

    public static synchronized TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        try {
            return transactionDao.findAllTransactions();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all transactions", e);
        }
    }

    @Override
    public Optional<Transaction> getTransactionById(int id) {
        try {
            return Optional.ofNullable(transactionDao.findTransactionById(id));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting transaction by ID", e);
        }
    }

    @Override
    public void createTransaction(Transaction transaction) {
        try {
            transactionDao.save(transaction);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating transaction", e);
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        try {
            transactionDao.update(transaction);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating transaction", e);
        }
    }

    @Override
    public void deleteTransaction(int id) {
        try {
            transactionDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transaction", e);
        }
    }
}