package javaFiles;

import java.sql.Date;



public class MonthLeading {
    private Date month;
	private double readingValue;
	String monthName;
    Bill billvalues = new Bill();
	
    public MonthLeading() {}
    public MonthLeading(double leadingValue,double amount, Date dueDate, Date paymentDate,String paymentStatus) {
    	this.readingValue = leadingValue;
    	billvalues.setAmount(amount);
    	billvalues.setDueDate(dueDate);
    	billvalues.getPaymenobj().setPaymentDate(paymentDate);
    	billvalues.getPaymenobj().setPaymentStatus(paymentStatus);
    }
    
    public MonthLeading(Date month, double readingValue, Bill billvalues) {
		this.month = month;
		this.readingValue = readingValue;
		this.billvalues = billvalues;
	}

	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public double getReadingValue() {
		return readingValue;
	}
	public void setReadingValue(double readingValue) {
		this.readingValue = readingValue;
	}
	
	 public Bill getBillvalues() {
			return billvalues;
	}

	 public void setBillvalues(Bill billvalues) {
			this.billvalues = billvalues;
	 }
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
}	
