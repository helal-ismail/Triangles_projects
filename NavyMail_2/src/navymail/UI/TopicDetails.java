package navymail.UI;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import navymail.UI2.R;
import navymail.adapters.ImageAdapter;
import navymail.core.ApplicationController;
import navymail.modules.Ta2shera;
import navymail.modules.Topic;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
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
	FrameLayout imgFrame;
	Topic currentTopic;
	String topicType = "";
	Canvas canvas = new Canvas();
	Paint mPaint = new Paint();
	Path path;
	ArrayList<Path> paths = new ArrayList<Path>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller.cachedOrders = "";
		controller.selectedUnits = "";
		setContentView(R.layout.activity_topic_details);
		imgFrame = (FrameLayout) findViewById(R.id.img_frame);
		pager = (ViewPager) findViewById(R.id.view_pager);
		setUpTopic();
		setUpClickListners();
		ImageAdapter adapter = new ImageAdapter(this, currentTopic);
		pager.setAdapter(adapter);

	}
	
	


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		int currentItem = pager.getCurrentItem();
		ImageAdapter adapter = new ImageAdapter(mContext, currentTopic);
		pager.setAdapter(adapter);
//		imgFrame.removeViewAt(1);
		pager.setCurrentItem(currentItem);
		
	}




	private void setUpTopic() {
		topicID = (Integer) getIntent().getExtras().get("topicID");
		topicType = (String) getIntent().getExtras().getString("topicType");
		Button ta2shera = (Button) findViewById(R.id.ta2shera);

		if (topicType.equalsIgnoreCase("khargy"))
			currentTopic = controller.khargy_topics.get(topicID);
		else
			currentTopic = controller.da5ly_topics.get(topicID);

		if (currentTopic.eSigned)
			ta2shera.setText("حذف التأشيرة");
	}

	private void setUpClickListners() {
		Button ta2shera = (Button) findViewById(R.id.ta2shera);
		ta2shera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
					putSignature();
				
			}
		});
		
		Button clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {	
					Reverter r = new Reverter();
					r.execute();
			}
		});
		
		Button commander_3ard = (Button) findViewById(R.id.commander_3ard);
		commander_3ard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
					putCommander3ard();
				
			}
		});
		
		
				
		

		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int pos) {
				if (imgFrame.getChildCount() > 1)
					imgFrame.removeViewAt(1);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		Button showHide = (Button) findViewById(R.id.show_hide);
		showHide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Button btn = (Button) arg0;
				RelativeLayout toolBox = (RelativeLayout) findViewById(R.id.tool_box);
				if (toolBox.getVisibility() == RelativeLayout.GONE) {
					toolBox.setVisibility(RelativeLayout.VISIBLE);
					btn.setBackgroundResource(R.drawable.down);
				}

				else {
					toolBox.setVisibility(RelativeLayout.GONE);
					btn.setBackgroundResource(R.drawable.up);
				}
			}
		});

		Button addUnits = (Button) findViewById(R.id.add_units);
		addUnits.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(mContext, UnitSelection.class);
				startActivity(intent);
			}
		});

		
		
		
		
		Button removeUnits = (Button) findViewById(R.id.remove_units);
		removeUnits.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				
				File dir = new File(Environment.getExternalStorageDirectory(),"navy");
				dir.mkdir();
				File PhotoDir = new File(dir, topicType);
				PhotoDir.mkdir();
				PhotoDir.setWritable(true);
				File topicFolder = new File(PhotoDir, currentTopic.title);
				topicFolder.mkdir();
				topicFolder.setWritable(true);
				
				String fileName = ApplicationController.getInstance().getFileName(pager.getCurrentItem() + 1);
				File newImage = new File(topicFolder, fileName);
				
				Intent editIntent = new Intent(mContext, EditTopic.class);
				editIntent.putExtra("path", newImage.getPath());
				startActivity(editIntent);

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

	private void putSignature() {
		Toast.makeText(mContext, "من فضلك ضع تأشيرة السيد رئيس اﻷركان", Toast.LENGTH_LONG).show();
		pager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				Exporter exporter = new Exporter(e,1);
				exporter.execute();
				return false;
			}
		});
	}
	
	
	
	private void putCommander3ard() {
		Toast.makeText(mContext, "عرض السيد القائد", Toast.LENGTH_LONG).show();
		pager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				Exporter exporter = new Exporter(e,0);
				exporter.execute();
				return false;
			}
		});
	}

	

	private String prepareSignature() {
		RelativeLayout tool_box = (RelativeLayout) findViewById(R.id.tool_box);
		String str = "";
		for (int i = 0; i < 2; i++) {
			LinearLayout container = (LinearLayout) tool_box.getChildAt(i);
			for (int j = 0; j < container.getChildCount(); j++) {
				if (container.getChildAt(j).getClass() == CheckBox.class){
					CheckBox c = (CheckBox) container.getChildAt(j);
					if (c.isChecked()) {
						str = str + c.getText() + "\n";
					}
				}
			}
		}
		controller.cachedOrders = str;
		return str;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		exportImage();
		finish();
		Intent intent = new Intent(mContext, Home.class);
		startActivity(intent);
	}

	private void exportImage() {
		try {

			/*
			 * Bitmap bmp = Bitmap.createBitmap(
			 * imgFrame.getLayoutParams().width,
			 * imgFrame.getLayoutParams().height, Bitmap.Config.ARGB_8888);
			 * Canvas c = new Canvas(bmp); imgFrame.layout(0, 0,
			 * imgFrame.getLayoutParams().width,
			 * imgFrame.getLayoutParams().height); imgFrame.draw(c);
			 */

			imgFrame.setDrawingCacheEnabled(true);
			imgFrame.buildDrawingCache();
			Bitmap bm = imgFrame.getDrawingCache();

			File dir = new File(Environment.getExternalStorageDirectory(),
					"navy");
			dir.mkdir();
			File PhotoDir = new File(dir, topicType);
			PhotoDir.mkdir();
			PhotoDir.setWritable(true);
			File topicFolder = new File(PhotoDir, currentTopic.title);
			topicFolder.mkdir();
			topicFolder.setWritable(true);
			String fileName = ApplicationController.getInstance().getFileName(pager.getCurrentItem() + 1);

			File newImage = new File(topicFolder,fileName);
			FileOutputStream out = new FileOutputStream(newImage);
			bm.compress(Bitmap.CompressFormat.JPEG, 50, out);

			imgFrame.destroyDrawingCache();

			// pager.getAdapter().notifyDataSetChanged();
		} catch (Exception e) {
			Log.d("helal", e.getMessage());
		}
	}

	private class Exporter extends AsyncTask<Void, Void, Void> {
		MotionEvent me;
		int type;

		public Exporter(MotionEvent me, int type) {
			this.me = me;
			this.type = type;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			int x = (int) me.getX();
			int y = (int) me.getY();

			LayoutInflater inflater = LayoutInflater.from(mContext);
			LinearLayout l;
			

			String preparedStr = "";
			
			if (type == 0 )
			{
				l = (LinearLayout) inflater.inflate(R.layout.custom_signature, null);	
			}
			else
			{
			l = (LinearLayout) inflater.inflate(R.layout.custom_signature2, null);	
			ImageView signature = (ImageView) l.getChildAt(1);
			TextView sigDate = (TextView) l.getChildAt(2);
			preparedStr = prepareSignature();
			signature.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
			Date d = new Date();
			String date = d.getDate() + "-" + (d.getMonth() + 1) + "-"
					+ (d.getYear() + 1900);
			date = controller.arabization(date);

			preparedStr = date + "\n" + preparedStr + "\n"
					+ controller.selectedUnits;
			sigDate.setText(preparedStr);

			}
			
			
			imgFrame.addView(l);
			
			Display display = getWindowManager().getDefaultDisplay();
			String[] lines = preparedStr.split("\n");
			int maxLength = 0;
			for (String s : lines) {
				if (s.length() > maxLength)
					maxLength = s.length();
			}
			Point p = controller.getXY(x, y, display, lines.length, maxLength);

			l.setX(p.x);
			l.setY(p.y);

			currentTopic.eSigned = true;
			String key = topicType + "_" + topicID;
			controller.signedHash.put(key, true);
			pager.setOnTouchListener(null);

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			int currentItem = pager.getCurrentItem();
			exportImage();
			ImageAdapter adapter = new ImageAdapter(mContext, currentTopic);
			pager.setAdapter(adapter);
			imgFrame.removeViewAt(1);
			pager.setCurrentItem(currentItem);
			//Button ta2shera = (Button) findViewById(R.id.ta2shera);
			//ta2shera.setText("حذف التأشيرة");

		}
	}

	private class Reverter extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			currentTopic.eSigned = false;
			String key = topicType + "_" + topicID;
			controller.signedHash.put(key, false);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			File dir = new File(Environment.getExternalStorageDirectory(),
					"navy");
			dir.mkdir();
			File PhotoDir = new File(dir, topicType);
			PhotoDir.mkdir();
			PhotoDir.setWritable(true);
			File topicFolder = new File(PhotoDir, currentTopic.title);
			topicFolder.mkdir();
			topicFolder.setWritable(true);
			while (topicFolder.list().length > 0)
				topicFolder.listFiles()[0].delete();

			File backup_dir = new File(
					Environment.getExternalStorageDirectory(), "navy_backup");
			backup_dir.mkdir();
			File backup_PhotoDir = new File(backup_dir, topicType);
			backup_PhotoDir.mkdir();
			backup_PhotoDir.setWritable(true);
			File backup_topicFolder = new File(backup_PhotoDir,
					currentTopic.title);
			backup_topicFolder.mkdir();

			try {
				controller.copy(backup_topicFolder, topicFolder);
			} catch (Exception e) {
				Log.d("helal", e.getMessage());
			}

			int currentItem = pager.getCurrentItem();
			ImageAdapter adapter = new ImageAdapter(mContext, currentTopic);
			pager.setAdapter(adapter);
			pager.setCurrentItem(currentItem);
			Button ta2shera = (Button) findViewById(R.id.ta2shera);
			ta2shera.setText("تأشيرة السيد رئيس اﻷركان");
		}

	}

}
