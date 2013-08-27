package navymail.UI;

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
		KhargyGridViewAdapter adapter = new KhargyGridViewAdapter(this);
		GridView da5ly_grid = (GridView)findViewById(R.id.da5ly_grid);
		da5ly_grid.setAdapter(adapter);
		
		
		TextView total_counter = (TextView)findViewById(R.id.total_counter);
		total_counter.setText("Total : "+ app.khargy_topics.size());
		int finished = 0;
		for (int i = 0 ; i < app.khargy_topics.size() ; i ++)
		{
			if(app.khargy_topics.get(i).eSigned)
				finished ++;
		}
		TextView finished_counter = (TextView)findViewById(R.id.finished_counter);
		finished_counter.setText("Finished : "+ finished);

		TextView remain_counter = (TextView)findViewById(R.id.remain_counter);
		remain_counter.setText("Remain : " + (app.khargy_topics.size() - finished));
	}

}
