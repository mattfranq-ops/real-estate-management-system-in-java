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
                    String name = rslt.getString("name");
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
                System.out.println("\n[New Applicant Ready]");
                System.out.println(String.format("%-15s | %-12s | %-8s | %-15s", "Prospect", "Mo Income", "Credit", "Decision"));

                //Check to see if income qualifies prospect for unit
                String prospectQuery = "select p.name, p.annual_income, p.credit_score, u.monthly_rent " +
                                        "From prospects p " +
                                        "JOIN units u ON p.unit_applying_for = u.id";

                ResultSet pRslt = stmt.executeQuery(prospectQuery);

                while (pRslt.next()){
                    String pName = pRslt.getString("name");
                    double monthlyIncome = pRslt.getDouble("annual_income") / 12;
                    int credit = pRslt.getInt("credit_score");
                    double rent = pRslt.getDouble("monthly_rent"); 

                    //Decision maker
                    boolean incomePass = monthlyIncome >= (rent * 3);
                    boolean creditPass = credit >= 600;

                    String decision = (incomePass && creditPass) ? "APPROVED" : "DENIED";

                    System.out.println(String.format("%-15s | $%-11.2f | %-8d | %-15s", pName, monthlyIncome, credit, decision));

                }
            }catch (SQLException e){
                System.err.println("Dashboard Err: " + e.getMessage());
        }
    }
}
