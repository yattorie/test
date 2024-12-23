package senla.service.impl;

import senla.dao.FamilyDao;
import senla.dao.impl.FamilyDaoImpl;
import senla.model.Family;
import senla.service.FamilyService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FamilyServiceImpl implements FamilyService {
    private static FamilyService instance;
    private final FamilyDao familyDao;

    private FamilyServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.familyDao = new FamilyDaoImpl(connection);
    }

    public static synchronized FamilyService getInstance() {
        if (instance == null) {
            instance = new FamilyServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Family> getAllFamilies() {
        try {
            return familyDao.findAllFamilies();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all families", e);
        }
    }

    @Override
    public Optional<Family> getFamilyById(int familyId) {
        try {
            return Optional.ofNullable(familyDao.findFamilyById(familyId));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting family by ID", e);
        }
    }

    @Override
    public void createFamily(Family family) {
        try {
            familyDao.create(family);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating family", e);
        }
    }

    @Override
    public void updateFamily(Family family) {
        try {
            familyDao.update(family);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating family", e);
        }
    }

    @Override
    public void deleteFamily(int familyId) {
        try {
            familyDao.delete(familyId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting family", e);
        }
    }
}