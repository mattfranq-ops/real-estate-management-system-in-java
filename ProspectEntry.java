import java.util.Scanner;
import java.sql.*;


public class ProspectEntry {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("++++++++++++ New Applicant Form ++++++++++++");

        System.out.print("Applicant name: ");
        String name = scanner.nextLine();

        System.out.print("Annual Income: ");
        double income = scanner.nextDouble();

        System.out.print("Credit Score: ");
        int credit = scanner.nextInt();

        System.out.print("Unit Applying for (ID Number): ");
        int unitId = scanner.nextInt();

        //Creating Template for SQL with placeholders
        String sql = "INSERT INTO prospects (name, annual_income, credit_score, unit_applying_for) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            //Value binders for ? placeholders

            pstmt.setString(1, name);
            pstmt.setDouble(2, income);
            pstmt.setInt(3, credit);
            pstmt.setInt(4, unitId);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0){
                System.out.println("\n[Operation Succesful: " + name + " has been added]");
            }
        }catch (SQLException e){
            System.out.println("\n[ERROR: could NOT save prospect... \n" + e.getMessage());
        }

    }
}

