package commonFile;

public class constantQuerryAdmin {
	
	static constantQuerryAdmin constFile = null;
	//static Logger logger = new CommonLogger(constantFiles.class).getLogger();
	private constantQuerryAdmin() {}

	public static synchronized constantQuerryAdmin getInstance() {
	        if (constFile == null) {
	        	constFile = new constantQuerryAdmin();
	        }
	        return constFile;
	    }
	

	private String isValidUsersCheckQuerry = "select UserName,Password,Role from User where UserName like ?;";
	
	private String getAllNewUserPendingRequest = "SELECT ur.RequestID,ur.RequestDate, ur.Status, ur.FirstName,ur.LastName,ur.PhoneNumber, concat(ur.Street, ', ', ur.District, ', ', ur.State) as FullAddress FROM UserRequest as ur where ur.status = 'Pending';";

	public String getIsValidUsersCheckQuerry() {
		return isValidUsersCheckQuerry;
	}

	public String getGetAllNewUserPendingRequest() {
		return getAllNewUserPendingRequest;
	}

	
	
}
