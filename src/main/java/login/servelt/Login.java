package login.servelt;

import java.io.IOException;
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

import login.loginCheck;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String passwd = request.getParameter("password");
		JSONObject jsonResponse = new JSONObject();
		
		int isValidInputs = 0;
		try {
			
			isValidInputs = loginCheck.getInstance().isValidUser(username,passwd,response);
			// sent login to users
			  switch (isValidInputs) {
		        case 0:
		        	jsonResponse.put("statusCode", 500);
		            jsonResponse.put("message", "Invalid Username");
		            break;
		        case 1:
		        	jsonResponse.put("statusCode", 500);
		            jsonResponse.put("message", "Password is not correct");
		            break;
		        case 2:
		        	jsonResponse.put("statusCode", 200);
		            jsonResponse.put("role", "admin");
		            break;
		        case 3:
		        	jsonResponse.put("statusCode", 200);
		            jsonResponse.put("role", "user");
		            break;
		    }
			
			
			
		} catch (Exception e) {
			System.out.println("SQL Exception");
		}
		

		
		response.getWriter().write(jsonResponse.toString());
		
	}

}



