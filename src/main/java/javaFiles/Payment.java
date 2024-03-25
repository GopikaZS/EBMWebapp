package javaFiles;

import java.sql.Date;

public class Payment {
    private Date paymentDate;
    private PaymentStatus paymentStatus;

    // Enum for PaymentStatus
    
    public Payment( Date paymentDate, PaymentStatus paymentStatus) {
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }
    
    public Payment() {}

    public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = PaymentStatus.valueOf(paymentStatus.toUpperCase());
	}

	public enum PaymentStatus {
        PAID,
        UNPAID
    }


   

}
