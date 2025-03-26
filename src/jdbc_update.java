import java.sql.*;

public class jdbc_update {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            System.out.println("Loading MySQL JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver Loaded!");
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PL?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "tanay28");
            System.out.println("Connected to the database!");

            // Define the employee id to update and the new designation.
            // If an employee with this id doesn't exist, we'll insert one.
            int employeeId = 1;
            String newDesignation = "Senior Developer";

            // Check if an employee with the given id exists.
            String selectSQL = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectSQL, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            selectStmt.setInt(1, employeeId);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                // No record found for the given id, so insert a new employee.
                String insertSQL = "INSERT INTO employees (name, designation) VALUES ('Tanay', 'Developer')";
                Statement insertStmt = connection.createStatement();
                int insertedRows = insertStmt.executeUpdate(insertSQL, Statement.RETURN_GENERATED_KEYS);
                if (insertedRows > 0) {
                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        employeeId = keys.getInt(1);
                        System.out.println("Inserted new employee with id " + employeeId);
                    }
                    keys.close();
                }
                insertStmt.close();
            }
            rs.close();
            selectStmt.close();

            // Update the employee's designation based on the id.
            String updateSQL = "UPDATE employees SET designation = ? WHERE id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSQL);
            updateStmt.setString(1, newDesignation);
            updateStmt.setInt(2, employeeId);
            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated employee with id " + employeeId);
            } else {
                System.out.println("No employee found with id " + employeeId);
            }
            updateStmt.close();

            // Retrieve and display the updated employee details.
            String finalSelectSQL = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement finalSelectStmt = connection.prepareStatement(finalSelectSQL);
            finalSelectStmt.setInt(1, employeeId);
            ResultSet resultSet = finalSelectStmt.executeQuery();
            System.out.println("ID\tName\tDesignation");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String designation = resultSet.getString("designation");
                System.out.println(id + "\t" + name + "\t" + designation);
            }
            resultSet.close();
            finalSelectStmt.close();

            connection.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}