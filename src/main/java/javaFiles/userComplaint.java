package javaFiles;

import java.util.Date;

public class userComplaint {
    private int complaintID;
    private int userID;
    private String complaintDescription;
    private Date complaintDate;
    private ComplaintStatus complaintStatus;

    public userComplaint(int complaintID, int userID, String complaintDescription, Date complaintDate, ComplaintStatus complaintStatus) {
        this.complaintID = complaintID;
        this.userID = userID;
        this.complaintDescription = complaintDescription;
        this.complaintDate = complaintDate;
        this.complaintStatus = complaintStatus;
    }

    // Getters and setters
    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }
}

enum ComplaintStatus {
    PENDING,
    IN_PROGRESS,
    RESOLVED
}

