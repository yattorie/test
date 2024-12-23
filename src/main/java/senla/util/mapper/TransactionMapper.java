package senla.util.mapper;

import senla.model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper{

    private TransactionMapper(){}

    public static Transaction buildModel(ResultSet resultSet) throws SQLException {
        return new Transaction.Builder()
                .transactionId(resultSet.getInt("transaction_id"))
                .familyId(resultSet.getInt("family_id"))
                .userId(resultSet.getInt("user_id"))
                .categoryId(resultSet.getInt("category_id"))
                .amount(resultSet.getBigDecimal("amount"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .type(resultSet.getString("type"))
                .description(resultSet.getString("description"))
                .build();
    }
}

