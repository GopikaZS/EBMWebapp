package commonFile;



public class constantQuerryUsers {
	
	static constantQuerryUsers constFileUsers = null;
	//static Logger logger = new CommonLogger(constantFiles.class).getLogger();
	private constantQuerryUsers() {}

	public static synchronized constantQuerryUsers getInstance() {
	        if (constFileUsers == null) {
	        	constFileUsers = new constantQuerryUsers();
	        }
	        return constFileUsers;
	    }

	private String getLongestPersonID = "SELECT MAX(PersonID) AS HighestPersonID FROM PersonDetails;";
	
	private String insertnewUserquerry = "INSERT INTO PersonDetails (FirstName, LastName, PhoneNumber) VALUES (?, ?, ?)";
	
	private String selectUserNewRequest = "SELECT * FROM UserRequest WHERE RequestID = ?";
	
	private  String insertUserQuerry = "INSERT INTO User (UserName, Password, PersonID) VALUES (?, ?, ?)";
	
	private String insertIntoAddress = "INSERT INTO Address (Street, District, UserID, State) VALUES (?, ?, ?, ?)";


	public String getGetLongestPersonID() {
		return getLongestPersonID;
	}

	public String getInsertnewUserquerry() {
		return insertnewUserquerry;
	}

	public String getSelectUserNewRequest() {
		return selectUserNewRequest;
	}

	public String getInsertUserQuerry() {
		return insertUserQuerry;
	}

	public String getInsertIntoAddress() {
		return insertIntoAddress;
	}
		
}
