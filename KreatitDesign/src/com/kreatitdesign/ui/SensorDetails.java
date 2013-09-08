package com.kreatitdesign.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.Sensor;
import com.kreatitdesign.core.User;

public class SensorDetails extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;
	Sensor sensor;

	EditText name;
	EditText value;
	EditText cvalue;
	EditText enable;
	EditText bind_enable;
	EditText time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_details);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Sensors");

		user = globalState.user;
		sensor = globalState.sensor;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(sensor.sensorName);

		name = (EditText) findViewById(R.id.name);
		name.setText(sensor.name);
		name.setEnabled(false);

		value = (EditText) findViewById(R.id.value);
		value.setText(sensor.value);
		value.setEnabled(false);

		cvalue = (EditText) findViewById(R.id.cvalue);
		cvalue.setText(sensor.criticalValue);
		cvalue.setEnabled(false);

		enable = (EditText) findViewById(R.id.enable);
		enable.setText(sensor.enable);
		enable.setEnabled(false);

		bind_enable = (EditText) findViewById(R.id.bind_enable);
		bind_enable.setText(sensor.bindEnable);
		bind_enable.setEnabled(false);

		time = (EditText) findViewById(R.id.time);
		time.setText(sensor.lastTime);
		time.setEnabled(false);

		// edite BUTTON
		Button edite_btn = (Button) findViewById(R.id.edit);
		edite_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				name.setEnabled(true);

				value.setEnabled(true);
				cvalue.setEnabled(true);
				enable.setEnabled(true);
				bind_enable.setEnabled(true);
				time.setEnabled(true);

			}
		});

		// Update BUTTON
		Button update_btn = (Button) findViewById(R.id.save);
		update_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				name.setEnabled(true);

				value.setEnabled(true);
				cvalue.setEnabled(true);
				enable.setEnabled(true);
				bind_enable.setEnabled(true);
				time.setEnabled(true);

			}
		});

	}
}
