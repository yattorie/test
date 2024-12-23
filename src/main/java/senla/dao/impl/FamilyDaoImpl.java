package senla.dao.impl;

import senla.dao.FamilyDao;
import senla.exception.DaoException;
import senla.model.Family;
import senla.util.ErrorMessages;
import senla.util.mapper.FamilyMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyDaoImpl implements FamilyDao {

    private static final String SQL_SELECT_BY_ID = """
            SELECT * FROM families WHERE family_id = ?;
            """;

    private static final String SQL_SELECT_FAMILY = """
            SELECT * FROM families;
            """;

    private static final String SQL_CREATE = """
            INSERT INTO families (family_name, family_description) VALUES (?, ?);
            """;

    private static final String SQL_UPDATE = """
            UPDATE families 
            SET family_description = ? WHERE family_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM families WHERE family_id = ?;
            """;

    private final Connection connection;

    public FamilyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Family family) throws SQLException {
        if (family == null || family.getFamilyName() == null || family.getFamilyDescription() == null) {
            throw new IllegalArgumentException("Family name and description must not be null.");
        }
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_CREATE)) {
            prepareStatement.setString(1, family.getFamilyName());
            prepareStatement.setString(2, family.getFamilyDescription());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_CREATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public Family findFamilyById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return FamilyMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<Family> findAllFamilies() throws SQLException {
        List<Family> families = new ArrayList<>();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_FAMILY);
             ResultSet resultSet = prepareStatement.executeQuery()) {
            while (resultSet.next()) {
                families.add(FamilyMapper.buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return families;
    }

    @Override
    public void update(Family family) throws SQLException {
        if (family == null || family.getFamilyId() == 0 || family.getFamilyDescription() == null) {
            throw new IllegalArgumentException("Family ID and description must not be null.");
        }
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE)) {
            prepareStatement.setString(1, family.getFamilyDescription());
            prepareStatement.setInt(2, family.getFamilyId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int familyId) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE)) {
            prepareStatement.setInt(1, familyId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}
