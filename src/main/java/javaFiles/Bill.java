package javaFiles;

import java.sql.Date;

public class Bill {
	    private int billId ;
	    private double amount;
		private Date dueDate;
	    private Payment paymenobj = new Payment();

		public int getBillId() {
			return billId;
		}

		public void setBillId(int billId) {
			this.billId = billId;
		}


		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public Date getDueDate() {
			return dueDate;
		}

		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}

		public Payment getPaymenobj() {
			return paymenobj;
		}

		public void setPaymenobj(Payment paymenobj) {
			this.paymenobj = paymenobj;
		}

		
		public Bill() {}
		
	    public Bill( double amount, Date dueDate) {
	        this.amount = amount;
	        this.dueDate = dueDate;

	    }
}
