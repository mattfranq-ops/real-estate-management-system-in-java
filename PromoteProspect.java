import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

public class PromoteProspect{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("++++++++++++ Lease Promotion Tool ++++++++++++");
        System.out.print("Enter the ID of the Prospect to Promote: ");

        int prospectId = scanner.nextInt();

        // Dates

        LocalDate start = LocalDate.now();
        LocalDate end = start.plusYears(1);

        //SQL Commands
        String moveSql = "INSERT INTO tenants (unit_id, name, email, lease_start, lease_end, rent_status, is_active) " +
                         "SELECT unit_applying_for, name, 'email@placeholder.com', ?, ?, 'Good Standing', true " +
                         "FROM prospects WHERE id = ?";

        String deleteSql = "DELETE FROM prospects WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()){

            conn.setAutoCommit(false); //Ensure safe transactions

            try (PreparedStatement moveStmt = conn.prepareStatement(moveSql);
                PreparedStatement delStmt = conn.prepareStatement(deleteSql)){

                //Prepare variables for movement
                moveStmt.setDate(1, Date.valueOf(start));
                moveStmt.setDate(2, Date.valueOf(end));
                moveStmt.setInt(3, prospectId);

                //Binders for Delete
                delStmt.setInt(1, prospectId);
                delStmt.executeUpdate();

                //Now safe to commit changes
                conn.commit();
                System.out.println("\n[Success! Prospect [" + prospectId + "] has advanced to Tenant Status!]");
                } catch (SQLException e){
                    conn.rollback();
                    System.out.println("\n [Err... Promotion Failed. System Rollback... " + e.getMessage() + "]");
                }
        } catch (SQLException e){
            System.out.println("\n[DATABASE ERR... " + e.getMessage() + "]");
        }
    }
}