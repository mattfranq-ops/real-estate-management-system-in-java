import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:54322/postgres"; 
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_updated_password"; 

    public static void main(String[] args) {
        System.out.println("Testing connection...");
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the Real Estate Database successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}