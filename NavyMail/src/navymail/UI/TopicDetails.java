package navymail.UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import navymail.adapters.ImageAdapter;
import navymail.core.ApplicationController;
import navymail.modules.Ta2shera;
import navymail.modules.Topic;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TopicDetails extends Activity {

	int topicID;
	int topicPhotoIndex;
	Context mContext = this;
	ApplicationController controller = ApplicationController.getInstance();
	ViewPager pager;
//	FrameLayout currentFrame;
	FrameLayout imgFrame;
	Topic currentTopic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic_details);
		imgFrame = (FrameLayout)findViewById(R.id.img_frame);
		pager = (ViewPager)findViewById(R.id.view_pager);
		setUpTopic();
		setUpClickListners();
		ImageAdapter adapter = new ImageAdapter(this, currentTopic);
		pager.setAdapter(adapter);
		
	}

	private void setUpTopic() {
		topicID = (Integer) getIntent().getExtras().get("topicID");

		ArrayList<String> imageUrls = new ArrayList<String>();
		File PhotoDir = new File(Environment.getExternalStorageDirectory(),
				"navy");
		PhotoDir.mkdir();

		File[] urls = PhotoDir.listFiles();
		for (int i = 0; i < urls.length; i++) {
			if (urls[i].getPath().contains(".jp"))
				imageUrls.add(urls[i].getPath());
		}

	currentTopic = new Topic(1, "بشأن أى حاجة", "ملخص الموضوع الفلانى",imageUrls, false);
	}
	
	
	private void setUpClickListners(){
		Button ta2shera = (Button)findViewById(R.id.ta2shera);	
		ta2shera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {				
				putSignature();
			}
		});
		
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {	
			@Override
			public void onPageSelected(int pos) {
				
				if(imgFrame.getChildCount() > 1)
					imgFrame.removeViewAt(1);
				
				Ta2shera t = currentTopic.hash.get(pager.getCurrentItem());
				if(t != null){
					restoreSignature(t, imgFrame);
					
				}
				
				

			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button showHide = (Button)findViewById(R.id.show_hide);
		showHide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Button btn = (Button)arg0;
				RelativeLayout toolBox = (RelativeLayout)findViewById(R.id.tool_box);
				if(toolBox.getVisibility() == RelativeLayout.GONE){
					toolBox.setVisibility(RelativeLayout.VISIBLE);
					btn.setBackgroundResource(R.drawable.down);
				}
					
				else
				{
					toolBox.setVisibility(RelativeLayout.GONE);
					btn.setBackgroundResource(R.drawable.up);
				}
			}
		});
		
		Button addUnits = (Button)findViewById(R.id.add_units);
		addUnits.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, UnitSelection.class);
				startActivity(intent);
			}
		});
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.navy_units, menu);
	    
	}
	
	private void putSignature(){
		imgFrame = (FrameLayout)findViewById(R.id.img_frame);
		
		if(imgFrame.getChildCount() > 1)
		{
			imgFrame.removeViewAt(1);
			currentTopic.hash.remove(pager.getCurrentItem());
		}
		else
		{
			Toast.makeText(mContext, "من فضلك ضع تأشيرة القائد", Toast.LENGTH_LONG).show();
			pager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				
				
				int x = (int)e.getX();
				int y = (int)e.getY();
				LayoutInflater inflater = LayoutInflater.from(mContext);
				LinearLayout l = (LinearLayout)inflater.inflate(R.layout.custom_signature, null);
				ImageView signature  = (ImageView)l.getChildAt(0);
				signature.setLayoutParams(new LinearLayout.LayoutParams(100,100));
				
				TextView sigDate = (TextView)l.getChildAt(1);
				Date d = new Date();
				String date = d.getDate() + "-"+ (d.getMonth()+1) +"-"+(d.getYear()+1900);
				date = controller.arabization(date);
				sigDate.setText(date);
				
				imgFrame.addView(l);
				l.setX(x);
				l.setY(y);
				
				currentTopic.eSigned = true;
				v.setOnTouchListener(null);
				
				Ta2shera ta2shera = new Ta2shera(x,y,R.layout.custom_signature);
				currentTopic.hash.put(pager.getCurrentItem(), ta2shera);
				return false;
			}
		});
		}
	}
	
	private void restoreSignature(Ta2shera t, FrameLayout frame){
		
		
		LayoutInflater inflater = LayoutInflater.from(mContext);
		LinearLayout l = (LinearLayout)inflater.inflate(R.layout.custom_signature, null);
		ImageView signature  = (ImageView)l.getChildAt(0);
		signature.setLayoutParams(new LinearLayout.LayoutParams(100,100));
		
		TextView sigDate = (TextView)l.getChildAt(1);
		Date d = new Date();
		String date = d.getDate() + "-"+ (d.getMonth()+1) +"-"+(d.getYear()+1900);
		date = controller.arabization(date);
		sigDate.setText(date);
		
		frame.addView(l);
		l.setX(t.xPos);
		l.setY(t.yPos);
	}

}
