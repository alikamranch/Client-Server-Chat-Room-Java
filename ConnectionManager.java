package assignment_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment4SC";    
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   
    private static String username = "sa";   
    private static String password = "1234";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. for example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
