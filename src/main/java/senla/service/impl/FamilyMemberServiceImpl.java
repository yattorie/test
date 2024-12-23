package senla.service.impl;

import senla.dao.FamilyMemberDao;
import senla.dao.impl.FamilyMemberDaoImpl;
import senla.model.FamilyMember;
import senla.service.FamilyMemberService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FamilyMemberServiceImpl implements FamilyMemberService {
    private static FamilyMemberService instance;
    private final FamilyMemberDao familyMemberDao;

    private FamilyMemberServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.familyMemberDao = new FamilyMemberDaoImpl(connection);
    }

    public static synchronized FamilyMemberService getInstance() {
        if (instance == null) {
            instance = new FamilyMemberServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FamilyMember> getAllFamilyMembers() {
        try {
            return familyMemberDao.findAllFamilyMembers();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all family members", e);
        }
    }

    @Override
    public Optional<FamilyMember> getFamilyMemberById(int memberId) {
        try {
            return Optional.ofNullable(familyMemberDao.findMemberById(memberId));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting family member by ID", e);
        }
    }

    @Override
    public void createFamilyMember(FamilyMember familyMember) {
        try {
            familyMemberDao.save(familyMember);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating family member", e);
        }
    }

    @Override
    public void updateFamilyMember(FamilyMember familyMember) {
        try {
            familyMemberDao.update(familyMember);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating family member", e);
        }
    }

    @Override
    public void deleteFamilyMember(int memberId) {
        try {
            familyMemberDao.delete(memberId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting family member", e);
        }
    }
}
