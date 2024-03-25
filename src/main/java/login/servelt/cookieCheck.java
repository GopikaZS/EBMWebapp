	package login.servelt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


import javax.servlet.http.Cookie;



@WebServlet("/cookieCheck")
public class cookieCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public cookieCheck() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
    String userName = "",role = "" ;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responseobj = new JSONObject();
		
		if(hasCookie(request)) {
			try {
				responseobj.put("statusCode", 200);
				responseobj.put("userName",userName);
				responseobj.put("role",role);
				response.getWriter().write(responseobj.toString());
			} catch (Exception e) {
				
			}
			
		}
		else {
			try {
				responseobj.put("statusCode", 500);
			} catch (Exception e) {
				System.out.println("Error in json");
			}
			
			System.out.println("");
			response.getWriter().write(responseobj.toString());
		}
		
	}
	
	  public  boolean hasCookie(HttpServletRequest request) {
		  userName = "";
		  role = "" ;
	        Cookie[] cookies = request.getCookies();
	        
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	            	
	            	if (cookie.getName().equals("user")) {
	            		System.out.println("UserName");
	            		try {

		            		userName = cookie.getValue();
						} catch (Exception e) {
							System.out.println("Error");
							e.printStackTrace();
						}
	                }
	            	
	                if (cookie.getName().equals("Role")) {
	                	System.out.println("Role");
	                	try {
							role = cookie.getValue();
						} catch (Exception e) {
							e.printStackTrace();
						}
	                }
	               
	            }
	        }
	        
	        if (!(userName.equals("")) && !(role.equals(""))) {
				return true;
			}
	        else {
	             return false;
	        }
	    }
	 
}
