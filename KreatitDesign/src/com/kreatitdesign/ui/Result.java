package com.kreatitdesign.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends Activity {

	TextView title;
	TextView result;
	TextView check;
	Button edit;

	LinearLayout update_panel;
	EditText new_value;
	Button update;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		
		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Bind Result");
		
		
		title = (TextView) findViewById(R.id.title);
		result = (TextView) findViewById(R.id.result);
		check = (TextView) findViewById(R.id.check);
		edit = (Button) findViewById(R.id.edit);
		
		update_panel = (LinearLayout) findViewById(R.id.update_panel);
		new_value = (EditText) findViewById(R.id.new_value);
		update = (Button) findViewById(R.id.update);
		
		
		title.setVisibility(View.VISIBLE);
		result.setVisibility(View.VISIBLE);
		check.setVisibility(View.VISIBLE);
		edit.setVisibility(View.GONE);
		update_panel.setVisibility(View.GONE);
		
		
		
		Intent intent = getIntent();
		title.setText(intent.getStringExtra("title"));
		result.setText(intent.getStringExtra("result"));
		

	}

}
