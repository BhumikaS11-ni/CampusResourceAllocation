package campus.dao;

import campus.model.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResourceDAO {

    public void saveResource(Resource resource) {

        String sql = "INSERT INTO resources "
                + "(resource_id, resource_name, resource_type, capacity, available) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, resource.getResourceId());
            statement.setString(2, resource.getResourceName());
            statement.setString(3, resource.getResourceType());
            statement.setInt(4, resource.getCapacity());
            statement.setBoolean(5, resource.isAvailable());

            statement.executeUpdate();

            System.out.println("Resource saved to database.");

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }
}
