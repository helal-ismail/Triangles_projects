package com.kreatitdesign.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Camera extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		TextView pageTitle = (TextView) findViewById(R.id.page_title);
		pageTitle.setText("Camera");
	}
		

}
