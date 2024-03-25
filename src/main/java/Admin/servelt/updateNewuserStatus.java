package Admin.servelt;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import newUser.newUsersDAO;

/*
 * 2. same for reject a application
*/

@WebServlet("/updateNewuserStatus")
public class updateNewuserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public updateNewuserStatus() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
		JSONObject responseObject  = new JSONObject();
			try {
				 JSONObject jsonObject = new JSONObject(requestBody);
				 String referenceId = jsonObject.getString("referenceId");
				 String reasonString = jsonObject.getString("reason");
				boolean update = newUsersDAO.getInstance().updateNewUserStatus(referenceId,reasonString);
				if(update) {
					responseObject.put("statusCode", 200);
					response.getWriter().write(responseObject.toString());
				}else {
					throw new Exception("Error");
				}
				
				
				
			} catch (Exception e) {
				System.out.println("Error in update a new user status");
				System.out.println(e.getMessage());
				
				try {
					responseObject.put("statusCode", 500);
					response.getWriter().write(responseObject.toString());
				} catch (Exception e2) {
					
				}
				
			}

	}
	
	

}
