package navymail.UI;

import navymail.UI2.R;
import navymail.adapters.Da5lyGridViewAdapter;
import navymail.adapters.KhargyGridViewAdapter;
import navymail.core.ApplicationController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class Da5lyActivity extends Activity{
	ApplicationController app = ApplicationController.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_da5ly);
		Da5lyGridViewAdapter adapter = new Da5lyGridViewAdapter(this);
		GridView da5ly_grid = (GridView)findViewById(R.id.da5ly_grid);
		da5ly_grid.setAdapter(adapter);
		
		
		TextView total_counter = (TextView)findViewById(R.id.total_counter);
		total_counter.setText("إجمالــــــى : عدد "+ app.arabization(app.da5ly_topics.size()+"") + " مكاتبة");
	
		int finished = 0;
		for (int i = 0 ; i < app.da5ly_topics.size() ; i ++)
		{
			if(app.da5ly_topics.get(i).eSigned)
				finished ++;
		}
		TextView finished_counter = (TextView)findViewById(R.id.finished_counter);
		finished_counter.setText("تم مراجعة : عدد "+ app.arabization(finished+"")+ " مكاتبة");

		TextView remain_counter = (TextView)findViewById(R.id.remain_counter);
		remain_counter.setText("المتبقــى : عدد " + app.arabization((app.da5ly_topics.size() - finished)+"")+ " مكاتبة");
	}

}
