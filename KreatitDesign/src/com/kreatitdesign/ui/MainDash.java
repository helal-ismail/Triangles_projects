package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.network.RequestTask;

@SuppressLint("InlinedApi")
public class MainDash extends Activity {

	Context myContext = this;

	String lock_key = "";
	
	ProgressBar prog_bar;
	LinearLayout progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maindash);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Main Dashboard");
		
		
		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);
		

		// LOCK BUTTON
		LinearLayout lock_btn = (LinearLayout) findViewById(R.id.lock_btn);
		lock_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("System Lock");
				alert.setMessage("Please enter your lock / unlock key ");
				alert.setIcon(R.drawable.icon_1);

				// Set an EditText view to get user input
				final EditText inputName = new EditText(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));
				alert.setView(inputName);

				alert.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								lock_key = inputName.getText().toString();

								try {

									LockTask task = new LockTask();
									task.execute();

								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});

				alert.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				alert.show();
			}
		});

		// STATUS BUTTON
		LinearLayout status_btn = (LinearLayout) findViewById(R.id.sys_btn);
		status_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle("System Status")
						.setIcon(R.drawable.icon_1)
						.setMessage("System Installed Successfully")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();

			}
		});

		// PANIC BUTTON
		LinearLayout panic_btn = (LinearLayout) findViewById(R.id.panic_btn);
		panic_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle("Panic Alert")
						.setIcon(R.drawable.icon_1)
						.setMessage("Panic Alert from Tyrone ")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();

			}
		});

		// NS PANIC BUTTON
		LinearLayout ns_panic_btn = (LinearLayout) findViewById(R.id.ns_panic_btn);
		ns_panic_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle("NS.Panic Alert")
						.setIcon(R.drawable.icon_1)
						.setMessage(
								"Panic alert issue between 14 Evergreen St to 36 Evergreen St ")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
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

	// =================================================================================

	private class LockTask extends RequestTask {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			 progress.setVisibility(View.VISIBLE);

			JSONArray arr = new JSONArray();
			JSONObject arm = new JSONObject();
			try 
			{
				arm.put("arm", lock_key);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arr.put(arm);

			this.setupParams("Tyrone", 3, "demo0001", "tcousins", "getin123",
					arr);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				
				JSONObject object = new JSONObject(result);
				JSONObject msg = object.optJSONObject("msg");
				String disp = msg.optString("disp");

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle("System Lock")
						.setIcon(R.drawable.icon_1)
						.setMessage(disp)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
				

				 progress.setVisibility(View.GONE);

				Log.d(Constants.TAG, "RESULT : " + result);

			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Constants.TAG, "Exception : " + e.getMessage());
			}
		}
	}

}
