package Admin.servelt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import manageAdmin.AdminDAO;
import newUser.newUserRequest;


@WebServlet("/newUserRequestView")
public class newUserRequestView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public newUserRequestView() {    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responseObject = new JSONObject();
		try {
			
			List<JSONObject> jsonCustomers = AdminDAO.getInstance().getAllNewRequestJSON();
			System.out.println("User entries...");
			
			responseObject.put("statusCode", 200);
			responseObject.put("content", jsonCustomers);
			response.getWriter().write(responseObject.toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Error in all constomers");
			try {
				responseObject.put("statusCode", 500);
				responseObject.put("content", "something went wrong");
				response.getWriter().write(responseObject.toString());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
		
			
		}
		
	}

}
