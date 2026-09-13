package campus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuditLogDAO {

    public void saveLog(String action, int requestId, String description) {

        String sql = "INSERT INTO audit_logs " +
                "(action, request_id, description) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, action);
            statement.setInt(2, requestId);
            statement.setString(3, description);

            statement.executeUpdate();

            System.out.println("Audit log recorded.");

        } catch (Exception e) {
            System.out.println(
                    "Database error while saving audit log: "
                    + e.getMessage());
        }
    }
}
