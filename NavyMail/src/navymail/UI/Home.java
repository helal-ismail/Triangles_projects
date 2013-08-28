package navymail.UI;

import navymail.adapters.Da5lyGridViewAdapter;
import navymail.adapters.KhargyGridViewAdapter;
import navymail.core.ApplicationController;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Home extends TabActivity{
	ApplicationController appController = ApplicationController.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		TabHost tabHost = getTabHost();
        TabSpec da5lySpec = tabHost.newTabSpec("المكاتبات الداخلية");
        da5lySpec.setIndicator("المكاتبات الداخلية");
        Intent da5lyIntent = new Intent(this, Da5lyActivity.class);
        da5lySpec.setContent(da5lyIntent);
         
        TabSpec khargySpec = tabHost.newTabSpec("المكاتبات الخارجية");
        khargySpec.setIndicator("المكاتبات الخارجية");
        Intent khargyIntent = new Intent(this, KhargyActivity.class);
        khargySpec.setContent(khargyIntent);
              
        tabHost.addTab(da5lySpec); 
        tabHost.addTab(khargySpec);
        
        
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(23);
        }
		
	}
	
	public void prepare(){
		Da5lyGridViewAdapter adapter = new Da5lyGridViewAdapter(this);
		GridView da5ly_grid = (GridView)findViewById(R.id.da5ly_grid);
		da5ly_grid.setAdapter(adapter);
		
		KhargyGridViewAdapter adapter2 = new KhargyGridViewAdapter(this);
		GridView khargy_grid = (GridView)findViewById(R.id.khargy_grid);
		khargy_grid.setAdapter(adapter2);
	}
	
	
	

}
