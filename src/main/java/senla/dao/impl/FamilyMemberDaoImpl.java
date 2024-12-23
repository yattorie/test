package senla.dao.impl;

import senla.dao.FamilyMemberDao;
import senla.exception.DaoException;
import senla.model.FamilyMember;
import senla.util.ErrorMessages;
import senla.util.mapper.FamilyMemberMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyMemberDaoImpl implements FamilyMemberDao {

    private static final String SQL_SELECT_BY_ID = """
            SELECT family_member_id, family_id, user_id, role, joined_at 
            FROM family_members 
            WHERE family_member_id = ?;
            """;

    public static final String SQL_SELECT_ALL = """
            SELECT * FROM family_members;
            """;

    private static final String SQL_SELECT_BY_FAMILY_ID = """
            SELECT family_member_id, family_id, user_id, role, joined_at 
            FROM family_members 
            WHERE family_id = ?;
            """;

    private static final String SQL_CREATE = """
            INSERT INTO family_members (family_id, user_id, role, joined_at) 
            VALUES (?, ?, ?, ?);
            """;

    private static final String SQL_UPDATE = """
            UPDATE family_members 
            SET family_id = ?, user_id = ?, role = ?, joined_at = ?
            WHERE family_member_id = ?;
            """;

    private static final String SQL_DELETE = """
            DELETE FROM family_members WHERE family_member_id = ?;
            """;

    private final Connection connection;

    public FamilyMemberDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(FamilyMember member) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, member.getFamilyId());
            preparedStatement.setInt(2, member.getUserId());
            preparedStatement.setString(3, member.getRole().name());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getJoinedAt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_SAVE_ERROR.getDescription(), e);
        }
    }

    @Override
    public List<FamilyMember> findAllFamilyMembers() throws SQLException {
        List<FamilyMember> familyMembers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                familyMembers.add(FamilyMemberMapper.buildModel(resultSet));
            }
        }catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return familyMembers;
    }

    @Override
    public FamilyMember findMemberById(int memberId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return FamilyMemberMapper.buildModel(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return null;
    }

    @Override
    public List<FamilyMember> findMembersByFamilyId(int familyId) throws SQLException {
        List<FamilyMember> members = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FAMILY_ID)) {
            preparedStatement.setInt(1, familyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    members.add(FamilyMemberMapper.buildModel(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_FIND_ERROR.getDescription(), e);
        }
        return members;
    }

    @Override
    public void update(FamilyMember member) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setInt(1, member.getFamilyId());
            preparedStatement.setInt(2, member.getUserId());
            preparedStatement.setString(3, member.getRole().name());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getJoinedAt()));
            preparedStatement.setInt(5, member.getMemberId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_UPDATE_ERROR.getDescription(), e);
        }
    }

    @Override
    public void delete(int memberId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.DAO_DELETE_ERROR.getDescription(), e);
        }
    }
}
