package senla.service;

import senla.model.Family;

import java.util.List;
import java.util.Optional;

public interface FamilyService {
    List<Family> getAllFamilies();

    Optional<Family> getFamilyById(int familyId);

    void createFamily(Family family);

    void updateFamily(Family family);

    void deleteFamily(int familyId);
}