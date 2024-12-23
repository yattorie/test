package senla.util;

import senla.exception.TransactionFailedException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionFailedException(ErrorMessages.TRANSACTION_BEGIN_ERROR.getDescription(), e);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionFailedException(ErrorMessages.TRANSACTION_COMMIT_ERROR.getDescription(), e);
        } finally {
            enableAutoCommit();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionFailedException(ErrorMessages.TRANSACTION_ROLLBACK_ERROR.getDescription(), e);
        } finally {
            enableAutoCommit();
        }
    }

    private void enableAutoCommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionFailedException(ErrorMessages.AUTO_COMMIT_ENABLE_ERROR.getDescription(), e);
        }
    }
}
