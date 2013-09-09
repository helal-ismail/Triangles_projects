package com.kreatitdesign.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;

public class System extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	LinearLayout family_list;
	LinearLayout basic_list;
	LinearLayout email_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activty_system);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("System");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		family_list = (LinearLayout) findViewById(R.id.family_list);
		basic_list = (LinearLayout) findViewById(R.id.basic_list);
		email_list = (LinearLayout) findViewById(R.id.email_list);

		basic_list.setVisibility(View.VISIBLE);
		family_list.setVisibility(View.GONE);
		email_list.setVisibility(View.GONE);

		// Basic BUTTON
		Button digital_btn = (Button) findViewById(R.id.basic);
		digital_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				basic_list.setVisibility(View.VISIBLE);
				family_list.setVisibility(View.GONE);
				email_list.setVisibility(View.GONE);

			}
		});

		// Family BUTTON
		Button analog_btn = (Button) findViewById(R.id.family);
		analog_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				basic_list.setVisibility(View.GONE);
				family_list.setVisibility(View.VISIBLE);
				email_list.setVisibility(View.GONE);

			}
		});

		// Email BUTTON
		Button email_btn = (Button) findViewById(R.id.email);
		email_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				basic_list.setVisibility(View.GONE);
				family_list.setVisibility(View.GONE);
				email_list.setVisibility(View.VISIBLE);

			}
		});

	}
}
