package senla.dao;

import senla.model.Family;

import java.sql.SQLException;
import java.util.List;

public interface FamilyDao {

    void create(Family family) throws SQLException;

    Family findFamilyById(int id) throws SQLException;

    List<Family> findAllFamilies() throws SQLException;

    void update(Family family) throws SQLException;

    void delete(int familyId) throws SQLException;
}
