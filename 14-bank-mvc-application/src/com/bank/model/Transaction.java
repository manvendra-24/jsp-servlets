package com.bank.model;

import java.sql.Date;

public class Transaction {
	
	private int id;
    private String senderAccNo;
    private String receiverAccNo;
    private String type;
    private double amount;
    private Date date;
    private double Sbalance;
    private double Rbalance;
    
    
    
	

	public double getSbalance() {
		return Sbalance;
	}

	public void setSbalance(double sbalance) {
		Sbalance = sbalance;
	}

	public double getRbalance() {
		return Rbalance;
	}

	public void setRbalance(double rbalance) {
		Rbalance = rbalance;
	}


	public Transaction(int id, String senderAccNo, String receiverAccNo, String type, double amount, Date date) {
		super();
		this.id = id;
		this.senderAccNo = senderAccNo;
		this.receiverAccNo = receiverAccNo;
		this.type = type;
		this.amount = amount;
		this.date = date;
	}

	public Transaction(String senderAccNo, String receiverAccNo, String type, double amount) {
		super();
		this.senderAccNo = senderAccNo;
		this.receiverAccNo = receiverAccNo;
		this.type = type;
		this.amount = amount;
	}
	
	public Transaction(String senderAccNo, String receiverAccNo, double amount) {
		super();
		this.senderAccNo = senderAccNo;
		this.receiverAccNo = receiverAccNo;
		this.amount = amount;
	}
	public Transaction() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(String senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public String getReceiverAccNo() {
		return receiverAccNo;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderAccNo=" + senderAccNo + ", receiverAccNo=" + receiverAccNo + ", type="
				+ type + ", amount=" + amount + ", date=" + date + "]";
	}
	public void setReceiverAccNo(String receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    

}
