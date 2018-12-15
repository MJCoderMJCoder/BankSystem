package com.trim.code;

public class BankAccount {
	private int accNo;
	private String accName;
	private double balance;

	public BankAccount(int accNo, String accName) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}

	public BankAccount(String accName, int accNo) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}

	public int getAccNo() {
		return accNo;
	}

	public String getAccName() {
		return accName;
	}

	public double getBalance() {
		return balance;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public void deposit(double amount) {
		balance = balance + amount;
		System.out.println("Deposit " + amount + " successful");
	}

	public void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfull.");
		}
	}

	protected boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= 0) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount
					+ " unsuccessfull. Do not have enough available funds.");
		}
		return allowed;
	}

	public String toString() {
		return "Account number: " + accNo + "\n" + "Account name: " + accName
				+ "\n" + "Balance: " + balance + "\n";
	}

}
