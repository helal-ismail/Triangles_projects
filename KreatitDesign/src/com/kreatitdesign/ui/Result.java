package com.kreatitdesign.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.kreatitdesign.core.Constants;
import com.kreatitdesign.core.GlobalState;
import com.kreatitdesign.core.KreatitDesignApp;
import com.kreatitdesign.core.User;
import com.kreatitdesign.network.RequestTask;

public class Result extends Activity {

	Context myContext = this;

	ProgressBar prog_bar;
	LinearLayout progress;

	KreatitDesignApp bindAlertApp = new KreatitDesignApp();

	GlobalState globalState = GlobalState.getInstance();
	User user;

	TextView title;
	TextView result;
	TextView check;
	Button edit;

	EditText new_value;
	Button update;
	RadioGroup radio_group;

	String mode;
	String opetational_mode;
	String tag;
	int task_number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Bind Result");

		user = globalState.user;

		progress = (LinearLayout) findViewById(R.id.progress_bar_container);

		prog_bar = (ProgressBar) findViewById(R.id.progress_bar);
		prog_bar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		progress.setVisibility(View.GONE);

		title = (TextView) findViewById(R.id.title);
		result = (TextView) findViewById(R.id.result);
		check = (TextView) findViewById(R.id.check);
		edit = (Button) findViewById(R.id.edit_button);

		new_value = (EditText) findViewById(R.id.new_value);
		update = (Button) findViewById(R.id.update);
		radio_group = (RadioGroup) findViewById(R.id.radio_group);
		radio_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// checkedId is the RadioButton selected

				switch (checkedId) {

				case R.id.normal:
					opetational_mode = "NORMAL";
					break;

				case R.id.bind:
					opetational_mode = "BIND";
					break;

				case R.id.neigbour_guardian:
					opetational_mode = "NEIGBOUR_GUARDIAN";
					break;

				default:
					break;
				}

			}
		});

		title.setVisibility(View.VISIBLE);
		result.setVisibility(View.VISIBLE);
		check.setVisibility(View.VISIBLE);
		edit.setVisibility(View.GONE);
		new_value.setVisibility(View.GONE);
		update.setVisibility(View.GONE);
		radio_group.setVisibility(View.GONE);

		Intent intent = getIntent();
		title.setText(intent.getStringExtra("title"));
		result.setText(intent.getStringExtra("result"));

		mode = intent.getStringExtra("mode");
		if (mode.equals("mode_1")) {
			result.setTextSize((float) 50.0);
			check.setVisibility(View.VISIBLE);
		} else {
			result.setTextSize((float) 30.0);
			check.setVisibility(View.GONE);
		}

		if (mode.equals("operational")) {
			opetational_mode = "NORMAL";
			radio_group.setVisibility(View.VISIBLE);
			update.setVisibility(View.VISIBLE);
		}

		if (mode.equals("mode_2")) {
			update.setVisibility(View.VISIBLE);
			tag = intent.getStringExtra("tag");
			
			String number = intent.getStringExtra("taskNumber");
			task_number = (int) Integer.parseInt(number);
		}

		// Update BUTTON
		update.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mode.equals("operational")) {
					try {

						ResultTask task = new ResultTask();
						task.taskNumber = 4;
						task.title = (String) title.getText();
						task.tag = "op_mode";

						JSONArray arr = new JSONArray();
						JSONObject arm = new JSONObject();
						try {
							arm.put("op_mode", opetational_mode);

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

				if (mode.equals("mode_2")) {
					update.setVisibility(View.GONE);
					new_value.setVisibility(View.VISIBLE);
					edit.setVisibility(View.VISIBLE);
				}

			}
		});

		
		// Edit BUTTON
		edit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				try {

					ResultTask task = new ResultTask();
					task.taskNumber = task_number;
					task.title = (String) title.getText();
					task.tag = tag;

					JSONArray arr = new JSONArray();
					JSONObject arm = new JSONObject();
					try {
						arm.put(tag, new_value.getEditableText().toString());

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

	}

	// =================================================================================

	private class ResultTask extends RequestTask {

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

				progress.setVisibility(View.GONE);

				finish();
				Intent i = new Intent(myContext, Result.class);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

				i.putExtra("title", title);
				i.putExtra("result", disp);
				i.putExtra("mode", "mode_1");

				startActivityForResult(i, 700);

				Log.d(Constants.TAG, "RESULT : " + result);

			} catch (Exception e) {
				// TODO: handle exception
				Log.d(Constants.TAG, "Exception : " + e.getMessage());
			}
		}
	}

}
