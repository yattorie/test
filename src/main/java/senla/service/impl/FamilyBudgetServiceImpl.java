package senla.service.impl;

import senla.dao.FamilyBudgetDao;
import senla.dao.impl.FamilyBudgetDaoImpl;
import senla.model.FamilyBudget;
import senla.service.FamilyBudgetService;
import senla.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FamilyBudgetServiceImpl implements FamilyBudgetService {
    private static FamilyBudgetService instance;
    private final FamilyBudgetDao familyBudgetDao;

    private FamilyBudgetServiceImpl() {
        Connection connection = ConnectionManager.getConnection();
        this.familyBudgetDao = new FamilyBudgetDaoImpl(connection);
    }

    public static synchronized FamilyBudgetService getInstance() {
        if (instance == null) {
            instance = new FamilyBudgetServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FamilyBudget> getAllFamilyBudgets() {
        try {
            return familyBudgetDao.findAllFamilyBudgets();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all family budgets", e);
        }
    }

    @Override
    public Optional<FamilyBudget> getFamilyBudgetById(int familyId) {
        try {
            return Optional.ofNullable(familyBudgetDao.findById(familyId));
        } catch (SQLException e) {
            throw new RuntimeException("Error getting family budget by ID", e);
        }
    }

    @Override
    public void createFamilyBudget(FamilyBudget familyBudget) {
        try {
            familyBudgetDao.create(familyBudget);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating family budget", e);
        }
    }

    @Override
    public void updateFamilyBudget(FamilyBudget familyBudget) {
        try {
            familyBudgetDao.update(familyBudget.getFamilyId(), familyBudget.getBalance());
        } catch (SQLException e) {
            throw new RuntimeException("Error updating family budget", e);
        }
    }

    @Override
    public void deleteFamilyBudget(int familyId) {
        try {
            familyBudgetDao.delete(familyId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting family budget", e);
        }
    }
}