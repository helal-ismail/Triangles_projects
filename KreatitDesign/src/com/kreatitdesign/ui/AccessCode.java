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

public class AccessCode extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_access_code);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Access Code");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		// Get Access BUTTON
		Button get_access = (Button) findViewById(R.id.access_view);
		get_access.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AccessCodeTask task = new AccessCodeTask();
					task.taskNumber = 6;
					task.title = "Access Code";

					JSONArray arr = new JSONArray();
					arr.put("ac");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update Access BUTTON
		Button update_access = (Button) findViewById(R.id.access_update);
		update_access.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(new ContextThemeWrapper(myContext,
						android.R.style.Theme_Holo_Dialog))
						.setTitle("Access Code")
						.setIcon(R.drawable.icon_1)
						.setMessage("You Can't Update Your Access Code !!")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();

			}
		});

		// Get Name BUTTON
		Button get_name = (Button) findViewById(R.id.name_view);
		get_name.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AccessCodeTask task = new AccessCodeTask();
					task.taskNumber = 6;
					task.title = "Ac_Code [ Name ]";

					JSONArray arr = new JSONArray();
					arr.put("susername");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update Name BUTTON
		Button update_name = (Button) findViewById(R.id.name_update);
		update_name.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update User Name");
				alert.setMessage("Please enter the new name ");
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
								String name = inputName.getText().toString();

								try {

									AccessCodeTask task = new AccessCodeTask();
									task.taskNumber = 6;
									task.title = "Update User Name";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("susername", name);

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

		// Get Password BUTTON
		Button get_pass = (Button) findViewById(R.id.pass_view);
		get_pass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					AccessCodeTask task = new AccessCodeTask();
					task.taskNumber = 6;
					task.title = "Ac_Code [ Password ]";

					JSONArray arr = new JSONArray();
					arr.put("spassword");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update Pass BUTTON
		Button update_pass = (Button) findViewById(R.id.pass_update);
		update_pass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update User Password");
				alert.setMessage("Please enter the new name ");
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
								String pass = inputName.getText().toString();

								try {

									AccessCodeTask task = new AccessCodeTask();
									task.taskNumber = 6;
									task.title = "Update User Password";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("susername", pass);

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

	private class AccessCodeTask extends RequestTask {

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

				if (!msg.optString("ac").equals(""))
					disp = msg.optString("ac");

				else if (!msg.optString("susername").equals(""))
					disp = msg.optString("susername");

				else if (!msg.optString("spassword").equals(""))
					disp = msg.optString("spassword");
				
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
