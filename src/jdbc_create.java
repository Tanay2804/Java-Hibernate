import java.sql.*;

public class jdbc_create {
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

            Statement statement = connection.createStatement();
            String table = "Employees";
            // Create user table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS "
                    + table
                    + "("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "designation VARCHAR(100) NOT NULL"
                    + ")"; // Removed the extra comma here
            statement.executeUpdate(createTableSQL);
            System.out.println("Employees table created or already exists.");

            // Insert the name "Tanay" into the Employee table
            String insertSQL = "INSERT INTO Employees (name,designation) VALUES ('Tanay','DEVELOPER');";
            statement.executeUpdate(insertSQL);
            System.out.println("Inserted Employee 'Tanay' into the Employees table.");
        
            // Retrieve and print inserted data
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
            System.out.println("ID\tName\tDesignation");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String designation = resultSet.getString("designation");
                System.out.println(id + "\t" + name + "\t" + designation);
            }
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}