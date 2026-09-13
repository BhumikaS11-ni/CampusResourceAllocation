package campus.dao;

import campus.model.Allocation;
import campus.model.ResourceRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllocationDAO {

    public void saveAllocation(Allocation allocation) {

        ResourceRequest request = allocation.getRequest();

        String sql = "INSERT INTO allocations "
                + "(allocation_id, request_id, allocation_date, "
                + "start_time, end_time, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, allocation.getAllocationId());
            statement.setInt(2, request.getRequestId());
            statement.setDate(3,
                    java.sql.Date.valueOf(allocation.getDate()));
            statement.setTime(4,
                    java.sql.Time.valueOf(allocation.getStartTime()));
            statement.setTime(5,
                    java.sql.Time.valueOf(allocation.getEndTime()));
            statement.setString(6, allocation.getStatus());

            statement.executeUpdate();

            System.out.println("Allocation saved to database.");

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }
}

