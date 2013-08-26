package com.kreatitdesign.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class KreatitDesignApp extends Application{
	
	private static Context context;
	public SharedPreferences prefs;
	
	public User user;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public static Context getAppContext() {
		return context;
	}

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

}
