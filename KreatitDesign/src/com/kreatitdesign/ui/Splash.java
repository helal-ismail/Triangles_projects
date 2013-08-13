  package com.kreatitdesign.ui;

import org.json.JSONArray;

import com.kreatitdesign.network.RequestTask;

import android.app.Activity;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		JSONArray arr = new JSONArray();
		arr.put("request");
		
		RequestTask task = new RequestTask();
		task.setupParams("Tyrone", 16, "demo0001", "tcousins", "getin123", arr);
		task.execute();
		
	}

	

}
