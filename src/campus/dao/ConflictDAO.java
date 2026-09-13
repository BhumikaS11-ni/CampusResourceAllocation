package campus.dao;

import campus.model.Conflict;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConflictDAO {

    public void saveConflict(Conflict conflict) {

        String sql = "INSERT INTO conflicts " +
                "(conflict_id, request1_id, request2_id, reason, resolution) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, conflict.getConflictId());
            statement.setInt(2, conflict.getRequest1().getRequestId());
            statement.setInt(3, conflict.getRequest2().getRequestId());
            statement.setString(4, conflict.getReason());
            statement.setString(5, conflict.getResolution());

            statement.executeUpdate();

            System.out.println("Conflict recorded in database.");

        } catch (Exception e) {
            System.out.println(
                    "Database error while saving conflict: "
                    + e.getMessage());
        }
    }
}
