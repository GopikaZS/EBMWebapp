package newUser;

import org.json.JSONException;
import org.json.JSONObject;

public class newUserRequest {
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String requestID;
    private String requestDate;
    private String Address;
    private String requestStatus;

    // Constructor
    public newUserRequest(String firstName, String secondName, String phoneNumber, String Address, String requestID, String requestDate,String requestStatus) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.Address = Address;
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
    }

    

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    
    public String getRequestID() {
		return requestID;
	}



	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}



	public String getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
    
    public boolean addressExists() {
        return true; 
    }
    
    
    public JSONObject newUserToJson() {
    	JSONObject object = new JSONObject();
    	try {
    		object.put("Reference", getRequestID());
    		object.put("ReferenceDate", getRequestDate());
    		object.put("ReferenceStatus", getRequestStatus());
    		object.put("FirstName", getFirstName());
        	object.put("lastName", getSecondName());
        	object.put("PhoneNumber", getPhoneNumber());
        	object.put("Address", getAddress());	
        	
		} catch (JSONException e) {
			
		}
    	return object;
		
	}



   
}

