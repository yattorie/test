package senla.service;

import senla.model.FamilyBudget;

import java.util.List;
import java.util.Optional;

public interface FamilyBudgetService {
    List<FamilyBudget> getAllFamilyBudgets();

    Optional<FamilyBudget> getFamilyBudgetById(int familyId);

    void createFamilyBudget(FamilyBudget familyBudget);

    void updateFamilyBudget(FamilyBudget familyBudget);

    void deleteFamilyBudget(int familyId);
}