package com.kreatitdesign.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ManagementDash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_managementdash);
		
		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Management Dashboard");
		
	}
}
