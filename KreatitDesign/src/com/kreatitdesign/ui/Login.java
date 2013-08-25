package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.network.RequestTask;

public class Login extends Activity {

	Context myContext = this;

	String result = "";

	EditText userName;
	EditText pass;
	EditText name;
	EditText devID;

	ProgressBar prog_bar;
	LinearLayout progress;

	String _userName = "";
	String _pass = "";
	String _name = "";
	String _devID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

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

					LoginTask task = new LoginTask();
					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	private class LoginTask extends RequestTask {

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
						
						
						Intent i = new Intent(myContext, MainDash.class);
						startActivityForResult(i, 700);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);
						
					}

				}
				
				else if (code == 13001
						&& disp.equalsIgnoreCase(" Installation already completed. See admin")){
					
					Intent i = new Intent(myContext, MainDash.class);
					startActivityForResult(i, 700);
					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_left);
					
				}

				else {
					Toast.makeText(myContext, "Username or Password Incorrect",
							3000).show();
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
