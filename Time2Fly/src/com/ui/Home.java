package com.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.core.CacheManager;
import com.core.Time2FlyApp;
import com.core.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.modules.Tab;
import com.network.GetDataTask;

public class Home extends FragmentActivity {
	Time2FlyApp appInstance;
	Context mContext = this;
	int update_rate = 10000;
	int bearing_angle = 0;
	boolean started = false;
	Timer timer = new Timer();
	GoogleMap googleMap;
	Location myLoc = null ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		appInstance = (Time2FlyApp) getApplication();
		initGoogleMap();
		
		Toast.makeText(mContext, "Loading flights data", Toast.LENGTH_LONG).show();
		timer.scheduleAtFixedRate(refreshVals, 0, update_rate);
		timer.scheduleAtFixedRate(refreshMap, 5000, update_rate + 2000);

	}
	

	Runnable r0 = new Runnable() {
		@Override
		public void run() {
			GetDataTask task = new GetDataTask();
			task.execute();
			update_rate = appInstance.getResponseVals().update_rate * 1000;
		}
	};

	TimerTask refreshVals = new TimerTask() {
		@Override
		public void run() {
			runOnUiThread(r0);
		}

	};

	Runnable r = new Runnable() {
		@Override
		public void run() {
			renderTargets();
		}
	};
	TimerTask refreshMap = new TimerTask() {
		@Override
		public void run() {
			runOnUiThread(r);
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
		renderTargets();
		
		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {	
			@Override
			public void onCameraChange(CameraPosition position) {
				
				int new_bearing_angle = (int)position.bearing;
				if (new_bearing_angle != bearing_angle){
					bearing_angle = new_bearing_angle;
					renderTargets();
				}
			}
		});
		
	}
	
	private void renderTargets(){
		googleMap.clear();
		//setMyLocationMarker();

		HashMap hash = CacheManager.getInstance().tabs_hash;
		Iterator itr = hash.keySet().iterator();

		while (itr.hasNext()) {
			String key = (String) itr.next();
			Tab t = (Tab) hash.get(key);
			LatLng latLng = new LatLng(t.lat, t.lon);
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), Utils.getInstance().getResourceID(t));

			bmp = Utils.getInstance().rotateImage(bmp, t.track, bearing_angle);
			
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
			
			googleMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title("Flight : " +t.callSign)
					.snippet(
							"Altitude : " + flightLevel + " - " +
							"Ground Speed : " +t.spd +"Kts")
					.icon(BitmapDescriptorFactory
							.fromBitmap(bmp)));
			if (!started)
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						latLng, 11));
			
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
	
	
	private void setMyLocationMarker(){
		try{
		if(myLoc == null){
			myLoc = googleMap.getMyLocation();
			if(myLoc == null)
			{
				Toast.makeText(mContext, "Couldnt capture current location", Toast.LENGTH_LONG).show();
				return;
			}
		}
		LatLng pos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
		googleMap.addMarker(new MarkerOptions()
		.position(pos)
		.title("Current location")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
		
		googleMap.addCircle(new CircleOptions()
		.center(pos)
		.fillColor(Color.TRANSPARENT)
		.radius(35)
		.strokeColor(Color.BLUE)
		.strokeWidth(3));
		}
		catch(Exception e){
			Toast.makeText(mContext, "E: "+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
}
