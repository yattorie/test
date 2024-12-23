package senla.util.mapper;

import senla.model.FamilyBudget;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyBudgetMapper {

    private FamilyBudgetMapper() {
    }

    public static FamilyBudget buildModel(ResultSet resultSet) throws SQLException {
        return new FamilyBudget.Builder()
                .familyId(resultSet.getInt("family_id"))
                .balance(resultSet.getBigDecimal("balance"))
                .build();
    }

}

