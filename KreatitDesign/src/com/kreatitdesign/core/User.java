package com.kreatitdesign.core;

public class User {
	
	public String userName;
	public String password;
	public String name;
	public String devID;
	
	
	public User()
	{
		userName = "";
		password = "";
		name = "";
		devID = "";
	}
	
	
	public User(String userName, String password, String name, String devID) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.devID = devID;
	}
	
	
	

}
