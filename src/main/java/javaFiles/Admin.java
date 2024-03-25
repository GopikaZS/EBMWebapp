package javaFiles;

import java.util.ArrayList;
import java.util.List;


import newUser.newUserRequest;


public class Admin  {
    // Additional attributes specific to Admin
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private List<Customer> consumerList = new ArrayList<Customer>(); // List of consumers
    private List<newUserRequest> userRequestsList = new ArrayList<newUserRequest>();; // List of userRequests
    
    public Admin() {}
    
    
    
    // Constructor
    public Admin(String userID, String firstName,String lastname, String phoneNumber,String Address) {
        this.userId = userID;
        this.firstName = firstName;
        this.userId = lastname;
        this.phoneNumber = phoneNumber;
        this.address = Address;
    }

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

	public List<newUserRequest> getUserRequests() {
		return userRequestsList;
	}

	public void setUserRequests(newUserRequest userRequests) {
		userRequestsList.add(userRequests);
	}

	public List<Customer> getConsumerList() {
        return consumerList;
    }
    
    public void setConsumer(Customer consumer) {
        consumerList.add(consumer);
    }
    
   
    
    // Additional methods specific to Admin
    public void addNewUser(Customer newUser) {
        // Add new user
    }
    
    public void deleteUser(int userID) {
        // Delete user
    }
    
    
}
