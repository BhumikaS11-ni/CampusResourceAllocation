package campus.dao;

import campus.model.ResourceRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestDAO {

    public void saveRequest(ResourceRequest request) {

        String sql = "INSERT INTO resource_requests "
                + "(request_id, user_id, resource_id, request_date, "
                + "start_time, end_time, purpose, priority, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, request.getRequestId());
            statement.setInt(2, request.getUser().getUserId());
            statement.setInt(3, request.getResource().getResourceId());
            statement.setDate(4,
                    java.sql.Date.valueOf(request.getDate()));
            statement.setTime(5,
                    java.sql.Time.valueOf(request.getStartTime()));
            statement.setTime(6,
                    java.sql.Time.valueOf(request.getEndTime()));
            statement.setString(7, request.getPurpose());
            statement.setInt(8, request.getPriority());
            statement.setString(9, request.getStatus().name());

            statement.executeUpdate();

            System.out.println("Request saved to database.");

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }

    public void updateRequestStatus(ResourceRequest request) {

        String sql = "UPDATE resource_requests "
                + "SET status = ? WHERE request_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, request.getStatus().name());
            statement.setInt(2, request.getRequestId());

            statement.executeUpdate();

            System.out.println("Request status updated in database.");

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }
}