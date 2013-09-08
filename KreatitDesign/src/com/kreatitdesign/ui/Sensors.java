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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.Sensor;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

public class Sensors extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;
	Sensor sensor;

	LinearLayout digital_list;
	LinearLayout analog_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensors);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Sensors");

		user = globalState.user;
		sensor = globalState.sensor;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		digital_list = (LinearLayout) findViewById(R.id.digital_list);
		analog_list = (LinearLayout) findViewById(R.id.analog_list);

		digital_list.setVisibility(View.VISIBLE);
		analog_list.setVisibility(View.GONE);

		// digital BUTTON
		Button digital_btn = (Button) findViewById(R.id.digital);
		digital_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				digital_list.setVisibility(View.VISIBLE);
				analog_list.setVisibility(View.GONE);

			}
		});

		// analog BUTTON
		Button analog_btn = (Button) findViewById(R.id.analog);
		analog_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				digital_list.setVisibility(View.GONE);
				analog_list.setVisibility(View.VISIBLE);

			}
		});

		// Sensor 1 BUTTON
		LinearLayout sensor1_btn = (LinearLayout) findViewById(R.id.sensor1);
		sensor1_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runSensor("Sensor 1", "ds1");
			}
		});

	}
	
	
	private void runSensor(String name, String tag)
	{
		try {

			SensorTask task = new SensorTask();
			task.taskNumber = 11;
			task.sensor_name = name;
			task.sensor_tag = tag;

			JSONArray arr = new JSONArray();
			arr.put(tag);
			task.arr = arr;

			task.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// =================================================================================

		private class SensorTask extends RequestTask {

			int taskNumber;
			JSONArray arr;
			
			String sensor_name;
			String sensor_tag;

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

					JSONObject sen = msg.optJSONObject(sensor_tag);
					sensor.sensorName = sensor_name;
					sensor.name = sen.optString("name");
					sensor.value = sen.optString("value");
					sensor.criticalValue = sen.optString("cvalue");
					sensor.enable = sen.optString("en");
					sensor.bindEnable = sen.optString("bind_en");
					sensor.lastTime = sen.optString("ltt");

					
					
					Intent i = new Intent(myContext, SensorDetails.class);
					startActivityForResult(i, 700);
					overridePendingTransition(R.anim.slide_in_right,
							R.anim.slide_out_left);
					
					
					progress.setVisibility(View.GONE);

					Log.d(Constants.TAG, "RESULT : " + result);

				} catch (Exception e) {
					// TODO: handle exception
					Log.d(Constants.TAG, "Exception : " + e.getMessage());
				}
			}
		}


}
