package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import commonFile.constantQuerryAdmin;
import commonFile.singleton;

public class loginCheck {
		    private static loginCheck instance;
		    private loginCheck() {
		        
		    }
		    public static synchronized loginCheck getInstance() {
		        if (instance == null) {
		            instance = new loginCheck();
		        }
		        return instance;
		    }
		
	
	
	Connection connection = null;
	
	
	// check the user is valid or not
	
	// return a int to valid
	public int isValidUser(String userNameInput, String passwordInput, HttpServletResponse response) throws SQLException {
		try {	
			System.out.println(userNameInput);
			connection = singleton.getInstance().getConnection();
			String sqlUserFind = constantQuerryAdmin.getInstance().getIsValidUsersCheckQuerry();
			PreparedStatement stmt = connection.prepareStatement(sqlUserFind);
			stmt.setString(1, userNameInput);
			ResultSet results =  stmt.executeQuery(); 
			if (results.next()) {
				String userName = results.getString("UserName");
				String userPassword = results.getString("Password");
				String roleOfUser = results.getString("Role");
				
				if((passwordInput.equals(userPassword))&& (roleOfUser.equals("admin"))) {
					setCookie(userName,"admin",response);
					return 2;
				}
				else if((passwordInput.equals(userPassword))&& (roleOfUser.equals("user"))) {
					setCookie(userName,"user",response);
					return 3;
				}
				else {
					
					System.out.println("Password mismatch");
					return 1;
				}
			}else {
				System.out.println("User exists..");
				return 0;
				
			}
		} catch (SQLException e) {
			System.out.println("Error Login db  connect...");
			
			}
		
		
	   return 0;	
	}
	
	
	// set the session id
	
	void setCookie(String userName, String Role, HttpServletResponse response) {	 
		Cookie userNameCookie = new Cookie("user", userName);
		Cookie roleCookie = new Cookie("Role", Role);
		response.addCookie(userNameCookie);
		response.addCookie(roleCookie);
	}
}
	

	
