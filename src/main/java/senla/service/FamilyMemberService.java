package senla.service;

import senla.model.FamilyMember;

import java.util.List;
import java.util.Optional;

public interface FamilyMemberService {
    List<FamilyMember> getAllFamilyMembers();
    Optional<FamilyMember> getFamilyMemberById(int memberId);
    void createFamilyMember(FamilyMember familyMember);
    void updateFamilyMember(FamilyMember familyMember);
    void deleteFamilyMember(int memberId);
}
