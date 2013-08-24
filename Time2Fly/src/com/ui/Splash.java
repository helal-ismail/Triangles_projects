package com.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity{
	Context mComtext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		getActionBar().hide();
		
		Handler handler = new Handler();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Intent homeIntent = new Intent(mComtext, Home.class);
				startActivity(homeIntent);
				finish();
			}
		};
		handler.postDelayed(r, 1500);
	}

}
