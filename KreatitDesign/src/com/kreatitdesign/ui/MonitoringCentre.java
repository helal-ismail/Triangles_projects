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

		// Get mc1 BUTTON
		Button get_mc1 = (Button) findViewById(R.id.mc1_view);
		get_mc1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 1";

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

		// Update mc1 BUTTON
		Button update_mc1 = (Button) findViewById(R.id.mc1_update);
		update_mc1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 1");
				alert.setMessage("Please enter the new M.C 1");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 1";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc1", mc);

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
		// Get mc2 BUTTON
		Button get_mc2 = (Button) findViewById(R.id.mc2_view);
		get_mc2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 2";

					JSONArray arr = new JSONArray();
					arr.put("mc2");
					task.arr = arr;

					task.execute();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Update mc2 BUTTON
		Button update_mc2 = (Button) findViewById(R.id.mc2_update);
		update_mc2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 2");
				alert.setMessage("Please enter the new M.C 2");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 2";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc2", mc);

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
		// Get mc3 BUTTON
		Button get_mc3 = (Button) findViewById(R.id.mc3_view);
		get_mc3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 3";

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

		// Update mc1 BUTTON
		Button update_mc3 = (Button) findViewById(R.id.mc3_update);
		update_mc3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 3");
				alert.setMessage("Please enter the new M.C 3");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 3";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc3", mc);

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
		// Get mc4 BUTTON
		Button get_mc4 = (Button) findViewById(R.id.mc4_view);
		get_mc4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 4";

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

		// Update mc4 BUTTON
		Button update_mc4 = (Button) findViewById(R.id.mc4_update);
		update_mc4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 4");
				alert.setMessage("Please enter the new M.C 4");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 4";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc4", mc);

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
		// Get mc5 BUTTON
		Button get_mc5 = (Button) findViewById(R.id.mc5_view);
		get_mc5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 5";

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

		// Update mc5 BUTTON
		Button update_mc5 = (Button) findViewById(R.id.mc5_update);
		update_mc5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 5");
				alert.setMessage("Please enter the new M.C 5");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 5";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc5", mc);

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
		// Get mc6 BUTTON
		Button get_mc6 = (Button) findViewById(R.id.mc6_view);
		get_mc1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 6";

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

		// Update mc6 BUTTON
		Button update_mc6 = (Button) findViewById(R.id.mc6_update);
		update_mc6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 6");
				alert.setMessage("Please enter the new M.C 6");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 6";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc6", mc);

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
		// Get mc7 BUTTON
		Button get_mc7 = (Button) findViewById(R.id.mc7_view);
		get_mc7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 7";

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

		// Update mc7 BUTTON
		Button update_mc7 = (Button) findViewById(R.id.mc7_update);
		update_mc7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 7");
				alert.setMessage("Please enter the new M.C 7");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 7";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc7", mc);

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
		// Get mc1 BUTTON
		Button get_mc8 = (Button) findViewById(R.id.mc8_view);
		get_mc8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 8";

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

		// Update mc8 BUTTON
		Button update_mc8 = (Button) findViewById(R.id.mc8_update);
		update_mc8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 8");
				alert.setMessage("Please enter the new M.C 8");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 8";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc8", mc);

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
		// Get mc9 BUTTON
		Button get_mc9 = (Button) findViewById(R.id.mc9_view);
		get_mc9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 9";

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

		// Update mc9 BUTTON
		Button update_mc9 = (Button) findViewById(R.id.mc9_update);
		update_mc9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 9");
				alert.setMessage("Please enter the new M.C 9");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 9";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc9", mc);

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
		// Get mc10 BUTTON
		Button get_mc10 = (Button) findViewById(R.id.mc10_view);
		get_mc10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Mnitoring Centre 10";

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

		// Update mc10 BUTTON
		Button update_mc10 = (Button) findViewById(R.id.mc10_update);
		update_mc10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Mnitoring Centre 10");
				alert.setMessage("Please enter the new M.C 10");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Mnitoring Centre 10";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("mc10", mc);

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
		// Get id BUTTON
		Button get_id = (Button) findViewById(R.id.id_view);
		get_id.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Commercial M.C ID";

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

		// Update id BUTTON
		Button update_id = (Button) findViewById(R.id.id_update);
		update_id.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Commercial M.C ID");
				alert.setMessage("Please enter the new M.C ID");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Commercial M.C ID";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("pmc_bindid", mc);

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

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
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
		// Get username BUTTON
		Button get_username = (Button) findViewById(R.id.username_view);
		get_username.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
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
		// Get pass BUTTON
		Button get_pass = (Button) findViewById(R.id.password_view);
		get_pass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
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

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
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
		// Get channel BUTTON
		Button get_channel = (Button) findViewById(R.id.channel_view);
		get_channel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Modem Channel";

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

		// Update channel BUTTON
		Button update_channel = (Button) findViewById(R.id.channel_update);
		update_channel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Modem Channel");
				alert.setMessage("Please enter the new M.C channel");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Modem Channel";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("modem_channel", mc);

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

					MonitoringTask task = new MonitoringTask();
					task.taskNumber = 5;
					task.title = "Modem PANID";

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

		// Update panid BUTTON
		Button update_panid = (Button) findViewById(R.id.panid_update);
		update_panid.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						new ContextThemeWrapper(myContext,
								android.R.style.Theme_Holo_Dialog));

				alert.setTitle("Modem PANID");
				alert.setMessage("Please enter the new M.C PANID");
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

									MonitoringTask task = new MonitoringTask();
									task.taskNumber = 5;
									task.title = "Modem PANID";

									JSONArray arr = new JSONArray();
									JSONObject arm = new JSONObject();
									try {
										arm.put("modem_panid", mc);

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

	private class MonitoringTask extends RequestTask {

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

				if (!msg.optString("mc1").equals(""))
					disp = msg.optString("mc1");
				else if (!msg.optString("mc2").equals(""))
					disp = msg.optString("mc2");
				else if (!msg.optString("mc3").equals(""))
					disp = msg.optString("mc3");
				else if (!msg.optString("mc4").equals(""))
					disp = msg.optString("mc4");
				else if (!msg.optString("mc5").equals(""))
					disp = msg.optString("mc5");
				else if (!msg.optString("mc6").equals(""))
					disp = msg.optString("mc6");
				else if (!msg.optString("mc7").equals(""))
					disp = msg.optString("mc7");
				else if (!msg.optString("mc8").equals(""))
					disp = msg.optString("mc8");
				else if (!msg.optString("mc9").equals(""))
					disp = msg.optString("mc9");
				else if (!msg.optString("mc10").equals(""))
					disp = msg.optString("mc10");
				
				else if (!msg.optString("pmc_bindid").equals(""))
					disp = msg.optString("pmc_bindid");
				else if (!msg.optString("pmc_key").equals(""))
					disp = msg.optString("pmc_key");
				else if (!msg.optString("pmc_username").equals(""))
					disp = msg.optString("pmc_username");
				else if (!msg.optString("pmc_password").equals(""))
					disp = msg.optString("pmc_password");
				else if (!msg.optString("pmc_en").equals(""))
					disp = msg.optString("pmc_en");
				else if (!msg.optString("modem_channel").equals(""))
					disp = msg.optString("modem_channel");
				else if (!msg.optString("modem_panid").equals(""))
					disp = msg.optString("modem_panid");
				
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
