package manageUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javaFiles.Customer;
import login.getLoginUser;
import commonFile.singleton;

public class viewProfile {

	public Customer getUserProfile(HttpServletRequest request) throws Exception{
		Customer customerObj = null;
		String sqlString = "SELECT u.UserName, pd.FirstName, pd.LastName, pd.PhoneNumber, concat(a.Street, ', ', a.District, ', ', a.State) as FullAddress FROM User u JOIN PersonDetails pd ON u.PersonID = pd.PersonID JOIN Address a ON u.UserID = a.UserID WHERE u.UserName like ?";
		 Cookie[] cookies = request.getCookies();
		 String userNameCookie = getLoginUser.getUserName(cookies);
		 System.out.println("User profile");
		 System.out.println(userNameCookie);
		
		try {
			Connection connection = singleton.getInstance().getConnection();
    	 	PreparedStatement stmt = connection.prepareStatement(sqlString);
    	 	stmt.setString(1, userNameCookie);
    	 	System.out.println("User profile");
    	 	ResultSet results =  stmt.executeQuery();
    	 	System.out.println("User profile");
    	 	
    	 	if(results.next()) {
    	 		System.out.println("User "+ results.getString("u.UserName"));
	    		String userName = results.getString("u.UserName");
				String firstName = results.getString("pd.FirstName");
				String lastname = results.getString("pd.LastName");
				String phoneNumber = results.getString("pd.phoneNumber");
				String Address = results.getString("FullAddress");
				
				customerObj = new Customer(userName, firstName, lastname, phoneNumber, Address);
				return customerObj;
    	 	}
    	 	else {
    	 		System.out.println("Gopika");
    	 		throw new Exception("Error in user Name");
    	 	}
		} catch (SQLException e) {
			
		}
		
		return customerObj;
	}
}
