package commonFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class singleton {
	    private static singleton instance;
	    private Connection connection;
	    
	    private singleton() {
	  
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/EBM", "gopika", "1234"
		        );
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	

	    public static singleton getInstance() {
	        if (instance == null) {
	            instance = new singleton();
	        }
	        return instance;
	    }

	    public Connection getConnection() {
	        return connection;
	    }
}
	


