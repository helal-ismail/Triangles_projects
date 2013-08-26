package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

public class Splash extends Activity {

	Context myContext = this;

	public static final String PREFS_NAME = "MyPrefsFile";
	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();

	ProgressBar prog_bar;
	LinearLayout progress;

	String _userName = "";
	String _pass = "";
	String _name = "";
	String _devID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);
		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		bindAlertApp.prefs = getSharedPreferences(PREFS_NAME, 0);

		if (bindAlertApp.prefs.getBoolean("remember_me", false)) {
			_userName = bindAlertApp.prefs.getString("userName", "");
			_pass = bindAlertApp.prefs.getString("pass", "");
			_name = bindAlertApp.prefs.getString("name", "");
			_devID = bindAlertApp.prefs.getString("devID", "");

			globalState.user = new User(_userName, _pass, _name, _devID);

			LoginTask task = new LoginTask();
			task.execute();
		}

		else {

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

	// ================================================================================

	@SuppressLint("ShowToast")
	private class LoginTask extends RequestTask {

		JSONArray arr;
		JSONObject object;
		JSONObject msg;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progress.setVisibility(View.VISIBLE);

			arr = new JSONArray();
			arr.put("request");

			this.setupParams(_name, 16, _devID, _userName, _pass, arr);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				object = new JSONObject(result);
				msg = object.optJSONObject("msg");
				int code = msg.optInt("code");
				String disp = msg.optString("disp");

				if (code == 13000
						&& disp.equalsIgnoreCase(" Successfully Login")) {

					arr = new JSONArray();
					arr.put("complete");

					RequestTask installRequest = new RequestTask();
					installRequest.setupParams(_name, 16, _devID, _userName,
							_pass, arr);
					String install_result = installRequest.execute().get();

					object = new JSONObject(install_result);
					msg = object.optJSONObject("msg");
					int install_code = msg.optInt("code");
					String install_disp = msg.optString("disp");

					if (install_code == 13000
							&& install_disp
									.equalsIgnoreCase(" Installation Completed")) {

						finish();
						Intent i = new Intent(myContext, MainDash.class);
						startActivityForResult(i, 700);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);

					}

				}

				else if (code == 13001
						&& disp.equalsIgnoreCase(" Installation already completed. See admin")) {

					finish();
					Intent i = new Intent(myContext, MainDash.class);
					startActivityForResult(i, 700);
					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_left);

				}

				else {
					Toast.makeText(myContext, disp, 3000).show();
				}

				progress.setVisibility(View.GONE);

				Log.d(Constants.TAG, "RESULT : " + result);

			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Constants.TAG, "Exception : " + e.getMessage());
			}
		}
	}

}
