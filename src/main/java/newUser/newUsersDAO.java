package newUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import java.sql.Statement;

import commonFile.constantQuerryUsers;
import commonFile.singleton;
import manageAdmin.AdminDAO;

public class newUsersDAO {

	private newUsersDAO() {}
	static newUsersDAO newUsersDAOOBJ = null;
	Connection connection = null;
	public static synchronized newUsersDAO getInstance() {
        if (newUsersDAOOBJ == null) {
        	newUsersDAOOBJ = new newUsersDAO();
        }
        return newUsersDAOOBJ;
    }
	
	
	public boolean updateNewUserStatus(String referenceId,String reasonString) throws Exception{
		String SqlString = "";
		if(reasonString.equals("approved")) {
			SqlString = "UPDATE UserRequest SET status = 'Approved' WHERE  RequestID = ?";
			createNewCustomerAccount(referenceId);
		}else {
			SqlString = "UPDATE UserRequest SET status = 'Rejected' WHERE  RequestID = ?";
		}
		
			try {
				 System.out.println( "querry entry");
					Connection  connection = singleton.getInstance().getConnection();
					PreparedStatement stmt = connection.prepareStatement(SqlString);
					 stmt.setString(1, referenceId);
					 int result = stmt.executeUpdate();
					 System.out.println(result + "querry excute");
					 return result>0;
      			} catch (Exception e) {
      				throw new Exception("Error in update a new user Status");
      			}
		
	}	
	
	
	public int insertIntoPersonDetails(String firstName, String secondName, String phoneNumber) throws Exception {
		String newUserInsertQuerry = constantQuerryUsers.getInstance().getInsertnewUserquerry();
		connection = singleton.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(newUserInsertQuerry, PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, secondName);
        preparedStatement.setString(3, phoneNumber);
        
        int affectedRows = preparedStatement.executeUpdate();
        int userIDFromDB  = 0;
        if (affectedRows > 0) {
        	 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        	 if (generatedKeys.next()) {
                  int lastInsertPersonId = generatedKeys.getInt(1);
                  userIDFromDB = insertPersonTableToUsers(lastInsertPersonId);
             } else {
                 System.out.println("Failed to retrieve last insert ID.");
             }
        }
        
        return userIDFromDB;
	}
	
	public void createNewCustomerAccount(String requestId) throws Exception {
		System.out.println("Insert the user into a new user ");
		connection = singleton.getInstance().getConnection();
		String selectUserNewRequest = constantQuerryUsers.getInstance().getSelectUserNewRequest();
		PreparedStatement preparedStatement = connection.prepareStatement(selectUserNewRequest);
		preparedStatement.setString(1, requestId);
		System.out.println(preparedStatement.toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println("Querry excuitedd success");
		if (resultSet.next()) {
			 String firstName = resultSet.getString("FirstName");
             String lastName = resultSet.getString("LastName");
             String phoneNumber = resultSet.getString("PhoneNumber");
             String street = resultSet.getString("Street");
             String district = resultSet.getString("District");
             String state = resultSet.getString("State");
             int userIdUnique = insertIntoPersonDetails(firstName,lastName,phoneNumber);
            
             if(userIdUnique !=0) {
            	 insertIntoAddressTable(street, district, state, userIdUnique);
             }else {
            	 // Error 
             }
		}
	}
	
	
	
	public String uniqueUsernameGenerator() throws Exception{
		Connection connection = singleton.getInstance().getConnection();
   	 	String unqiueUserName;
   		while (true) {
	    	String sql = "SELECT * FROM UserRequest WHERE RequestID = ?";
	    	 try  {
	    		 PreparedStatement stmt = connection.prepareStatement(sql);
	    		 unqiueUserName = generateRandomNumber();
	             stmt.setString(1, unqiueUserName);
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
	    	return unqiueUserName;
	}
	
	
	String generateRandomNumber() {
        Random random = new Random();
        char randomAlphabet = (char) (random.nextInt(26) + 'A');
        char randomAlphabet1 = (char) (random.nextInt(26) + 'A');
        // Generate random 9-digit number 
        String randomDigits = String.format("%09d", random.nextInt(1000000000));       
        String randomNumber = randomAlphabet+""+randomAlphabet1 + randomDigits;
        return randomNumber;
    }
    
	
	public int insertPersonTableToUsers(int personId) throws Exception {
		String insertUserQuery = constantQuerryUsers.getInstance().getInsertUserQuerry();
		Connection connection = singleton.getInstance().getConnection();
		int userId = 0;
		 try  {
    		 PreparedStatement statement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
    		 	
    		 	String uniqueUsername = uniqueUsernameGenerator();
    		 	statement.setString(1, uniqueUsername);
    		 	statement.setString(2, uniqueUsername);
    		 	statement.setInt(3, personId);
    		 	statement.executeUpdate();
    		 	
    		 	 ResultSet userGeneratedKeys = statement.getGeneratedKeys();
    		 	 if (userGeneratedKeys.next()) {
    		 		 	userId = userGeneratedKeys.getInt(1);
    		           
    		      }
         }
    	 catch (SQLException e) {
 			throw new Exception("Error in db");
 		}
		 
		 return userId;
	}
	
	
	void insertIntoAddressTable(String street, String district,  String state,int userId) throws SQLException {
		String insertAddressTableQuerry = constantQuerryUsers.getInstance().getInsertIntoAddress();
		Connection connection = singleton.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(insertAddressTableQuerry);
		 preparedStatement.setString(1, street);
         preparedStatement.setString(2, district);
         preparedStatement.setInt(3, userId);
         preparedStatement.setString(4, state);
         preparedStatement.executeUpdate();
	}
	
	
//	public int  getHighestPersonID() {Badminton
//		String getHighestPersonIDQuerry = constantQuerryUsers.getInstance().getGetLongestPersonID();
//		connection = 
//		
//		int highestPersonID ;
//		return 0;
//	}
	
}
