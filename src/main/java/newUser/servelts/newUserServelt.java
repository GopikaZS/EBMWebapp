package newUser.servelts;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import newUser.*;
import commonFile.singleton;


@WebServlet("/newUserServelt")
public class newUserServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public newUserServelt() {
        super();

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// return the request add or not
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject reponse = new JSONObject();
		
		try {
			System.out.println("Entry ");
			int isAddRequest = addRequestToDB(request); 
			if (isAddRequest == 1) {
				reponse.put("Statuscode", 200);
				reponse.put("referenceId", referenceId);
				response.getWriter().write(reponse.toString());
			}else if(isAddRequest == 2){
				reponse.put("Statuscode", 500);
				reponse.put("Error", "duplicate address");
				response.getWriter().write(reponse.toString());
			}
			else {
				reponse.put("Statuscode", 501);
				reponse.put("Error", "Address in request list");
				response.getWriter().write(reponse.toString());
			}
			
		}catch (Exception e) {
			
			try {
				System.out.println("Error");
				reponse.put("Statuscode", 400);
				reponse.put("Error", "Something went wrong");
			} catch (JSONException e1) {
				System.out.println("Hello");
				e1.printStackTrace();
			}
			
			response.getWriter().write(reponse +"");
		}
		
		
		
	}


	Connection connection = null;
	PreparedStatement stmt = null;
	String referenceId = "";

	int addRequestToDB(HttpServletRequest request) throws Exception {
		try {
			
			String stringObj = request.getReader().readLine();
			System.out.println("Entry.....");
			System.out.println(stringObj);
				JSONObject object = new JSONObject(stringObj);
				System.out.println(object);
				String firstName = (String) object.get("firstName");
				System.out.println("Entry.....");
				System.out.println(firstName);
			    String secondName = (String) object.get("lastName");
		        String phoneNumber = (String) object.get("phoneNumber");
		        String street = (String) object.get("street");
		        String district = (String) object.get("district");
		        String state = (String) object.get("state"); 
		        String userID = (String) object.get("userID");
		        boolean isValidRequest = addressExists(street,district,state);
		        String setRequest = "Pending";
		        UniqueReference uniqueId = new UniqueReference();
		        referenceId = uniqueId.uniqueRandomValue();
		        System.out.println(isValidRequest);
				if(!isValidRequest) {
					return 2;
				}
				
				boolean isRequestAddress = isAlreadyRequest(street,district,state);
				if(!isRequestAddress) {
					return 3;
				}
				
				
				 connection = singleton.getInstance().getConnection();
				 String sql = "INSERT INTO UserRequest (FirstName,LastName, PhoneNumber, Street, District, State, RequestDate, Status,userID,RequestID) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?)";
				 stmt = connection.prepareStatement(sql);
				 stmt.setString(1, firstName);
				 stmt.setString(2, secondName);
				 stmt.setString(3, phoneNumber); 
				 stmt.setString(4, street); 
				 stmt.setString(5,district); 
				 stmt.setString(6, state); 
				 stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				 stmt.setString(8, setRequest);
				 stmt.setString(9, userID);
				 stmt.setString(10, referenceId);
				 System.out.println(" querry start");
				 int addRequest = stmt.executeUpdate();
				 System.out.println(" querry update");
				 System.out.println(addRequest);
				 return addRequest;
				 
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in add new request");
		}
		
		
	}
	
	
	boolean addressExists(String street, String district, String state) throws SQLException{
		try {
			    connection = singleton.getInstance().getConnection();
				stmt = connection.prepareStatement("SELECT * FROM Address WHERE Street = ? AND District = ? AND State = ?");
			 	stmt.setString(1, street);
	            stmt.setString(2, district);
	            stmt.setString(3, state);
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("User entry...");
	            if(rs.next()) {
	            	System.out.println("invalid user");
	            	return false;
	            }
	            
	            return true;
		} catch (SQLException e) {
			System.out.println("User exists Error...");
			e.printStackTrace();
			return false;
		}
		
	}
	
	boolean isAlreadyRequest(String street, String district, String postalCode) throws SQLException {
		stmt = connection.prepareStatement("SELECT * FROM UserRequest WHERE Street = ? AND District = ? AND State = ?");
		stmt.setString(1, street);
        stmt.setString(2, district);
        stmt.setString(3, postalCode);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	return false;
        }
        
        return true;
	}


}










