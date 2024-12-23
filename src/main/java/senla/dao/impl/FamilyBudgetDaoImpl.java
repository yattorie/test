package senla.dao.impl;

import senla.dao.FamilyBudgetDao;
import senla.exception.DaoException;
import senla.model.FamilyBudget;
import senla.util.ErrorMessages;
import senla.util.mapper.FamilyBudgetMapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyBudgetDaoImpl implements FamilyBudgetDao {

    private static final String SQL_FIND_BY_ID = """
            SELECT * FROM family_budgets WHERE family_id = ?;
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM family_budgets; 
            """;

    private static final String SQL_CREATE = """
            INSERT INTO family_budgets (family_id, balance)
            VALUES (?, ?);
            """;

    private static final String SQL_UPDATE_BALANCE = """
            UPDATE family_budgets
            SET balance = ?
            WHERE family_id = ?;
            """;

    private static final String SQL_DELETE_BY_ID = """
            DELETE FROM family_budgets WHERE family_id = ?;
            """;

    private final Connection connection;

    public FamilyBudgetDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FamilyBudget findById(int familyId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return FamilyBudgetMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<FamilyBudget> findAllFamilyBudgets() throws SQLException {
        List<FamilyBudget> familyBudgets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                familyBudgets.add(FamilyBudgetMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return familyBudgets;
    }

    @Override
    public void create(FamilyBudget familyBudget) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, familyBudget.getFamilyId());
            preparedStatement.setBigDecimal(2, familyBudget.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void update(int familyId, BigDecimal newBalance) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, familyId);
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }


    @Override
    public void delete(int familyId) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            prepareStatement.setInt(1, familyId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}