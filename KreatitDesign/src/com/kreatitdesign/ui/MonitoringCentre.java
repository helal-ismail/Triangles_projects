package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONException;
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

public class MonitoringCentre extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoring);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Mnitoring Centre");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		// =============================================================================
		// ID
		LinearLayout monitor_1 = (LinearLayout) findViewById(R.id.monitor1);
		monitor_1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C ID";
					task.tag = "pmc_bindid";

					JSONArray arr = new JSONArray();
					arr.put("pmc_bindid");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// KEY
		LinearLayout monitor_2 = (LinearLayout) findViewById(R.id.monitor2);
		monitor_2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C key";
					task.tag = "pmc_key";

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

		// =============================================================================

		// =============================================================================
		// USER NAME
		LinearLayout monitor_3 = (LinearLayout) findViewById(R.id.monitor3);
		monitor_3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C UserName";
					task.tag = "pmc_username";

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

		// =============================================================================

		// =============================================================================
		// PASSWORD
		LinearLayout monitor_4 = (LinearLayout) findViewById(R.id.monitor4);
		monitor_4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C Password";
					task.tag = "pmc_password";

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

		// =============================================================================

		// =============================================================================
		// ENABLE
		LinearLayout monitor_5 = (LinearLayout) findViewById(R.id.monitor5);
		monitor_5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C Enable";
					task.tag = "pmc_en";

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

		// =============================================================================

		// =============================================================================
		// Modem Channel
		LinearLayout monitor_6 = (LinearLayout) findViewById(R.id.monitor6);
		monitor_6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Modem Channel";
					task.tag = "modem_channel";

					JSONArray arr = new JSONArray();
					arr.put("modem_channel");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================
		// =============================================================================
		// Modem PANID
		LinearLayout monitor_7 = (LinearLayout) findViewById(R.id.monitor7);
		monitor_7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Modem PANID";
					task.tag = "modem_panid";

					JSONArray arr = new JSONArray();
					arr.put("modem_panid");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 1
		LinearLayout monitor_8 = (LinearLayout) findViewById(R.id.monitor8);
		monitor_8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 1";
					task.tag = "mc1";

					JSONArray arr = new JSONArray();
					arr.put("mc1");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================
		// =============================================================================
		// Center 2
		LinearLayout monitor_9 = (LinearLayout) findViewById(R.id.monitor9);
		monitor_9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 2";
					task.tag = "mc2";

					JSONArray arr = new JSONArray();
					arr.put("m21");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 3
		LinearLayout monitor_10 = (LinearLayout) findViewById(R.id.monitor10);
		monitor_10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 3";
					task.tag = "mc3";

					JSONArray arr = new JSONArray();
					arr.put("mc3");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 4
		LinearLayout monitor_11 = (LinearLayout) findViewById(R.id.monitor11);
		monitor_11.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 4";
					task.tag = "mc4";

					JSONArray arr = new JSONArray();
					arr.put("mc4");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 5
		LinearLayout monitor_12 = (LinearLayout) findViewById(R.id.monitor12);
		monitor_12.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 5";
					task.tag = "mc5";

					JSONArray arr = new JSONArray();
					arr.put("mc5");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 6
		LinearLayout monitor_13 = (LinearLayout) findViewById(R.id.monitor13);
		monitor_13.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 6";
					task.tag = "mc6";

					JSONArray arr = new JSONArray();
					arr.put("mc6");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 7
		LinearLayout monitor_14 = (LinearLayout) findViewById(R.id.monitor14);
		monitor_14.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 7";
					task.tag = "mc7";

					JSONArray arr = new JSONArray();
					arr.put("mc7");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 8
		LinearLayout monitor_15 = (LinearLayout) findViewById(R.id.monitor15);
		monitor_15.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 8";
					task.tag = "mc8";

					JSONArray arr = new JSONArray();
					arr.put("mc8");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 9
		LinearLayout monitor_16 = (LinearLayout) findViewById(R.id.monitor16);
		monitor_16.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 9";
					task.tag = "mc9";

					JSONArray arr = new JSONArray();
					arr.put("mc9");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

		// =============================================================================
		// Center 10
		LinearLayout monitor_17 = (LinearLayout) findViewById(R.id.monitor17);
		monitor_17.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitorTask task = new MonitorTask();
					task.taskNumber = 5;
					task.title = "Monitoring Center 10";
					task.tag = "mc10";

					JSONArray arr = new JSONArray();
					arr.put("mc10");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// =============================================================================

	}

	// =================================================================================

	private class MonitorTask extends RequestTask {

		int taskNumber;
		String title;
		String tag;
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
				if (!msg.optString(tag).equals(""))
					disp = msg.optString(tag);
				else if (!msg.optString("disp").equals(""))
					disp = msg.optString("disp");

				Intent i = new Intent(myContext, Result.class);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

				i.putExtra("title", title);
				i.putExtra("result", disp);
				i.putExtra("tag", tag);
				i.putExtra("mode", "mode_2");
				String task = "" + taskNumber;
				i.putExtra("taskNumber", task);

				startActivityForResult(i, 700);

				progress.setVisibility(View.GONE);

				Log.d(Constants.TAG, "RESULT : " + result);

			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Constants.TAG, "Exception : " + e.getMessage());
			}
		}
	}

}
