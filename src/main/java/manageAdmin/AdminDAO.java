package manageAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import commonFile.constantQuerryAdmin;
import javaFiles.Admin;
import javaFiles.Customer;
import commonFile.singleton;
import newUser.newUserRequest;

public class AdminDAO {

	private AdminDAO() {}
	static AdminDAO adminDAO = null;
	
	public static synchronized AdminDAO getInstance() {
        if (adminDAO == null) {
        	adminDAO = new AdminDAO();
        }
        return adminDAO;
    }
	
	public List<JSONObject> getAllNewRequestJSON() throws SQLException{
		List<newUserRequest> allRerquest = getAllNewRequest();
		List<JSONObject> jsonCustomers = new ArrayList<>();
		for(newUserRequest newRequest:allRerquest) {	
			JSONObject jsonCustomer = new JSONObject();
			jsonCustomer = newRequest.newUserToJson(); 
			jsonCustomers.add(jsonCustomer);
			
		}
		return jsonCustomers;
	}
     
   public List<newUserRequest> getAllNewRequest() throws SQLException{
	   String sqlString = constantQuerryAdmin.getInstance().getGetAllNewUserPendingRequest();
         
         try {
        	   Connection connection = singleton.getInstance().getConnection();
        	 	PreparedStatement stmt = connection.prepareStatement(sqlString);
        	 	System.out.println("start all user");
        	 	ResultSet results =  stmt.executeQuery();
        	 	Admin adminObj = new Admin();
        	 	System.out.println("success all user");
        	 	while(results.next()) {
        	 		System.out.println("user...");
    	    		String requestUniqueId = results.getString("ur.RequestID");
    				String requestDate = results.getString("ur.RequestDate");
    				String requestStatus = results.getString("ur.Status");
    				String firstName = results.getString("ur.FirstName");
    				String lastName = results.getString("ur.LastName");
    				String phoneNumber = results.getString("ur.PhoneNumber");
    				String Address = results.getString("FullAddress");
    				
    				System.out.println(" Strart User "+ firstName);
    				
				    newUserRequest requestObj = new newUserRequest(firstName, lastName, phoneNumber,  Address, requestUniqueId,requestDate,requestStatus);
    		        System.out.println(" add User "+ firstName); 
    		        adminObj.setUserRequests(requestObj);
    	    	}
        	 	return adminObj.getUserRequests();
         }catch (SQLException e) {
        	 throw new SQLException("Error in all new request ");
         }
     }

   
   
   // All customer List
   public List<JSONObject> getAllCustomersJSON() throws SQLException {
	   List<Customer> allCustomers = getAllConstomers();
		List<JSONObject> jsonCustomers = new ArrayList<>();
		System.out.println("User entries...");
		for(Customer customer:allCustomers) {
			System.out.println("Customers");
			JSONObject jsonCustomer = new JSONObject();
			 jsonCustomer = (JSONObject) customer.customerToJson();
			  System.out.println(jsonCustomer);
			  jsonCustomers.add(jsonCustomer);
			 
			}
		
		return jsonCustomers;
	   
   }
   
   
   public List<Customer> getAllConstomers() throws SQLException{
		String sqlUserFind = "SELECT u.UserID, u.UserName, pd.FirstName, pd.LastName, pd.phoneNumber,CONCAT(a.Street, ', ', a.District, ', ', a.State) AS FullAddress FROM User u JOIN Address a ON u.UserID = a.UserID JOIN PersonDetails pd ON u.PersonID = pd.PersonID WHERE u.Role like 'user'";
		Admin adminObj = new Admin();
		try {
			
			Connection connection = singleton.getInstance().getConnection();
	    	PreparedStatement stmt = connection.prepareStatement(sqlUserFind);
	    	ResultSet results =  stmt.executeQuery();
	    	while(results.next()) {
	    		System.out.println("User "+ results.getString("u.UserName"));
	    		String userName = results.getString("u.UserName");
				String firstName = results.getString("pd.FirstName");
				String lastname = results.getString("pd.LastName");
				String phoneNumber = results.getString("pd.phoneNumber");
				String Address = results.getString("FullAddress");
				System.out.println(" Strart User "+ results.getString("u.UserName"));
				Customer customerObj = new Customer(userName, firstName, lastname, phoneNumber, Address);
				adminObj.setConsumer(customerObj);     
		        System.out.println(" add User "+ results.getString("u.UserName"));     
	    	}
	    	 return adminObj.getConsumerList();
		} catch (SQLException e) {
			System.out.println("Error in db view Customer");
			throw new SQLException("View User Exception ");
		}
		
	}
   
   
   
	
}
