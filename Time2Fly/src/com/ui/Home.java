package com.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.core.CacheManager;
import com.core.Constants;
import com.core.Time2FlyApp;
import com.core.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.modules.Tab;
import com.network.GetDataTask;

public class Home extends FragmentActivity {
	Time2FlyApp appInstance;
	Context mContext = this;
	int update_rate = 10000;
	boolean started = false;
	Timer timer = new Timer();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		appInstance = (Time2FlyApp) getApplication();

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
			GoogleMap googleMap;

			googleMap = ((SupportMapFragment) (getSupportFragmentManager()
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

				Bitmap bmp = BitmapFactory.decodeResource(getResources(), Utils.getInstance().getResourceID(t.reg));
				bmp = Utils.getInstance().rotateImage(bmp, t.track);
				
				googleMap.addMarker(new MarkerOptions()
						.position(latLng)
						.title("Flight : " +t.callSign)
						.snippet(
								"Altitude:" + (int)t.alt +"feet - "+
								"Ground Speed:" +t.spd +"Mph")
						.icon(BitmapDescriptorFactory
								.fromBitmap(bmp)));
				if (!started)
					googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
							latLng, 11));
				
				started = true;
			}
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
	

}
