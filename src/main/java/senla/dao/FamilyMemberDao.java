package senla.dao;

import senla.model.FamilyMember;

import java.sql.SQLException;
import java.util.List;

public interface FamilyMemberDao {

    void save(FamilyMember member) throws SQLException;

    List<FamilyMember> findAllFamilyMembers() throws SQLException;

    FamilyMember findMemberById(int memberId) throws SQLException;

    List<FamilyMember> findMembersByFamilyId(int familyId) throws SQLException;

    void update(FamilyMember member) throws SQLException;

    void delete(int memberId) throws SQLException;
}
