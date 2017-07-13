package com.nikoer;

public class Chinese implements Human {
	
	private String name;
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("中国人对吃很有一套"+this.name);
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("中国人行如飞");
	}

}
