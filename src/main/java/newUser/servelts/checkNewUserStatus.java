package newUser.servelts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import commonFile.singleton;


@WebServlet("/checkNewUserStatus")
public class checkNewUserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public checkNewUserStatus() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uniqueID = request.getParameter("referenceId");
		JSONObject reponse = new JSONObject();
		try {
			String sql = "SELECT RequestID,Status FROM UserRequest WHERE RequestID = ?";
			Connection connection = singleton.getInstance().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, uniqueID);
			System.out.println("Check the unique id is valid");
            ResultSet results =  stmt.executeQuery(); 
            if(results.next()) {
            	String status = results.getString("Status");
            	reponse.put("statusCode", 200);
				reponse.put("referenceId", uniqueID);
				reponse.put("status", status);
				if(status.equals("Approved")) {
					// if approved
				}
				
				response.getWriter().write(reponse + "");
            }
            else {
            	reponse.put("statuscode", 500);	
            	reponse.put("Error", "Invalid Reference ID");	
            	response.getWriter().write(reponse + "");
            }
            
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
