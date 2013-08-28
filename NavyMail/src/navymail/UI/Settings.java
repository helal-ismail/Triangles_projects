package navymail.UI;

import navymail.core.NavyMailApp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {

	Button loginButoButton;
	ImageView clear;
	EditText passField;
	LinearLayout numPad;
	NavyMailApp appInstance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		appInstance = (NavyMailApp)getApplication();
		passField = (EditText) findViewById(R.id.password_field);
		numPad = (LinearLayout) findViewById(R.id.num_pad);
		loginButoButton = (Button)findViewById(R.id.login_button);
		clear = (ImageView)findViewById(R.id.clear);
		setUpClickListeners(); 
		
	}
	
	private void setUpClickListeners(){
		for (int i = 0; i < 4; i++) {
			LinearLayout child = (LinearLayout) numPad.getChildAt(i);
			for (int j = 0; j < 3; j++) {
				Button btn = (Button) child.getChildAt(j);
				btn.setOnClickListener(this);
			}
		}
		loginButoButton.setOnClickListener(this);
		clear.setOnClickListener(this);
		passField.setFocusable(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear:
			passField.setText("");
			break;
		
		case R.id.login_button:
			changePass();
			break;
			
		default:
			Button btn = (Button) v;
			String num = btn.getText().toString();
			passField.append(num);
			break;
		}
	}
	
	private void changePass(){
		String password = passField.getEditableText().toString();
		if(password != null && password.length() > 2){
			appInstance.setPassword(password);
			Intent loginScreen = new Intent(this, Login.class);
			finish();
			startActivity(loginScreen);
		}
		else
		{
			Toast.makeText(this, "من فضلك أدخل 3 أرقم على اﻷقل", Toast.LENGTH_LONG).show();
		}
	}

}
