package com.trim.code;

import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> bank = new ArrayList<BankAccount>();

	public void openAccount(BankAccount b) {
		bank.add(b);
	}

	public void closeAccount(BankAccount b) {
		bank.remove(b);
	}

	public void update() {
		for (BankAccount b : bank) {
			if (b.getBalance() < 0) {
				System.out.println(b.getAccName()
						+ " is in overdraft, a letter is sent");
			}
		}
	}

}
