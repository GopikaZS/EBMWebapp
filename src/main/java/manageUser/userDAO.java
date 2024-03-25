package manageUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import Admin.servelt.newUserRequestView;
import commonFile.singleton;
import javaFiles.Bill;
import javaFiles.Customer;
import javaFiles.MonthLeading;
import login.getLoginUser;


public class userDAO {
	

	private userDAO() {}
	static userDAO userDAOObj = null;
	
	public static synchronized userDAO getInstance() {
        if (userDAOObj == null) {
        	userDAOObj = new userDAO();
        }
        return userDAOObj;
    }
	
	

	// current Month Reading
	
	public MonthLeading getCurrentMonth(HttpServletRequest request) throws Exception{
		String sqlString = "SELECT r.ReadingValue,b.Amount, b.DueDate, p.PaymentDate,p.PaymentStatus FROM User u JOIN MonthReading r ON u.UserID = r.UserID JOIN Bill b ON r.ReadingID = b.ReadingID JOIN Payment p ON b.PaymentID = p.PaymentID WHERE u.UserName like ? AND YEAR(r.ReadingMonth) = YEAR(CURRENT_DATE())  AND MONTH(r.ReadingMonth) = MONTH(CURRENT_DATE());";
        Cookie[] cookies = request.getCookies();
		String userName = getLoginUser.getUserName(cookies);
		
		MonthLeading monthObjLeading = new MonthLeading();
		if(!userName.equals("")) {
				try {
					Connection connection = singleton.getInstance().getConnection();
		    	 	PreparedStatement stmt = connection.prepareStatement(sqlString);
		    	 	stmt.setString(1, userName);
		    	 	ResultSet results =  stmt.executeQuery();
		    	 	if (results.next()) {
		    	 		
		    	 		Double leadingValue = results.getDouble("r.ReadingValue");
						Double amount = results.getDouble("b.Amount");
						Date dueDate = results.getDate("b.DueDate");
						Date paymentDate = results.getDate("p.PaymentDate");
						String paymentStatus = results.getString("p.PaymentStatus");
						monthObjLeading = new MonthLeading(leadingValue,amount,dueDate,paymentDate,paymentStatus);
						System.out.println("hi an");
						return monthObjLeading;
					}else {
						System.out.println("not update");
						// current bill is not update
					}
		    	 	
		    	 	
				} catch (Exception e) {
					throw new Exception("Error in current month bill");
				}
		
		}
		return null;	
			
	}
	
	
	
	public List<JSONObject> getAllBillHistory(HttpServletRequest request) throws JSONException {
		List<JSONObject> userAllBillHisJsonObjects = new ArrayList<JSONObject>();
		Cookie[] cookies = request.getCookies();
		String userName = getLoginUser.getUserName(cookies);
		List<MonthLeading> userMonthLeading = getBillHistroy(userName);
		
		for (MonthLeading monthLeading : userMonthLeading) {
			JSONObject billObjJsonObject = new JSONObject();
			billObjJsonObject.put("amount", monthLeading.getBillvalues().getAmount());
			billObjJsonObject.put("dueDate",monthLeading.getBillvalues().getDueDate());
			billObjJsonObject.put("paymentDate",monthLeading.getBillvalues().getPaymenobj().getPaymentDate());
			billObjJsonObject.put("leadingValue",monthLeading.getReadingValue());
			billObjJsonObject.put("paymentStatus", monthLeading.getBillvalues().getPaymenobj().getPaymentStatus());
			billObjJsonObject.put("MonthName",monthLeading.getMonthName());
			userAllBillHisJsonObjects.add(billObjJsonObject);
		}
		
		return userAllBillHisJsonObjects;
	}
	
	
//	bill History
	public List<MonthLeading> getBillHistroy(String userName) {
		String sqlString = "select r.ReadingValue, b.Amount, b.DueDate, p.PaymentDate, p.PaymentStatus,CONCAT(MONTHNAME(b.DueDate), '-', YEAR(b.DueDate))  AS DueMonth FROM User u JOIN MonthReading r ON u.UserID = r.UserID JOIN Bill b ON r.ReadingID = b.ReadingID JOIN Payment p ON b.PaymentID = p.PaymentID WHERE u.UserName = ? ORDER BY b.DueDate DESC LIMIT 12";
			
			List<MonthLeading> userMonthList = new ArrayList<MonthLeading>();
		try {
				Connection connection = singleton.getInstance().getConnection();
	    	 	PreparedStatement stmt = connection.prepareStatement(sqlString);
	    	 	stmt.setString(1, userName);
	    	 	ResultSet results =  stmt.executeQuery();
	    	 	while(results.next()) {
	    	 	     double leadingValue = results.getDouble("r.ReadingValue");
	    	 	     double amount = results.getDouble("b.Amount");
	    	 	     Date dueDate = results.getDate("b.DueDate");
	    	 	     Date paymentDate = results.getDate("p.PaymentDate");
					 String paymentStatus = results.getString("p.PaymentStatus"); 
					 String monthName = results.getString("DueMonth");
					 MonthLeading monthObjLeading = new MonthLeading(leadingValue,amount,dueDate,paymentDate,paymentStatus);
					 monthObjLeading.setMonthName(monthName);
					 userMonthList.add(monthObjLeading);
	    	 	}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userMonthList;
      
	}
	
	public void getChartValueJson() {
		
	}
	
	
	public List<MonthLeading> getChartView(String username) {
		String userHistroyChart = "select r.ReadingValue, b.Amount,MONTHNAME(b.DueDate)  AS DueMonth FROM User u JOIN MonthReading r ON u.UserID = r.UserID JOIN Bill b ON r.ReadingID = b.ReadingID JOIN Payment p ON b.PaymentID = p.PaymentID WHERE u.UserName = ? ORDER BY b.DueDate DESC LIMIT 12;";
		
		List<MonthLeading> userChartDetails = new ArrayList<MonthLeading>();
		
		try {
			Connection connection = singleton.getInstance().getConnection();
    	 	PreparedStatement stmt = connection.prepareStatement(userHistroyChart);
    	 	stmt.setString(1, username);
    	 	ResultSet results =  stmt.executeQuery();
    	 	while(results.next()) {
    	 		double leadingValue = results.getDouble("r.ReadingValue");
    	 		double amount =  results.getDouble("b.Amount");
    	 		String monthName = results.getString("DueMonth");
    	 		 MonthLeading monthObjLeading = new MonthLeading();
    	 		monthObjLeading.setMonthName(monthName);
    	 		monthObjLeading.setReadingValue(leadingValue);
    	 		Bill billObj = new Bill();
    	 		billObj.setAmount(amount);
    	 		monthObjLeading.setBillvalues(billObj);
    	 		userChartDetails.add(monthObjLeading);
    	 		}
    	 	
		}catch (Exception e) {
			
		}
			return userChartDetails;
		}
	
	}
	

