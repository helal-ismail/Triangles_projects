package navymail.adapters;

import java.util.ArrayList;

import navymail.UI.R;
import navymail.UI.TopicDetails;
import navymail.modules.Topic;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

public class KhargyGridViewAdapter extends BaseAdapter{

	Context mContext;
	ArrayList<Topic> topics = new ArrayList<Topic>();

	
	public KhargyGridViewAdapter(Context c) {
		mContext = c;
	}
	@Override
	public int getCount() {
		return 21;
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
		LayoutInflater inflater = LayoutInflater.from(mContext);
		RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.custom_topic_icon3, null);
		//final Topic topic = topics.get(arg0);
		
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent topicDetails = new Intent(mContext, TopicDetails.class);
			//	topicDetails.putExtra("topicID", topic.id);
				topicDetails.putExtra("topicID", 15);
				mContext.startActivity(topicDetails);
			}
		});
		return layout;
		
	}
	

}
