package com.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bugsense.trace.BugSense;
import com.bugsense.trace.BugSenseHandler;
import com.core.CacheManager;
import com.core.Constants;
import com.core.Time2FlyApp;
import com.core.Utils;
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
	
	CacheManager cache = CacheManager.getInstance();
	LinearLayout drawer;
	LatLng hkLatLng = new LatLng(22.3089, 113.9144);
	Location hkLoc = new Location("t2f");
	
	float weather_transparency;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        BugSenseHandler.initAndStartSession(mContext, "c417ebfa");

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
			Log.d(Constants.TAG, "rate : " + cache.update_rate);
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
		BugSenseHandler.closeSession(this);
		finish();
	};
	

	private void initGoogleMap(){
		
		googleMap = ((SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.setMyLocationEnabled(true);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hkLatLng, 9));
		
		hkLoc.setLatitude(hkLatLng.latitude);
		hkLoc.setLongitude(hkLatLng.longitude);
		
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
					boolean renderWeather = false;
					if (cache.zoom <= 9 && position.zoom >9)
						renderWeather = true;
					
					else if (cache.zoom <= 11 && ( position.zoom > 11 || position.zoom <= 9))
						renderWeather = true;
					
					if (cache.zoom > 11 && position.zoom <= 11)
						renderWeather = true;
					cache.zoom = position.zoom;
					if (renderWeather)
						addWeatherOverlay();
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
				if(layoutIndex > -1 )
				{
					LinearLayout selectedLayout = (LinearLayout)drawer.getChildAt(layoutIndex);
					selectedLayout.setBackgroundResource(R.drawable.rounded_border_yellow);
					cache.current_key = (String)selectedLayout.getTag();
				}
				return false;
			}
		});
		
		addWeatherOverlay();
		
		
	}
	
	private void renderTargets(){
		googleMap.clear();
		drawer.removeAllViews();
		addWeatherOverlay();
		
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
			
		case R.id.settings :
			timer.cancel();
			finish();
			Intent settings = new Intent(mContext, Settings.class);
			startActivity(settings);
			break;
			
		case R.id.side_panel:
			View parent = ((View)drawer.getParent());
			if(parent.getVisibility() == View.GONE)
				parent.setVisibility(View.VISIBLE);
			else
				parent.setVisibility(View.GONE);
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
		
		LatLng latLng = new LatLng(t.lat, t.lon);
		Location loc = new Location("t2f");
		loc.setLatitude(latLng.latitude);
		loc.setLongitude(latLng.longitude);
		loc.setBearing(t.track);
		float bearingAngle = loc.bearingTo(hkLoc);
		float distance = loc.distanceTo(hkLoc)/1000;
		TextView tv2 = (TextView)list_item.getChildAt(2);
		tv2.setText("Direction : "+bearingAngle+"\nDistance : "+distance + " Km" );
		
		
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
		if (!appInstance.isWeatheroverlayEnabled())
			return ;
		
		if (cache.weatherOverlay !=null)
			cache.weatherOverlay.remove();
		LatLng southwest = null;
		LatLng northeast = null;
		
		if(cache.zoom <= 9)
		{
			southwest = new LatLng(20.00107, 111.68321);
			northeast = new LatLng(24.60560, 116.66013);
		}

		if(cache.zoom <= 11)
		{		
		southwest = new LatLng(21.15220, 112.92745);
		northeast = new LatLng(23.45446, 115.41589);	
		}
		
		else
		{
			southwest = new LatLng(21.72777, 113.54956);
			northeast = new LatLng(22.87890, 114.79378);
		}
		
		float transparency = appInstance.getWeatherOverlayTransparency();
		
		LatLngBounds bounds = new LatLngBounds(southwest, northeast);
		cache.weatherOverlay = googleMap.addGroundOverlay(new GroundOverlayOptions()
									.positionFromBounds(bounds)
									.transparency(transparency)
									.image(BitmapDescriptorFactory.fromResource(R.drawable.weather)));
	}		
	

	
	
}
