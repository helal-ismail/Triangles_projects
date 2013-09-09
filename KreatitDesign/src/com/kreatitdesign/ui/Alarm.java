package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

public class Alarm extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Alarm");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		// Get time BUTTON
		Button get_time = (Button) findViewById(R.id.time_view);
		get_time.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AlarmTask task = new AlarmTask();
					task.taskNumber = 15;
					task.title = "Alarm Time";

					JSONArray arr = new JSONArray();
					arr.put("time");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update time BUTTON
		Button update_time = (Button) findViewById(R.id.time_update);
		update_time.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update Time");
				alert.setMessage("Please enter the new time ");
				alert.setIcon(R.drawable.icon_1);

				// Set an EditText view to get user input
				final EditText inputName = new EditText(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));
				alert.setView(inputName);

				alert.setPositiveButton("Update",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String time = inputName.getText().toString();

								try {

									AlarmTask task = new AlarmTask();
									task.taskNumber = 15;
									task.title = "Update Time";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("time", time);

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									arr.put(arm);

									task.arr = arr;

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

		// Get schedule BUTTON
		Button get_sch = (Button) findViewById(R.id.sch_view);
		get_sch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AlarmTask task = new AlarmTask();
					task.taskNumber = 15;
					task.title = "Alarm Schedule";

					JSONArray arr = new JSONArray();
					arr.put("sch");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update schedule BUTTON
		Button update_sch = (Button) findViewById(R.id.sch_update);
		update_sch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update Schedule");
				alert.setMessage("Please enter the new schedule ");
				alert.setIcon(R.drawable.icon_1);

				// Set an EditText view to get user input
				final EditText inputName = new EditText(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));
				alert.setView(inputName);

				alert.setPositiveButton("Update",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String sch = inputName.getText().toString();

								try {

									AlarmTask task = new AlarmTask();
									task.taskNumber = 15;
									task.title = "Update Schedule";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("sch", sch);

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									arr.put(arm);

									task.arr = arr;

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

		// Get enable BUTTON
		Button get_enable = (Button) findViewById(R.id.enable_view);
		get_enable.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AlarmTask task = new AlarmTask();
					task.taskNumber = 15;
					task.title = "Alarm Enable";

					JSONArray arr = new JSONArray();
					arr.put("en");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update enable BUTTON
		Button update_enable = (Button) findViewById(R.id.enable_update);
		update_enable.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update Enable");
				alert.setMessage("Please enter the new permesion ");
				alert.setIcon(R.drawable.icon_1);

				// Set an EditText view to get user input
				final EditText inputName = new EditText(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));
				alert.setView(inputName);

				alert.setPositiveButton("Update",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String en = inputName.getText().toString();

								try {

									AlarmTask task = new AlarmTask();
									task.taskNumber = 15;
									task.title = "Update Permission";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("en", en);

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									arr.put(arm);

									task.arr = arr;

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

	}

	// =================================================================================

	private class AlarmTask extends RequestTask {

		int taskNumber;
		String title;
		JSONArray arr;

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

				if (!msg.optString("time").equals(""))
					disp = msg.optString("time");

				else if (!msg.optString("sch").equals(""))
					disp = msg.optString("sch");

				else if (!msg.optString("en").equals(""))
					disp = msg.optString("en");

				else if (!msg.optString("disp").equals(""))
					disp = msg.optString("disp");

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle(title)
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
