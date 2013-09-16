package navymail.adapters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import navymail.UI2.R;
import navymail.UI.TopicDetails;
import navymail.core.ApplicationController;
import navymail.modules.Topic;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Da5lyGridViewAdapter extends BaseAdapter {

	Context mContext;
	ApplicationController app = ApplicationController.getInstance();
	
	public Da5lyGridViewAdapter(Context c) {
		mContext = c;
		prepareContent();
	}
	@Override
	public int getCount() {
		return app.da5ly_topics.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		final Topic topic = app.da5ly_topics.get(arg0);

		LayoutInflater inflater = LayoutInflater.from(mContext);
		RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.custom_topic_icon3, null);
		RelativeLayout container = (RelativeLayout)layout.getChildAt(1);
		TextView title = (TextView)container.getChildAt(0);
		title.setText(topic.title);
		
		if(topic.eSigned)
		{
			FrameLayout fl = (FrameLayout)layout.getChildAt(0);
			ImageView imgV = (ImageView)fl.getChildAt(1);
			imgV.setVisibility(View.VISIBLE);
		}
		
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent topicDetails = new Intent(mContext, TopicDetails.class);
				topicDetails.putExtra("topicID", topic.id);
				topicDetails.putExtra("topicType", "da5ly");
				mContext.startActivity(topicDetails);
				((Activity)mContext).finish();
			}
		});
		return layout;
		
	}
	
	private void prepareContent(){
		app.da5ly_topics.clear();
		File dir = new File(Environment.getExternalStorageDirectory(),"navy");
		dir.mkdir();
		File photoDir = new File(dir,"da5ly");
		photoDir.mkdir();
		File[] topicFolders = photoDir.listFiles();	
		Arrays.sort(topicFolders);
		for (int i = 0 ; i < topicFolders.length ; i ++)
		{
			ArrayList<String> imageUrls = new ArrayList<String>();
			int id = i;
			String title = topicFolders[i].getName();
			String conclusion = "";
			File[] urls = topicFolders[i].listFiles();
			Arrays.sort(urls);
			for (int j = 0; j < urls.length; j++) {
				if (urls[j].getPath().contains(".jp"))
					imageUrls.add(urls[j].getPath());
			}
			
			boolean eSigned = false;
			Boolean b = (Boolean)app.signedHash.get("da5ly"+"_"+id);
			if (b != null && b == true)
				eSigned = true;
			
			Topic topic = new Topic(id, title, conclusion, imageUrls, eSigned);
			app.da5ly_topics.add(topic);
		}
	}
	

}
