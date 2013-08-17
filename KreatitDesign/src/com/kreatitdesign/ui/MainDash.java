package com.kreatitdesign.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainDash extends Activity {
	
	Context myContext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maindash);
		
		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Main Dashboard");
		
		
		LinearLayout manage = (LinearLayout) findViewById(R.id.management_btn);
		manage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				
				Intent i = new Intent(myContext, ManagementDash.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
				

			}
		});
		
	}
}
