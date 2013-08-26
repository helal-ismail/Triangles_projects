package com.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.core.CacheManager;
import com.core.Constants;
import com.core.Time2FlyApp;
import com.core.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.modules.Tab;
import com.network.GetDataTask;

public class Home extends FragmentActivity  {
	Time2FlyApp appInstance;
	Context mContext = this;
	int bearing_angle = 0;
	boolean started = false;
	Timer timer = new Timer();
	GoogleMap googleMap;
	Location myLoc = null ;
	CacheManager cache = CacheManager.getInstance();
	LinearLayout drawer;
	LatLng hkLatLng = new LatLng(22.2783, 114.1589);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initActionBar();
        

		drawer = (LinearLayout)findViewById(R.id.drawer);
		appInstance = (Time2FlyApp) getApplication();
		initGoogleMap();
		Toast.makeText(mContext, "Loading flights data", Toast.LENGTH_LONG).show();
		
		timer.scheduleAtFixedRate(refreshVals, 0, cache.update_rate);
		timer.scheduleAtFixedRate(refreshMap, 5000, cache.update_rate + 2000);
		

	}
	

	Runnable refreshValsRunnable = new Runnable() {
		@Override
		public void run() {
			GetDataTask task = new GetDataTask();
			task.execute();
		}
	};

	TimerTask refreshVals = new TimerTask() {
		@Override
		public void run() {
			runOnUiThread(refreshValsRunnable);
		}
	};

	Runnable refreshMapRunnable = new Runnable() {
		@Override
		public void run() {
			renderTargets();
		}
	};
	TimerTask refreshMap = new TimerTask() {
		@Override
		public void run() {
			runOnUiThread(refreshMapRunnable);
		}
	};
	

	@Override
	public void onBackPressed() {
		timer.cancel();
		finish();
	};
	

	private void initGoogleMap(){
		
		googleMap = ((SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.setMyLocationEnabled(true);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hkLatLng, 8));
		
		
		renderTargets();
		
		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {	
			@Override
			public void onCameraChange(CameraPosition position) {
				int new_bearing_angle = (int)position.bearing;
				if (new_bearing_angle != bearing_angle){
					bearing_angle = new_bearing_angle;
					renderTargets();
				}
				
				if(position.zoom != cache.zoom){
					cache.zoom = position.zoom;					
				}
				
			}
		});
		
		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				String title = marker.getTitle();
				for(int i = 0 ; i < drawer.getChildCount() ; i ++)
					drawer.getChildAt(i).setBackgroundResource(R.drawable.rounded_border);
				
				int layoutIndex = Utils.getInstance().searchByTitle(title);
				LinearLayout selectedLayout = (LinearLayout)drawer.getChildAt(layoutIndex);
				selectedLayout.setBackgroundResource(R.drawable.rounded_border_yellow);
				selectedLayout.requestFocus();
				cache.current_key = (String)selectedLayout.getTag();
				return false;
			}
		});
		
		addWeatherOverlay();
		
		
	}
	
	private void renderTargets(){
		clearMap();
		drawer.removeAllViews();		
		HashMap hash = CacheManager.getInstance().tabs_hash;
		Iterator itr = hash.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			Tab t = (Tab) hash.get(key);
			boolean isActive;
			Date now = new Date();
			long timeDiff = now.getTime() - t.timeStamp.getTime();
			isActive = (timeDiff < Constants.TS_THRESHOLD);
			if(timeDiff > Constants.TS_REMOVE)
				continue;
					
			LatLng latLng = new LatLng(t.lat, t.lon);
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), Utils.getInstance().getResourceID(t,isActive));
			bmp = Utils.getInstance().rotateImage(bmp, t.track, bearing_angle);
			BitmapDrawable d = new BitmapDrawable(bmp);
			d.setAlpha(50);
			Bitmap b = d.getBitmap();
			
			float alt = ((int)t.alt)/100;
			int altitude = Math.round(alt);
			String flightLevel = "";
			if(alt > 100){
				flightLevel = "FL" + altitude;
			}
			else
			{
				flightLevel = "A0"+altitude;
			}
			

			
			t.marker = googleMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title(t.callSign)
					
					.snippet(
							"Altitude : " + flightLevel + " - " +
							"Ground Speed : " +t.spd +"Kts")
					.icon(BitmapDescriptorFactory
							.fromBitmap(b)));
			
			
			
			hash.put(key, t);
			addFlightTab(t,isActive);

			started = true;
		}
		
		//setMyLocationMarker();
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		
		case R.id.normal:
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
			
		case R.id.sat:
			googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
			
		case R.id.ter:
			googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	
	private void addFlightTab(Tab t, boolean isActive){
		LayoutInflater inflater = LayoutInflater.from(mContext);
		LinearLayout list_item = (LinearLayout)inflater.inflate(R.layout.custom_list_item, null);
		
		if(!isActive)
			list_item.setBackgroundResource(R.drawable.rounded_border_red);
		if (t.reg.equalsIgnoreCase(cache.selectedReg))
			list_item.setBackgroundResource(R.drawable.rounded_border_yellow);
		
		TextView tv0 = (TextView)list_item.getChildAt(0);
		tv0.setText(t.callSign);
		TextView tv1 = (TextView)list_item.getChildAt(1);
		tv1.setText( t.spd + " Kts");
		final float lat = t.lat;
		final float lon = t.lon;
		final String key = t.reg;
		final Marker marker = t.marker;
		list_item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				cache.current_key = key;
				for (int i = 0 ; i < drawer.getChildCount() ; i ++){
					LinearLayout childLayout = (LinearLayout)drawer.getChildAt(i);
					childLayout.setBackgroundResource(R.drawable.rounded_border);

				}
				LinearLayout clickedLayout = (LinearLayout)view;
				clickedLayout.setBackgroundResource(R.drawable.rounded_border_yellow);
				
				LatLng latLng = new LatLng(lat, lon);
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
				
				if(marker != null)
					marker.showInfoWindow();
				else
					Toast.makeText(mContext, "NULL", 3000).show();
				
				cache.selectedReg = key;
				
				
			}
		});
		list_item.setId(t.reg.hashCode());
		list_item.setTag(key);
		drawer.addView(list_item);
		
	
	}
	
	private void initActionBar(){
		ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
	}


	

	
	private void addWeatherOverlay(){
		if (cache.weatherOverlay !=null)
			cache.weatherOverlay.remove();
		LatLng southwest = null;
		LatLng northeast = null;
		
		if(cache.zoom <= 8)
		{
			southwest = new LatLng(20.00107, 111.68321);
			northeast = new LatLng(24.60560, 116.66013);
		}

		if(cache.zoom <= 10)
		{		
		southwest = new LatLng(21.15220, 112.92745);
		northeast = new LatLng(23.45446, 115.41589);	
		}
		
		else
		{
			southwest = new LatLng(21.72777, 113.54956);
			northeast = new LatLng(22.87890, 114.79378);
		}
		float transparency = (float) 0.5;
		
		LatLngBounds bounds = new LatLngBounds(southwest, northeast);
		cache.weatherOverlay = googleMap.addGroundOverlay(new GroundOverlayOptions()
									.positionFromBounds(bounds)
									.transparency(transparency)
									.image(BitmapDescriptorFactory.fromResource(R.drawable.weather)));
	}		
	

	private void clearMap(){
		Iterator itr = cache.tabs_hash.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			Tab t = (Tab) cache.tabs_hash.get(key);
			if(t.marker != null)
				t.marker.remove();
			t.marker = null;
		}
	}
	
}
