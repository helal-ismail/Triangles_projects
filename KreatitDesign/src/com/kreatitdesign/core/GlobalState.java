package com.kreatitdesign.core;

public class GlobalState {
	
	
	private static GlobalState instance = new GlobalState();
	public static GlobalState getInstance(){
		
		return instance;
	}
	

	public User user = new User();
	
	public Sensor sensor = new Sensor();
	
	public Temperature temp = new Temperature();
	
	

}
