  package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kreatitdesign.network.RequestTask;

public class Splash extends Activity {

	
	Context myContext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		Runnable r = new Runnable() {
			public void run() {
				finish();
				
				Intent i = new Intent(myContext, Login.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			}
		};

		Handler handler = new Handler();
		handler.postDelayed(r, 2000);
		
		
		
	}

	

}
