package com.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.bugsense.trace.BugSenseHandler;
import com.core.Time2FlyApp;

public class Splash extends Activity{
	Context mContext = this;
	Time2FlyApp appInstance;
	LinearLayout parent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        BugSenseHandler.initAndStartSession(mContext, "c417ebfa");

		setContentView(R.layout.activity_splash);
		appInstance = (Time2FlyApp)getApplication();
		parent = (LinearLayout)findViewById(R.id.parent);
		
		switch (getWindowManager().getDefaultDisplay().getOrientation()) {
		case Configuration.ORIENTATION_PORTRAIT:
			parent.setOrientation(LinearLayout.VERTICAL);
			break;
			
		case Configuration.ORIENTATION_LANDSCAPE:
			parent.setOrientation(LinearLayout.HORIZONTAL);
			break;
		default:
			break;
		}
			
		Handler handler = new Handler();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Intent homeIntent;
				if(appInstance.isFirstTime())
					homeIntent = new Intent(mContext, Settings.class);
				else
					homeIntent = new Intent(mContext, Home.class);
				startActivity(homeIntent);
				finish();
			}
		};
		handler.postDelayed(r, 2000);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		switch (newConfig.orientation) {
		case Configuration.ORIENTATION_PORTRAIT:
			parent.setOrientation(LinearLayout.VERTICAL);
			break;
			
		case Configuration.ORIENTATION_LANDSCAPE:
			parent.setOrientation(LinearLayout.HORIZONTAL);
			break;
		default:
			break;
		} 
	}

}
