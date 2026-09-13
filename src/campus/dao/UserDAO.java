package campus.dao;

import campus.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void saveUser(User user) {

        String sql = "INSERT INTO users "
                + "(user_id, name, email, role) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());

            statement.executeUpdate();

            System.out.println("User saved to database.");

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }

    public void displayUsers() {

        String sql = "SELECT * FROM users";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println(
                        "ID: " + resultSet.getInt("user_id"));

                System.out.println(
                        "Name: " + resultSet.getString("name"));

                System.out.println(
                        "Email: " + resultSet.getString("email"));

                System.out.println(
                        "Role: " + resultSet.getString("role"));

                System.out.println("-------------------");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }
}
