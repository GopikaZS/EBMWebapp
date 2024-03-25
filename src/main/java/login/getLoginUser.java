package login;

import javax.servlet.http.Cookie;

public class getLoginUser {
	public static synchronized String getUserName(Cookie[] cookiesRequest) {
		  Cookie[] cookies = cookiesRequest;
	        
	        if (cookies != null) {
	        	for (Cookie cookie : cookies) {
                	 if (cookie.getName().equals("user")) {
		            		try {
		            			return cookie.getValue();
							} catch (Exception e) {
								System.out.println("Error");
						    }
	                }
	           }
	        	
	       }
	        
	        return "";
	  }
}
