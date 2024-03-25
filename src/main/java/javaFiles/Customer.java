package javaFiles;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.JsonAccessLogValve;
import org.json.JSONException;
import org.json.JSONObject;

import newUser.newUserRequest;


public class Customer {
	private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private List<MonthLeading> leadings = new ArrayList<MonthLeading>() ;
    private List<userComplaint> complaints = new ArrayList<userComplaint>();
    
    
    public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public void addLeadingValue(MonthLeading currectMonth) {
		this.leadings.add(currectMonth);
	}
	
	public List<MonthLeading> getLeadingValues() {
		return leadings;
	}

	
    public Customer(String userID, String firstName,String lastname, String phoneNumber,String Address) {
        this.userId = userID;
        this.firstName = firstName;
        this.lastName = lastname;
        this.phoneNumber = phoneNumber;
        this.address = Address;
    }
    
    public Customer() {}
    
    public JSONObject customerToJson() {
    	JSONObject object = new JSONObject();
    	try {
    		object.put("UserId", userId);
    		object.put("FirstName", firstName);
        	object.put("lastName", lastName);
        	object.put("PhoneNumber", phoneNumber);
        	object.put("Address", address);	
		} catch (JSONException e) {
				// add a log file
		}	
    	return object;	
	}
    
    /*
    
    public static void main(String[] args) throws JSONException {
		Customer testCustomer = new Customer("Krish", "123", "na", "132", "278");
		JSONObject gottenObject = testCustomer.customerToJson() ;
		System.out.println(gottenObject.get("UserName"));
	}
	
	*/
}
