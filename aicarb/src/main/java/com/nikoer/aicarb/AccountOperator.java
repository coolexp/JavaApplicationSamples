package com.nikoer.aicarb;

public class AccountOperator implements Runnable {
	private Account account;
	public AccountOperator(Account account) {
		this.account = account;
	}
	public void run() {
		// TODO Auto-generated method stub
		 synchronized (account) {
	         account.deposit(500);
	         account.withdraw(500);
	         System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
	         System.out.println(Thread.currentThread().getId());
	      }
	}

}
