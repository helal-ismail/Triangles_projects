package com.navyscan.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	Context mContext = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler handler = new Handler();
		handler.postDelayed(r, 1500);
	}
	Runnable r = new Runnable() {
		
		@Override
		public void run() {
			Intent home = new Intent(mContext, Home.class);
			startActivity(home);
			finish();
		}
	};

}
