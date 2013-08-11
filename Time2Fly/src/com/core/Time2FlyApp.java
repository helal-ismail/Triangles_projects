package com.core;

import com.modules.ResponseBlock;
import com.network.GetDataTask;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class Time2FlyApp extends Application {
	private static Context context;
	protected SharedPreferences prefs;

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

	// ===== Settings Functions =====
	public void updateResponseVals(String unix_ts, int update_rate,
			String version) {
		Editor editor = prefs.edit();
		editor.putString(Constants.UNIX_TS, unix_ts);
		editor.putInt(Constants.UPDATE_RATE, update_rate);
		editor.putString(Constants.VERSION, version);
		editor.commit();
	}

	public ResponseBlock getResponseVals() {
		String ts = prefs.getString(Constants.UNIX_TS, "");
		int rate = prefs.getInt(Constants.UPDATE_RATE, 10);
		String ver = prefs.getString(Constants.VERSION, "");
		ResponseBlock r = new ResponseBlock(ts, rate, ver);
		return r;

	}

	// ===== Core Functions =====
	
	public void init() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						GetDataTask task = new GetDataTask();
						task.execute();
						int update_rate = getResponseVals().update_rate;
						synchronized (this) {
							wait(1000 * update_rate);
						}
					} catch (Exception e) {
						Log.d(Constants.TAG, "Exception " + e.getMessage());
						return;
					}
				}
			};
		};
		t.start();
	}

}
