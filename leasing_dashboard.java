import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class leasing_dashboard {
    public static void main (String[] args){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("!!Welcome to your Leasing Consultant Dashboard!!");
        System.out.println(String.format("%-15s | %-10s | %-12s | %-15s","Tenant", "Status", "Days Left", "Action Required"));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        String query = "SELECT name, rent_status, lease_end FROM tenants WHERE is_active = true";

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rslt = stmt.executeQuery(query)){

                LocalDate today = LocalDate.now();
                while (rslt.next()){
                    String name = rslt.getString("Name");
                    String rentStatus = rslt.getString("rent_status");
                    LocalDate leaseEnd = rslt.getDate("lease_end").toLocalDate();

                    //Day Calc
                    long daysLeft = ChronoUnit.DAYS.between(today, leaseEnd);

                    //Renew or Move-Out
                    String action = "None";
                    if (daysLeft <= 30){
                        action = "[RENEW OR MOVE-OUT]";
                    }

                    System.out.println(String.format("%-15s | %-10s | %-12d | %-15s", name, rentStatus, daysLeft, action));
                }
            }catch (SQLException e){
                System.err.println("Dashboard Err: " + e.getMessage());
        }
    }
}
