package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

@SuppressLint("ShowToast")
public class Login extends Activity {

	Context myContext = this;

	String result = "";

	EditText userName;
	EditText pass;
	EditText name;
	EditText devID;
	CheckBox remember_me;

	ProgressBar prog_bar;
	LinearLayout progress;

	String _userName = "";
	String _pass = "";
	String _name = "";
	String _devID = "";
	
	
	public static final String PREFS_NAME = "MyPrefsFile";
	KreatitDesignApp bindAlertApp = new KreatitDesignApp();
	
	GlobalState globalState = GlobalState.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		bindAlertApp.prefs = getSharedPreferences(PREFS_NAME, 0);
		

		remember_me = (CheckBox) findViewById(R.id.remember_me);

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);
		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		userName = (EditText) findViewById(R.id.user_name);
		userName.setText("tcousins");

		pass = (EditText) findViewById(R.id.password);
		pass.setText("getin123");

		name = (EditText) findViewById(R.id.name);
		name.setText("Tyrone");

		devID = (EditText) findViewById(R.id.device_ID);
		devID.setText("demo0001");

		Button sign_in = (Button) findViewById(R.id.signin);
		sign_in.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {
					
					globalState.user = new User(_userName, _pass, _name, _devID);

					LoginTask task = new LoginTask();
					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	
	//================================================================================
	
	
	public class LoginTask extends RequestTask {

		JSONArray arr;
		JSONObject object;
		JSONObject msg;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progress.setVisibility(View.VISIBLE);

			_userName = userName.getText().toString();
			_pass = pass.getText().toString();
			_name = name.getText().toString();
			_devID = devID.getText().toString();

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
					installRequest.setupParams(_name, 16, _devID, _userName, _pass, arr);
					String install_result = installRequest.execute().get();
					
					object = new JSONObject(install_result);
					msg = object.optJSONObject("msg");
					int install_code = msg.optInt("code");
					String install_disp = msg.optString("disp");
					
					if (install_code == 13000
							&& install_disp.equalsIgnoreCase(" Installation Completed")) {
						
						if (remember_me.isChecked()){
							Editor editor = bindAlertApp.prefs.edit();
							editor.putString("userName", _userName);
							editor.putString("pass", _pass);
							editor.putString("name", _name);
							editor.putString("devID", _devID);
							editor.putBoolean("remember_me", true);
							editor.commit();
						}
						else
						{
							Editor editor = bindAlertApp.prefs.edit();
							editor.remove("userName");
							editor.remove("pass");
							editor.remove("name");
							editor.remove("devID");
							editor.putBoolean("remember_me", false);
							editor.commit();
						}
						
						
						finish();
						Intent i = new Intent(myContext, MainDash.class);
						startActivityForResult(i, 700);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);
						
					}

				}
				
				else if (code == 13001
						&& disp.equalsIgnoreCase(" Installation already completed. See admin")){
					
					
					if (remember_me.isChecked()){
						Editor editor = bindAlertApp.prefs.edit();
						editor.putString("userName", _userName);
						editor.putString("pass", _pass);
						editor.putString("name", _name);
						editor.putString("devID", _devID);
						editor.putBoolean("remember_me", true);
						editor.commit();
					}
					else
					{
						Editor editor = bindAlertApp.prefs.edit();
						editor.remove("userName");
						editor.remove("pass");
						editor.remove("name");
						editor.remove("devID");
						editor.putBoolean("remember_me", false);
						editor.commit();
					}
					
					
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
