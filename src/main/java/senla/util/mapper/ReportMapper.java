package senla.util.mapper;

import senla.model.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper {

    private ReportMapper() {}

    public static Report buildModel(ResultSet resultSet) throws SQLException {
        return new Report.Builder()
                .reportId(resultSet.getInt("report_id"))
                .familyId(resultSet.getInt("family_id"))
                .reportName(resultSet.getString("report_name"))
                .description(resultSet.getString("description"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
