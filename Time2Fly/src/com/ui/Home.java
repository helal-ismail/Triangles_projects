package com.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.widget.Toast;

import com.core.CacheManager;
import com.core.Time2FlyApp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.modules.Tab;

public class Home extends Activity {
	Time2FlyApp appInstance;
	Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		appInstance = (Time2FlyApp) getApplication();
		appInstance.init();
		// int status =
		// GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		// Log.d(Constants.TAG, "Status : " + status);

		final Runnable r = new Runnable() {
			@Override
			public void run() {
				GoogleMap googleMap;
				googleMap = ((MapFragment) (getFragmentManager()
						.findFragmentById(R.id.map))).getMap();
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				googleMap.getUiSettings().setCompassEnabled(true);
				googleMap.getUiSettings().setZoomControlsEnabled(true);
				googleMap.clear();
				HashMap hash = CacheManager.getInstance().tabs_hash;
				Iterator itr = hash.keySet().iterator();

				while (itr.hasNext()) {
					String key = (String) itr.next();
					Tab t = (Tab) hash.get(key);
					LatLng latLng = new LatLng(t.lat, t.lon);
					
					
					googleMap
							.addMarker(new MarkerOptions()
									.position(latLng)
									.title(t.addr)
									.snippet(t.code)
									.icon(BitmapDescriptorFactory.fromResource(R.drawable.airbus_a320)));


					googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
							latLng, 1));
					
				}
			}
		};

		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(r);
			}
		};
		
		Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(t, 12000, 10000);
		
	}
	
	

}
