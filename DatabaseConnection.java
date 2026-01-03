import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:54322/postgres"; 
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Main method added for foundation testing
    public static void main(String[] args) {
        System.out.println("--- Database Foundation Test ---");
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("SUCCESS: Connected to the Real Estate Database.");
            }
        } catch (SQLException e) {
            System.err.println("FAILURE: Could not connect to the database.");
            System.err.println("Error details: " + e.getMessage());
            System.err.println("\nChecklist:");
            System.err.println("1. Is Supabase running? (Run 'supabase start')");
            System.err.println("2. Is the password correct?");
        }
    }
}
