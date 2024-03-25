package Admin.servelt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import manageAdmin.AdminDAO;





@WebServlet("/viewCustomers")
public class viewCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public viewCustomers() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject responseObject =  new JSONObject();
		try {
			List<JSONObject> jsonCustomers = AdminDAO.getInstance().getAllCustomersJSON();
			responseObject.put("statusCode", 200);
			responseObject.put("customerLists", jsonCustomers);
			response.getWriter().write(responseObject.toString());
			System.out.println("Success");
		}catch (Exception e) {
			try {
				responseObject.put("statusCode", 500);
				responseObject.put("content", "something went wrong");
				response.getWriter().write(responseObject.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		System.out.println("Super");
	}
	
	
	

}
