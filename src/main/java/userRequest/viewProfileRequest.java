package userRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import javaFiles.Customer;
import manageUser.viewProfile;
import newUser.newUserRequest;


@WebServlet("/viewProfile")
public class viewProfileRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public viewProfileRequest() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responsObject = new JSONObject();
		viewProfile userProfile = new viewProfile();
		try {
			Customer customerObj = userProfile.getUserProfile(request);
			System.out.println(customerObj);
			if(customerObj !=null) {
				JSONObject customerJson = customerObj.customerToJson();
				responsObject.put("StatusCode", 200);
				responsObject.put("Content", customerJson);
				response.getWriter().write(responsObject+"");
			}
			
			System.out.println("User");
			
		} catch (Exception e) {
				try {
					responsObject.put("StatusCode", 500);
					response.getWriter().write(responsObject+"");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
		}
		
	}

}
