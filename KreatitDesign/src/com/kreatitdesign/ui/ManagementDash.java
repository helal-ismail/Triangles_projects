package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
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

				Intent i = new Intent(myContext, Op_Mode.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

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

	}

}
