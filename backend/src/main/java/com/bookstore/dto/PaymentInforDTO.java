package com.bookstore.dto;

public class PaymentInforDTO {

	private String status;
	private String message;
	private String orderInfo;
	private String payDate;
	private String amount;
	private String bankTranNo;
	private String transactionNo;

	
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBankTranNo() {
		return bankTranNo;
	}
	public void setBankTranNo(String bankTranNo) {
		this.bankTranNo = bankTranNo;
	}


}
