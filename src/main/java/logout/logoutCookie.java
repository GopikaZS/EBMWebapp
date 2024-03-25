package logout;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.Cookie;


@WebServlet("/logoutCookie")
public class logoutCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public logoutCookie() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responseObject = new JSONObject();
		        // Remove the existing user cookie
		        Cookie[] cookies = request.getCookies();
		        if (cookies != null) {
		        	System.out.println(" Cookie entry");
		            for (Cookie cookie : cookies) {
		                if (cookie.getName().equals("user")) {
		                    cookie.setMaxAge(0); 
		                    response.addCookie(cookie);
		                }
		                if (cookie.getName().equals("Role")) {
		                    cookie.setMaxAge(0); 
		                    response.addCookie(cookie);
		                }
		            }
		            
		            try {
		                 System.out.println("Logout");
						responseObject.put("statusCode", 200);
					} catch (JSONException e) {
						e.printStackTrace();
					}
		            
		        }
		        else {
		        	try {
						responseObject.put("statusCode", 500);
					} catch (JSONException e) {
						e.printStackTrace();
					}
		        }

		        response.getWriter().write(responseObject+"");
		    }
		

	}


