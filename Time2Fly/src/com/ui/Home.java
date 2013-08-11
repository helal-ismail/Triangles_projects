package com.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.core.Constants;
import com.core.Time2FlyApp;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class Home extends Activity {
	Time2FlyApp appInstance;
	Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		appInstance = (Time2FlyApp) getApplication();
		appInstance.init();
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		Log.d(Constants.TAG, "Status : " + status);
	
	}
}
