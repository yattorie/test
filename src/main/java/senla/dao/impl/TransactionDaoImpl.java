package senla.dao.impl;

import senla.dao.TransactionDao;
import senla.exception.DaoException;
import senla.model.Transaction;
import senla.util.ErrorMessages;
import senla.util.mapper.TransactionMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    public static final String SQL_SELECT_BY_ID = """
            SELECT * FROM transactions WHERE transaction_id = ?;
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM transactions;
            """;

    public static final String SQL_CREATE = """
            INSERT INTO transactions (family_id, user_id, category_id, amount, type, description, created_at) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    public static final String SQL_UPDATE = """
            UPDATE transactions 
            SET family_id = ?, user_id = ?, category_id = ?, amount = ?, type = ?, description = ? 
            WHERE transaction_id = ?;
            """;

    public static final String SQL_DELETE = """
            DELETE FROM transactions WHERE transaction_id = ?;
            """;

    private final Connection connection;

    public TransactionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Transaction transaction) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, transaction.getFamilyId());
            preparedStatement.setInt(2, transaction.getUserId());
            preparedStatement.setInt(3, transaction.getCategoryId());
            preparedStatement.setBigDecimal(4, transaction.getAmount());
            preparedStatement.setString(5, transaction.getType());
            preparedStatement.setString(6, transaction.getDescription());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(transaction.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }



    @Override
    public Transaction findTransactionById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return TransactionMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Transaction> findAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                transactions.add(TransactionMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return transactions;
    }

    @Override
    public void update(Transaction transaction) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, transaction.getFamilyId());
            preparedStatement.setInt(2, transaction.getUserId());
            preparedStatement.setInt(3, transaction.getCategoryId());
            preparedStatement.setBigDecimal(4, transaction.getAmount());
            preparedStatement.setString(5, transaction.getType());
            preparedStatement.setString(6, transaction.getDescription());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(transaction.getCreatedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int transactionId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, transactionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}