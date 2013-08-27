package navymail.adapters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import navymail.UI.R;
import navymail.UI.TopicDetails;
import navymail.core.ApplicationController;
import navymail.modules.Topic;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class KhargyGridViewAdapter extends BaseAdapter{

	Context mContext;
	//ArrayList<Topic> topics = new ArrayList<Topic>();
	ApplicationController app = ApplicationController.getInstance();
	
	public KhargyGridViewAdapter(Context c) {
		mContext = c;
		prepareContent();
	}
	@Override
	public int getCount() {
		return app.khargy_topics.size();
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
		final Topic topic = app.khargy_topics.get(arg0);

		LayoutInflater inflater = LayoutInflater.from(mContext);
		RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.custom_topic_icon3, null);
		RelativeLayout container = (RelativeLayout)layout.getChildAt(1);
		TextView title = (TextView)container.getChildAt(0);
		title.setText(topic.title);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent topicDetails = new Intent(mContext, TopicDetails.class);
				topicDetails.putExtra("topicID", topic.id);
				mContext.startActivity(topicDetails);
			}
		});
		return layout;
		
	}
	
	private void prepareContent(){
		app.khargy_topics.clear();
		ArrayList<String> imageUrls = new ArrayList<String>();
		File PhotoDir = new File(Environment.getExternalStorageDirectory(),"navy");
		PhotoDir.mkdir();
		File[] topicFolders = PhotoDir.listFiles();	
		Arrays.sort(topicFolders);
		for (int i = 0 ; i < topicFolders.length ; i ++)
		{
			int id = i;
			String title = topicFolders[i].getName();
			String conclusion = "";
			File[] urls = topicFolders[i].listFiles();
			Arrays.sort(urls);
			for (int j = 0; j < urls.length; j++) {
				if (urls[j].getPath().contains(".jp"))
					imageUrls.add(urls[j].getPath());
			}
			
			Topic topic = new Topic(id, title, conclusion, imageUrls, false);
			app.khargy_topics.add(topic);
		}
	}
	

}
