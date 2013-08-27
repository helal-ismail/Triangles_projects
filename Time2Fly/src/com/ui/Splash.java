package com.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bugsense.trace.BugSenseHandler;
import com.core.Time2FlyApp;

public class Splash extends Activity{
	Context mComtext = this;
	Time2FlyApp appInstance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        BugSenseHandler.initAndStartSession(mComtext, "c417ebfa");

		setContentView(R.layout.activity_splash);
		appInstance = (Time2FlyApp)getApplication();
		
		Handler handler = new Handler();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Intent homeIntent;
				if(appInstance.isFirstTime())
					homeIntent = new Intent(mComtext, Settings.class);
				else
					homeIntent = new Intent(mComtext, Home.class);
				startActivity(homeIntent);
				finish();
			}
		};
		handler.postDelayed(r, 1500);
	}

}
