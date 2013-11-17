package com.navyscan.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity implements OnClickListener {

	Button newDa5ly;
	Button newKhargy;
	Button refresh;
	Button exit;
	Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		newDa5ly = (Button)findViewById(R.id.new_da5ly);
		newDa5ly.setOnClickListener(this);
		
		newKhargy = (Button)findViewById(R.id.new_khargy);
		newKhargy.setOnClickListener(this);
		
		refresh = (Button)findViewById(R.id.refresh);
		refresh.setOnClickListener(this);
		
		exit = (Button)findViewById(R.id.exit);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.new_da5ly:
			Intent dataEntry = new Intent(mContext, DataEntry.class);
			startActivity(dataEntry);
			break;

		case R.id.new_khargy:
			dataEntry = new Intent(mContext, DataEntry.class);
			startActivity(dataEntry);
			break;

		case R.id.refresh:
			Toast.makeText(mContext, "REFRESH", Toast.LENGTH_LONG).show();
			break;

		case R.id.exit:
			finish();
			break;
		default:
			break;
		}
	}

}
