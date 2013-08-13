package navymail.UI;

import navymail.adapters.Da5lyGridViewAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class Da5lyActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_da5ly);
		Da5lyGridViewAdapter adapter = new Da5lyGridViewAdapter(this);
		GridView da5ly_grid = (GridView)findViewById(R.id.da5ly_grid);
		da5ly_grid.setAdapter(adapter);
	
	}

}
