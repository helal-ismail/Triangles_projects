package com.kreatitdesign.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainDash extends Activity {

	Context myContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maindash);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Main Dashboard");

		// PANIC BUTTON
		LinearLayout panic_btn = (LinearLayout) findViewById(R.id.panic_btn);
		panic_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(myContext)
						.setTitle("Panic Alert")
						.setIcon(R.drawable.icon_1)
						.setMessage("Panic Alert from Tyrone ")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();

			}
		});

		// NS PANIC BUTTON
		LinearLayout ns_panic_btn = (LinearLayout) findViewById(R.id.ns_panic_btn);
		ns_panic_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(myContext)
						.setTitle("NS.Panic Alert")
						.setIcon(R.drawable.icon_1)
						.setMessage("Panic alert issue between 14 Evergreen St to 36 Evergreen St ")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();

			}
		});

		// MANAGEMENT BUTTON
		LinearLayout manage = (LinearLayout) findViewById(R.id.management_btn);
		manage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(myContext, ManagementDash.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});
		
		
		// Camera BUTTON
				LinearLayout camera = (LinearLayout) findViewById(R.id.camera_btn);
				camera.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						Intent i = new Intent(myContext, Camera.class);
						startActivityForResult(i, 700);
						overridePendingTransition(R.anim.slide_in_right,
								R.anim.slide_out_left);

					}
				});

	}
}
