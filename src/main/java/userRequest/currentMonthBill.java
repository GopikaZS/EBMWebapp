package userRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import javaFiles.MonthLeading;
import login.getLoginUser;
import manageUser.userDAO;
import newUser.newUserRequest;


@WebServlet("/currentMonthBill")
public class currentMonthBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public currentMonthBill() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println( "CurrentBill" );
		JSONObject responseObject = new JSONObject();
		
		try {
			MonthLeading monthLeading = userDAO.getInstance().getCurrentMonth(request);
			System.out.println(monthLeading + "leading");
			if (monthLeading != null) {
				responseObject.put("statusCode", 200);
				System.out.println(responseObject.toString());
				JSONObject billObj = new JSONObject();
				billObj.put("amount", monthLeading.getBillvalues().getAmount());
				billObj.put("dueDate",monthLeading.getBillvalues().getDueDate());
				billObj.put("paymentDate",monthLeading.getBillvalues().getPaymenobj().getPaymentDate());
				billObj.put("leadingValue",monthLeading.getReadingValue());
				billObj.put("paymentStatus", monthLeading.getBillvalues().getPaymenobj().getPaymentStatus());
				responseObject.put("Content", billObj);
				System.out.println(responseObject.toString());
				response.getWriter().write(responseObject.toString());
			}else {		
				responseObject.put("statusCode", 500);
				responseObject.put("Error", "No current month file");
				System.out.println(responseObject.toString());
				response.getWriter().write(responseObject.toString());
			}
			
		} catch (Exception e) {
			System.out.println("hi and bye");
			e.printStackTrace();
		}
	
	}

}
