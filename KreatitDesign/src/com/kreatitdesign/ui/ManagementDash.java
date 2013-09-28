package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

public class ManagementDash extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_managementdash);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Management Dashboard");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		// OP_MODE BUTTON
		LinearLayout status_btn = (LinearLayout) findViewById(R.id.op_mode_btn);
		status_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				try {

					ManageDashTask task = new ManageDashTask();
					task.taskNumber = 4;
					task.title = "Operational Mode";
					task.mode = "operational";

					JSONArray arr = new JSONArray();
					arr.put("op_mode");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			}
		});

		// Monitoring Center BUTTON
		LinearLayout monitoring_btn = (LinearLayout) findViewById(R.id.monitoring_btn);
		monitoring_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, MonitoringCentre.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

		// Access Code BUTTON
		LinearLayout access_btn = (LinearLayout) findViewById(R.id.access_btn);
		access_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, AccessCode.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

		// Wireless BUTTON
		LinearLayout wireless_btn = (LinearLayout) findViewById(R.id.wireless_btn);
		wireless_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, Wireless.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

		// Sensors BUTTON
		LinearLayout sensors_btn = (LinearLayout) findViewById(R.id.sensors_btn);
		sensors_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, Sensors.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

		// Alarm BUTTON
		LinearLayout alarm_btn = (LinearLayout) findViewById(R.id.alarm_btn);
		alarm_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, Alarm.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

		// System BUTTON
		LinearLayout system_btn = (LinearLayout) findViewById(R.id.system_btn);
		system_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, System.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});

	}

	
	
	// =================================================================================

		private class ManageDashTask extends RequestTask {

			int taskNumber;
			String title;
			JSONArray arr;
			String mode;

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();

				progress.setVisibility(View.VISIBLE);

				this.setupParams(user.name, taskNumber, user.devID, user.userName,
						user.password, arr);
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				try {

					JSONObject object = new JSONObject(result);
					JSONObject msg = object.optJSONObject("msg");
					
					String disp = "";
					
					
					if (!msg.optString("op_mode").equals(""))
						disp = msg.optString("op_mode");
					else if (!msg.optString("disp").equals(""))
						disp = msg.optString("disp");
					
					
					
					
					Intent i = new Intent(myContext, Result.class);
					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_left);
					
					i.putExtra("title", title);
					i.putExtra("result", disp);
					i.putExtra("mode", mode);
					
					startActivityForResult(i, 700);
					


					progress.setVisibility(View.GONE);

					Log.d(Constants.TAG, "RESULT : " + result);

				} catch (Exception e) {
					// TODO: handle exception
					Log.d(Constants.TAG, "Exception : " + e.getMessage());
				}
			}
		}
		
}
