package campus.main;

import campus.dao.DatabaseConnection;
import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try {
            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println("================================");
            System.out.println(" JDBC CONNECTION SUCCESSFUL!");
            System.out.println(" Connected to MySQL database.");
            System.out.println("================================");

            connection.close();

        } catch (Exception e) {

            System.out.println("JDBC CONNECTION FAILED!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
