package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static String url = "jdbc:mysql://localhost:3306/network";
    private static String username = "root";
    private static String password = "bazammanam";
    private static Connection connection;

    public static Connection connectDB(){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
	        try {
				connection = (Connection) DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
	        System.out.println("Database connected!");
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        
        return connection;
    }
}
