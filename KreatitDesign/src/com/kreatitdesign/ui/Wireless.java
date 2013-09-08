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

public class Wireless extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wireless);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Wireless Modem");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		// -----------------------------------------------------------------------------
		// Get channel BUTTON
		Button get_channel = (Button) findViewById(R.id.channel_view);
		get_channel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Wireless Channel";

					JSONArray arr = new JSONArray();
					arr.put("channel");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update channel BUTTON
		Button update_channel = (Button) findViewById(R.id.channel_update);
		update_channel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Wireless Channel");
				alert.setMessage("Please enter the new channel");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Wireless Channel";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("channel", mc);

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
		// -----------------------------------------------------------------------------
		// Get panid BUTTON
		Button get_panid = (Button) findViewById(R.id.panid_view);
		get_panid.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Modem PANID";

					JSONArray arr = new JSONArray();
					arr.put("panid");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update panid BUTTON
		Button update_panid = (Button) findViewById(R.id.panid_update);
		update_panid.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Modem PANID");
				alert.setMessage("Please enter the new PANID");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Modem PANID";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("panid", mc);

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

		// -----------------------------------------------------------------------------
		// Get username BUTTON
		Button get_username = (Button) findViewById(R.id.username_view);
		get_username.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Commercial M.C Username";

					JSONArray arr = new JSONArray();
					arr.put("pmc_username");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update username BUTTON
		Button update_username = (Button) findViewById(R.id.username_update);
		update_username.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Commercial M.C Username");
				alert.setMessage("Please enter the new M.C username");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Commercial M.C Username";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_username", mc);

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

		// -----------------------------------------------------------------------------
		// Get name BUTTON
		Button get_name = (Button) findViewById(R.id.name_view);
		get_name.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Commercial M.C Name";

					JSONArray arr = new JSONArray();
					arr.put("pmc_name");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update name BUTTON
		Button update_name = (Button) findViewById(R.id.name_update);
		update_name.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Commercial M.C Nname");
				alert.setMessage("Please enter the new M.C name");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Commercial M.C Name";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_name", mc);

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

		// -----------------------------------------------------------------------------
		// Get pass BUTTON
		Button get_pass = (Button) findViewById(R.id.password_view);
		get_pass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Commercial M.C Password";

					JSONArray arr = new JSONArray();
					arr.put("pmc_password");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update pass BUTTON
		Button update_pass = (Button) findViewById(R.id.password_update);
		update_pass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Commercial M.C Username");
				alert.setMessage("Please enter the new M.C password");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Commercial M.C Username";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_password", mc);

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
		// -----------------------------------------------------------------------------
		// Get enable BUTTON
		Button get_enable = (Button) findViewById(R.id.enable_view);
		get_enable.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Commercial M.C Enable";

					JSONArray arr = new JSONArray();
					arr.put("pmc_en");
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

				alert.setTitle("Commercial M.C Enable");
				alert.setMessage("Please enter the new M.C enable");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Commercial M.C Enable";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_en", mc);

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

		// -----------------------------------------------------------------------------
		// Get key BUTTON
		Button get_key = (Button) findViewById(R.id.key_view);
		get_key.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Commercial M.C Key";

					JSONArray arr = new JSONArray();
					arr.put("pmc_key");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update key BUTTON
		Button update_key = (Button) findViewById(R.id.key_update);
		update_key.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Commercial M.C Key");
				alert.setMessage("Please enter the new M.C key");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Commercial M.C Key";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_key", mc);

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

		// -----------------------------------------------------------------------------
		// Get available BUTTON
		Button get_available = (Button) findViewById(R.id.available_view);
		get_available.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					WirelessTask task = new WirelessTask();
					task.taskNumber = 10;
					task.title = "Update Available";

					JSONArray arr = new JSONArray();
					arr.put("ua");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update available BUTTON
		Button update_available = (Button) findViewById(R.id.available_update);
		update_available.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Update Available");
				alert.setMessage("Please enter the new Update Available key");
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
								String mc = inputName.getText().toString();

								try {

									WirelessTask task = new WirelessTask();
									task.taskNumber = 10;
									task.title = "Update Available";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("ua", mc);

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

	private class WirelessTask extends RequestTask {

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

				if (!msg.optString("pmc_key").equals(""))
					disp = msg.optString("pmc_key");
				else if (!msg.optString("pmc_username").equals(""))
					disp = msg.optString("pmc_username");
				else if (!msg.optString("pmc_password").equals(""))
					disp = msg.optString("pmc_password");
				else if (!msg.optString("pmc_en").equals(""))
					disp = msg.optString("pmc_en");
				else if (!msg.optString("channel").equals(""))
					disp = msg.optString("channel");
				else if (!msg.optString("panid").equals(""))
					disp = msg.optString("panid");
				else if (!msg.optString("ua").equals(""))
					disp = msg.optString("ua");
				else if (!msg.optString("pmc_name").equals(""))
					disp = msg.optString("pmc_name");

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
