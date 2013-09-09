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
import com.kreatitdesign.core.Temperature;
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
	Temperature temp;

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
		temp = globalState.temp;

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

				runDigital("Sensor 1", "ds1");
			}
		});

		// Sensor 2 BUTTON
		LinearLayout sensor2_btn = (LinearLayout) findViewById(R.id.sensor2);
		sensor2_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 2", "ds2");
			}
		});

		// Sensor 3 BUTTON
		LinearLayout sensor3_btn = (LinearLayout) findViewById(R.id.sensor3);
		sensor3_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 3", "ds3");
			}
		});

		// Sensor 4 BUTTON
		LinearLayout sensor4_btn = (LinearLayout) findViewById(R.id.sensor4);
		sensor4_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 4", "ds4");
			}
		});

		// Sensor 5 BUTTON
		LinearLayout sensor5_btn = (LinearLayout) findViewById(R.id.sensor5);
		sensor5_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 5", "ds5");
			}
		});

		// Sensor 1 BUTTON
		LinearLayout sensor6_btn = (LinearLayout) findViewById(R.id.sensor6);
		sensor6_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 6", "ds6");
			}
		});

		// Sensor 7 BUTTON
		LinearLayout sensor7_btn = (LinearLayout) findViewById(R.id.sensor7);
		sensor7_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 7", "ds7");
			}
		});

		// Sensor 8 BUTTON
		LinearLayout sensor8_btn = (LinearLayout) findViewById(R.id.sensor8);
		sensor8_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 8", "ds8");
			}
		});

		// Sensor 9 BUTTON
		LinearLayout sensor9_btn = (LinearLayout) findViewById(R.id.sensor9);
		sensor9_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 9", "ds9");
			}
		});

		// Sensor 10 BUTTON
		LinearLayout sensor10_btn = (LinearLayout) findViewById(R.id.sensor10);
		sensor10_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 10", "ds10");
			}
		});

		// Sensor 11 BUTTON
		LinearLayout sensor11_btn = (LinearLayout) findViewById(R.id.sensor11);
		sensor11_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 11", "ds11");
			}
		});

		// Sensor 12 BUTTON
		LinearLayout sensor12_btn = (LinearLayout) findViewById(R.id.sensor12);
		sensor12_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runDigital("Sensor 12", "ds12");
			}
		});

		// ################################################################################

		// temp 1 BUTTON
		LinearLayout temp1_btn = (LinearLayout) findViewById(R.id.temp1);
		temp1_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Temperature 1", "t1");
			}
		});

		// temp 2 BUTTON
		LinearLayout temp2_btn = (LinearLayout) findViewById(R.id.temp2);
		temp2_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Temperature 2", "t2");
			}
		});

		// temp 3 BUTTON
		LinearLayout temp3_btn = (LinearLayout) findViewById(R.id.temp3);
		temp3_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Temperature 3", "t3");
			}
		});

		// ss 1 BUTTON
		LinearLayout ss1_btn = (LinearLayout) findViewById(R.id.ss1);
		ss1_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Special Sensor 1", "ss1");
			}
		});

		// ss 2 BUTTON
		LinearLayout ss2_btn = (LinearLayout) findViewById(R.id.ss2);
		ss2_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Special Sensor 2", "ss2");
			}
		});

		// ss 3 BUTTON
		LinearLayout ss3_btn = (LinearLayout) findViewById(R.id.ss3);
		ss3_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Special Sensor 3", "ss3");
			}
		});

		// ss 4 BUTTON
		LinearLayout ss4_btn = (LinearLayout) findViewById(R.id.ss4);
		ss4_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Special Sensor 4", "ss4");
			}
		});

		// ss 5 BUTTON
		LinearLayout ss5_btn = (LinearLayout) findViewById(R.id.ss5);
		ss5_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				runAnalog("Special Sensor 5", "ss5");
			}
		});

	}

	private void runDigital(String name, String tag) {
		try {

			DigitalTask task = new DigitalTask();
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

	private void runAnalog(String name, String tag) {
		try {

			AnalogTask task = new AnalogTask();
			task.taskNumber = 12;
			task.temp_name = name;
			task.temp_tag = tag;

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

	private class DigitalTask extends RequestTask {

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

				sensor.sensorName = "";
				sensor.name = "";
				sensor.value = "";
				sensor.criticalValue = "";
				sensor.enable = "";
				sensor.bindEnable = "";
				sensor.lastTime = "";

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

	private class AnalogTask extends RequestTask {

		int taskNumber;
		JSONArray arr;

		String temp_name;
		String temp_tag;

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

				temp.tempName = "";
				temp.name = "";
				temp.value = "";
				temp.criticalValue = "";
				temp.onTime = "";
				temp.offTime = "";
				temp.schedule = "";
				temp.enable = "";
				temp.bindEnable = "";
				temp.lastTime = "";
				temp.connect = "";

				JSONObject object = new JSONObject(result);
				JSONObject msg = object.optJSONObject("msg");

				JSONObject tem = msg.optJSONObject(temp_tag);
				temp.tempName = temp_name;
				temp.name = tem.optString("name");
				temp.value = tem.optString("value");
				temp.criticalValue = tem.optString("cvalue");
				temp.onTime = tem.optString("on_time");
				temp.offTime = tem.optString("off_time");
				temp.schedule = tem.optString("sch");
				temp.enable = tem.optString("en");
				temp.bindEnable = tem.optString("bind_en");
				temp.lastTime = tem.optString("ltt");
				temp.connect = tem.optString("connect");

				Intent i = new Intent(myContext, TemperatureDetails.class);
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
