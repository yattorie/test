package senla.dao;

import senla.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    void save(Transaction transaction) throws SQLException;

    Transaction findTransactionById(int id) throws SQLException;

    List<Transaction> findAllTransactions() throws SQLException;

    void update(Transaction transaction) throws SQLException;

    void delete(int transactionId) throws SQLException;
}
