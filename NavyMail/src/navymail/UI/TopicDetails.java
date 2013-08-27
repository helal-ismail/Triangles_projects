package navymail.UI;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import navymail.adapters.ImageAdapter;
import navymail.core.ApplicationController;
import navymail.modules.Ta2shera;
import navymail.modules.Topic;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TopicDetails extends Activity {

	int topicID;
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
		currentTopic = controller.khargy_topics.get(topicID);
		
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
				//date = controller.arabization(date);
				String preparedStr = prepareSignature();
				
				sigDate.setText(date+"\n"+preparedStr);
				
				imgFrame.addView(l);
				l.setX(x);
				l.setY(y);
				
				currentTopic.eSigned = true;
				v.setOnTouchListener(null);
				
				Ta2shera ta2shera = new Ta2shera(x,y,R.layout.custom_signature);
				currentTopic.hash.put(pager.getCurrentItem(), ta2shera);
			//	exportImage();
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
		sigDate.setText(date + "\n"+controller.cachedOrders);
		
		frame.addView(l);
		l.setX(t.xPos);
		l.setY(t.yPos);
	}
	
	private String prepareSignature(){
		RelativeLayout tool_box = (RelativeLayout)findViewById(R.id.tool_box);
		String str = "";
		for(int i = 0 ; i < 2 ; i ++){
			LinearLayout container = (LinearLayout)tool_box.getChildAt(i);
			for (int j = 0 ; j < container.getChildCount() ; j ++){
				CheckBox c = (CheckBox)container.getChildAt(j);
				if(c.isChecked())
				{
					 str = str + c.getText() + "\n";
				}
			}
		}
		controller.cachedOrders = str;
		return str;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		exportImage();
	}
	
	private void exportImage(){
		try{
		imgFrame.setDrawingCacheEnabled(true);
		imgFrame.buildDrawingCache();
		Bitmap bm = imgFrame.getDrawingCache();
		
		File PhotoDir = new File(Environment.getExternalStorageDirectory(),"navy");
		PhotoDir.mkdir();
		File topicFolder = new File(PhotoDir, currentTopic.title);
		topicFolder.mkdir();
		File newImage = new File(topicFolder, "j"+pager.getCurrentItem()+".jpg");
		FileOutputStream out = new FileOutputStream(newImage);
	    bm.compress(Bitmap.CompressFormat.JPEG, 30, out);
		}
		catch(Exception e)
		{
			Log.d("helal", e.getMessage());
		}
	}

}
