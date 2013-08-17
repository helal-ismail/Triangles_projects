package com.kreatitdesign.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {
	
	Context myContext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		Button sign_in = (Button) findViewById(R.id.signin);
		sign_in.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				
				Intent i = new Intent(myContext, MainDash.class);
				startActivityForResult(i, 700);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
				

			}
		});
		
		
		
	}

}
