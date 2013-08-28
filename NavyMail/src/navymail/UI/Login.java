package navymail.UI;

import navymail.core.NavyMailApp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	EditText passField;
	LinearLayout numPad;
	ImageView clear;
	Button loginButoButton;
	ProgressBar progressBar;
	LinearLayout verification_panel;
	LinearLayout layout0;
	LinearLayout layout1;
	TextView changePass;
	NavyMailApp appInstance;
	Context mContext = this;
	Handler handler = new Handler();

	

	// animation
		private Animation mSlideInLeft;
		private Animation mSlideOutRight;
		private Animation mSlideInRight;
		private Animation mSlideOutLeft;
		private Animation mFade;
		private Animation mSlideOutBottom;
		private Animation mSlideInBottom;
		private Animation mSlideOutTop;
		private Animation mSlideInTop;
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		appInstance = (NavyMailApp)getApplication();
		passField = (EditText) findViewById(R.id.password_field);
		numPad = (LinearLayout) findViewById(R.id.num_pad);
		clear = (ImageView)findViewById(R.id.clear);
		loginButoButton = (Button)findViewById(R.id.login_button);
		progressBar = (ProgressBar)findViewById(R.id.progress_bar);
		verification_panel = (LinearLayout)findViewById(R.id.verification_panel);
		layout0 = (LinearLayout)findViewById(R.id.layout0);
		layout1 = (LinearLayout)findViewById(R.id.layout1);
		changePass = (TextView)findViewById(R.id.change_pas);
		
		setUpClickListerns(); 
		
		this.initAnimation();
	}

	private void initAnimation() {
		// animation
		mSlideInLeft = AnimationUtils.loadAnimation(mContext,
				R.anim.push_left_in);
		mSlideOutRight = AnimationUtils.loadAnimation(mContext,
				R.anim.push_right_out);
		mSlideInRight = AnimationUtils.loadAnimation(mContext,
				R.anim.push_right_in);
		mSlideOutLeft = AnimationUtils.loadAnimation(mContext,
				R.anim.push_left_out);
		
		mFade = AnimationUtils.loadAnimation(mContext,
				R.anim.fade_in);
		
		mSlideOutBottom = AnimationUtils.loadAnimation(mContext,
				R.anim.slide_out_bottom);
		
		mSlideInBottom = AnimationUtils.loadAnimation(mContext,
				R.anim.slide_in_bottom);
		
		mSlideOutTop = AnimationUtils.loadAnimation(mContext,
				R.anim.slide_out_top);
		
		mSlideInTop = AnimationUtils.loadAnimation(mContext,
				R.anim.slide_in_top);
	}
	
	
	private void setUpClickListerns() {
		for (int i = 0; i < 4; i++) {
			LinearLayout child = (LinearLayout) numPad.getChildAt(i);
			for (int j = 0; j < 3; j++) {
				Button btn = (Button) child.getChildAt(j);
				btn.setOnClickListener(this);
			}
		}
		clear.setOnClickListener(this);
		loginButoButton.setOnClickListener(this);
		changePass.setOnClickListener(this);
		passField.setFocusable(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear:
			passField.setText("");
			break;
		
		case R.id.login_button:
			loginBehaviour();
			break;
			
		case R.id.change_pas :
			Intent changePassScreen = new Intent(mContext, Settings.class);
			finish();
			startActivity(changePassScreen);
			break;
		
		default:
			Button btn = (Button) v;
			String num = btn.getText().toString();
			passField.append(num);
			break;
		}
	}
	
	private void loginBehaviour(){
		
		String password = passField.getEditableText().toString();
		boolean passValid = appInstance.checkPassword(password);
		final Runnable r1 = new Runnable() {
			@Override
			public void run() {
				
				Intent homeScreen = new Intent(mContext, Home.class);
				finish();
				startActivity(homeScreen);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			}
		};
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				int progress = progressBar.getProgress();
				if (progress < 99)
				{
					progressBar.setProgress(progress+33);
					handler.postDelayed(this, 750);
				}
				else
				{
					progressBar.setProgress(100);
					
					layout0.setVisibility(View.GONE);
					layout0.startAnimation(mSlideOutTop);
					
					layout1.setVisibility(View.VISIBLE);
					layout1.startAnimation(mFade);	
					handler.postDelayed(r1, 3000);
				}
			}
		};
		
		
		if(passValid)
		{
			changePass.setVisibility(View.GONE);
			verification_panel.setVisibility(LinearLayout.VISIBLE);
			handler.postDelayed(r, 750);
		}
		else
		{
			Toast.makeText(mContext, "كلمة المرور غير صحيحة", Toast.LENGTH_LONG).show();
		}
	}

}
