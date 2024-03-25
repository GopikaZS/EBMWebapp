package userRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Admin.servelt.newUserRequestView;
import manageUser.userDAO;


@WebServlet("/billHistoryServelt")
public class billHistoryServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public billHistoryServelt() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject responseObject = new JSONObject();
		try {
			List<JSONObject> billHistroyJsonObjects = userDAO.getInstance().getAllBillHistory(request);
			responseObject.put("statusCode", 200);
			responseObject.put("billList", billHistroyJsonObjects);
			response.getWriter().write(responseObject.toString());
		} catch (Exception e) {
			
			try {
				responseObject.put("statusCode", 500);
				responseObject.put("reason", "Someting went wrong in a show a bill histroy please try again later");
				response.getWriter().write(responseObject.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
	}

}
