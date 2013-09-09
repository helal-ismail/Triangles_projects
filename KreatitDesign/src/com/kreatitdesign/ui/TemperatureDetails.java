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
import com.kreatitdesign.core.Temperature;
import com.kreatitdesign.core.User;

public class TemperatureDetails extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;
	Temperature temp;

	EditText name;
	EditText value;
	EditText cvalue;
	EditText on_time;
	EditText off_time;
	EditText schedule;
	EditText enable;
	EditText bind_enable;
	EditText time;
	EditText connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp_details);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Sensors");

		user = globalState.user;
		temp = globalState.temp;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(temp.tempName);

		name = (EditText) findViewById(R.id.name);
		temp.name = temp.name.replace("#", "");
		name.setText(temp.name);
		name.setEnabled(false);

		value = (EditText) findViewById(R.id.value);
		value.setText(temp.value);
		value.setEnabled(false);

		cvalue = (EditText) findViewById(R.id.cvalue);
		cvalue.setText(temp.criticalValue);
		cvalue.setEnabled(false);
		
		on_time = (EditText) findViewById(R.id.on_time);
		on_time.setText(temp.onTime);
		on_time.setEnabled(false);
		
		
		off_time = (EditText) findViewById(R.id.off_time);
		off_time.setText(temp.offTime);
		off_time.setEnabled(false);
		
		
		schedule = (EditText) findViewById(R.id.schedule);
		schedule.setText(temp.schedule);
		schedule.setEnabled(false);
		

		enable = (EditText) findViewById(R.id.enable);
		enable.setText(temp.enable);
		enable.setEnabled(false);

		bind_enable = (EditText) findViewById(R.id.bind_enable);
		bind_enable.setText(temp.bindEnable);
		bind_enable.setEnabled(false);

		time = (EditText) findViewById(R.id.time);
		time.setText(temp.lastTime);
		time.setEnabled(false);
		
		
		connect = (EditText) findViewById(R.id.connect);
		
		if (temp.connect.equals(""))
			connect.setText("-----");
		else
			connect.setText(temp.connect);
		
		connect.setEnabled(false);

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
