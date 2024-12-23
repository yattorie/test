package senla.dao;

import senla.model.FamilyBudget;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface FamilyBudgetDao {

    FamilyBudget findById(int familyId) throws SQLException;

    List<FamilyBudget> findAllFamilyBudgets() throws SQLException;

    void create(FamilyBudget familyBudget) throws SQLException;

    void update(int familyId, BigDecimal newBalance) throws SQLException;

    void delete(int familyId) throws SQLException;
}
