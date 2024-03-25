package newUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import commonFile.singleton;

public class UniqueReference {
	    String generateRandomNumber() {
	        Random random = new Random();
	        char randomAlphabet = (char) (random.nextInt(26) + 'A');
	        // Generate random 9-digit number 
	        String randomDigits = String.format("%09d", random.nextInt(1000000000));       
	        String randomNumber = randomAlphabet + randomDigits;
	        return randomNumber;
	    }
	    
	    public  String uniqueRandomValue() throws Exception{
	    	Connection connection = singleton.getInstance().getConnection();
	    	 String randomNumber;
	    	while (true) {
	    	String sql = "SELECT * FROM UserRequest WHERE RequestID = ?";
	    	 try  {
	    		 PreparedStatement stmt = connection.prepareStatement(sql);
	    		  randomNumber = generateRandomNumber();
	             stmt.setString(1, randomNumber);
	             ResultSet rs = stmt.executeQuery();
	             if (!rs.next()) {
	                 // Random number is unique, break out of the loop
	                 break;
	             }
	         }
	    	 catch (SQLException e) {
	 			throw new Exception("Error in db");
	 		}
	     }
	    	return randomNumber;
	    }
	    
	    
		
}
	    
	    



