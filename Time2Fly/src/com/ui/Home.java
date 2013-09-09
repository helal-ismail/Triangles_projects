package com.ui;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.network.GetWeatherOvelay;
import com.network.MyLocationListener;

public class Home extends FragmentActivity {
	Time2FlyApp appInstance;
	Context mContext = this;
	int bearing_angle = 0;
	boolean started = false;
	Timer timer = new Timer();
	public int round_robin = 0;
	GoogleMap googleMap;
	Bitmap weather_bmp;
	CacheManager cache = CacheManager.getInstance();
	LinearLayout drawer;
	LatLng hkLatLng = new LatLng(22.3089, 113.9144);
	boolean weatherPlayed = true;
	float weather_transparency;
	
	Runnable refreshValsRunnable = new Runnable() {
		@Override
		public void run() {
			NetworkTask task = new NetworkTask();
			task.execute();
		}
	};

	TimerTask weatherTask = new TimerTask() {
		@Override
		public void run() {
			WeatherTask task = new WeatherTask();
			task.execute();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//======= Init App =======
		super.onCreate(savedInstanceState);
		BugSenseHandler.initAndStartSession(mContext, "c417ebfa");
		setContentView(R.layout.activity_home);
		cache.tabs_hash.clear();
		initActionBar();
		drawer = (LinearLayout) findViewById(R.id.drawer);
		drawer.removeAllViews();
		appInstance = (Time2FlyApp) getApplication();
		initGoogleMap();
		cache.cyclesCount = 0;
		Toast.makeText(mContext, "Loading flights data", Toast.LENGTH_LONG).show();
		
		// Call the 1st JSON Update
		runOnUiThread(refreshValsRunnable);
		
		// Init Weather Task
		if (appInstance.isWeatheroverlayEnabled()) {
			timer.schedule(weatherTask, 0, 12 * 60 * 1000);
		}

	}

	@Override
	public void onBackPressed() {
		weatherTask.cancel();
		timer.cancel();
		timer.purge();
		BugSenseHandler.closeSession(this);
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	};

	private void initGoogleMap() {
		googleMap = ((SupportMapFragment) (getSupportFragmentManager()
				.findFragmentById(R.id.map))).getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.setMyLocationEnabled(true);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hkLatLng, 9));
		
		cache.currentLoc.setLatitude(hkLatLng.latitude);
		cache.currentLoc.setLongitude(hkLatLng.longitude);

		Location myLoc = getCurrentLocation();
		if(myLoc != null && !appInstance.isHomeHK()){
			Log.d(Constants.TAG, myLoc.getLatitude()+" - "+myLoc.getLongitude());
			cache.currentLoc = myLoc;
		}
		
		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition position) {
				int new_bearing_angle = (int) position.bearing;
				if (new_bearing_angle != bearing_angle) {
					bearing_angle = new_bearing_angle;
					renderTargets();
				}

				if (position.zoom != cache.zoom) {
					boolean renderWeather = false;
					if (cache.zoom <= 9 && position.zoom > 9)
						renderWeather = true;

					else if (cache.zoom <= 11
							&& (position.zoom > 11 || position.zoom <= 9))
						renderWeather = true;

					if (cache.zoom > 11 && position.zoom <= 11)
						renderWeather = true;
					cache.zoom = position.zoom;

				}

			}
		});

		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				String title = marker.getTitle();
				for (int i = 0; i < drawer.getChildCount(); i++)
					drawer.getChildAt(i).setBackgroundResource(
							R.drawable.rounded_border);

				//int layoutIndex = Utils.getInstance().searchByTitle(title);
				int layoutIndex = cache.tabs_hash.searchByTitle(title);
				if (layoutIndex > -1) {
					LinearLayout selectedLayout = (LinearLayout) drawer
							.getChildAt(layoutIndex);
					if (selectedLayout != null) {
						selectedLayout
								.setBackgroundResource(R.drawable.rounded_border_yellow);
					}
				}
				return false;
			}
		});

	}

	private void renderTargets() {
		Object[] tabs = cache.tabs_hash.exportSortedList();
		
		drawer.removeAllViews();
		for(int i = 0 ; i < tabs.length ; i ++)
		{
			Tab tab = (Tab)tabs[i];
			boolean isActive = false;
			isActive = ((cache.cyclesCount - tab.cycles) < 2);
			if ((cache.cyclesCount - tab.cycles) > 3) {
				if (tab.marker != null)
					tab.marker.remove();
				cache.tabs_hash.remove(tab.addr);
				continue;
			}

			LatLng latLng = new LatLng(tab.lat, tab.lon);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), Utils
					.getInstance().getResourceID(tab, isActive));
			bmp = Utils.getInstance()
					.rotateImage(bmp, tab.track, bearing_angle);

			float alt = ((int) tab.alt) / 100;
			int altitude = Math.round(alt);
			String flightLevel = "";
			if (alt > 100) {
				flightLevel = "FL" + altitude;
			} else {
				flightLevel = "A0" + altitude;
			}

			Location loc = new Location("t2f");
			loc.setLatitude(latLng.latitude);
			loc.setLongitude(latLng.longitude);
			// loc.setBearing(t.track);

			float bearingAngle = cache.currentLoc.bearingTo(loc);
			if (bearingAngle < 0)
				bearingAngle = bearingAngle + 360;

			String direction = Utils.getInstance().getDirectionFromAngle(
					bearingAngle);

			float distance = loc.distanceTo(cache.currentLoc) / 1000;
			distance = (float) (Math.round(distance * 20.0) / 20.0);
			String snippet = distance + "Km | " + direction + " | " + tab.spd
					+ "Kts";

			if (tab.marker == null) {
				tab.marker = googleMap.addMarker(new MarkerOptions()
						.position(latLng)
						.title(tab.callSign + " | " + flightLevel)
						.snippet(snippet)
						.icon(BitmapDescriptorFactory.fromBitmap(bmp)));
			} else {
				LatLng org = new LatLng(tab.xLat, tab.xLon);
				LatLng dest = new LatLng(tab.lat, tab.lon);
				animateMarker(tab, org, dest, snippet, flightLevel, isActive);
			}
			if (cache.selectedReg.equalsIgnoreCase(tab.addr)) {
				tab.marker.showInfoWindow();
			}

			addFlightTab(tab, isActive, String.valueOf(distance)+"Km");
		}
	}

	private void animateMarker(Tab tab, final LatLng org, final LatLng dest,
			String snippet, String flightLevel, boolean isActive) {

		final Marker marker = tab.marker;
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), Utils
				.getInstance().getResourceID(tab, isActive));
		bmp = Utils.getInstance().rotateImage(bmp, tab.track, bearing_angle);
		marker.setTitle(tab.callSign + " | " + flightLevel);
		marker.setSnippet(snippet);
		marker.setIcon(BitmapDescriptorFactory.fromBitmap(bmp));

		final Handler handler = new Handler();
		final long start = SystemClock.uptimeMillis();
		final long duration = 500;
		final Interpolator interpolator = new LinearInterpolator();
		handler.post(new Runnable() {
			@Override
			public void run() {
				long elapsed = SystemClock.uptimeMillis() - start;
				float t = interpolator.getInterpolation((float) elapsed
						/ duration);
				double lng = t * dest.longitude + (1 - t) * org.longitude;
				double lat = t * dest.latitude + (1 - t) * org.latitude;
				marker.setPosition(new LatLng(lat, lng));
				if (t < 1.0) {
					// Post again 16ms later.
					handler.postDelayed(this, 16);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.normal:
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;

		case R.id.sat:
			googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;

		case R.id.ter:
			googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;

		case R.id.settings:
			timer.cancel();
			finish();
			Intent settings = new Intent(mContext, Settings.class);
			startActivity(settings);
			break;

		case R.id.side_panel:
			View parent = ((View) drawer.getParent());
			if (parent.getVisibility() == View.GONE)
				parent.setVisibility(View.VISIBLE);
			else
				parent.setVisibility(View.GONE);
			break;

		case R.id.play:
			if (weatherPlayed) {
				weatherPlayed = false;
				Bitmap b = BitmapFactory.decodeResource(getResources(),
						R.drawable.play);
				BitmapDrawable d = new BitmapDrawable(b);
				item.setIcon(d);

			} else {
				weatherPlayed = true;
				Bitmap b = BitmapFactory.decodeResource(getResources(),
						R.drawable.pause);
				BitmapDrawable d = new BitmapDrawable(b);
				item.setIcon(d);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void addFlightTab(Tab t, boolean isActive, String distance) {

		LayoutInflater inflater = LayoutInflater.from(mContext);
		LinearLayout list_item = (LinearLayout) inflater.inflate(
				R.layout.custom_list_item, null);

		if (t.addr.equalsIgnoreCase(cache.selectedReg))
			list_item.setBackgroundResource(R.drawable.rounded_border_yellow);

		TextView tv0 = (TextView) list_item.getChildAt(0);
		tv0.setText(t.callSign);

		TextView tv1 = (TextView) list_item.getChildAt(1);
		tv1.setText(distance);

		final float lat = t.lat;
		final float lon = t.lon;
		final String key = t.reg;
		final String addr = t.addr;
		final Marker marker = t.marker;
		list_item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// cache.current_key = key;

				LinearLayout item = (LinearLayout) view;
				if (cache.selectedReg.equalsIgnoreCase(addr)) {
					item.setBackgroundResource(R.drawable.rounded_border);
					cache.selectedReg = "";
					marker.hideInfoWindow();
					return;

				}

				for (int i = 0; i < drawer.getChildCount(); i++) {
					LinearLayout childLayout = (LinearLayout) drawer
							.getChildAt(i);
					childLayout
							.setBackgroundResource(R.drawable.rounded_border);
				}
				LinearLayout clickedLayout = (LinearLayout) view;
				clickedLayout
						.setBackgroundResource(R.drawable.rounded_border_yellow);

				LatLng latLng = new LatLng(lat, lon);
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						latLng, 11));

				if (marker != null)
					marker.showInfoWindow();
				else
					Toast.makeText(mContext, "NULL", 3000).show();

				cache.selectedReg = addr;

			}
		});
		list_item.setId(t.reg.hashCode());
		list_item.setTag(key);
		drawer.addView(list_item);

	}

	private void initActionBar() {
		ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
	}

	private Location getCurrentLocation() {
	
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,mlocListener);
		Location loc = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		return loc;
	}

	private void addWeatherOverlay(Bitmap bmp) {
		if (!appInstance.isWeatheroverlayEnabled())
			return;
		if (cache.weatherOverlay != null)
			cache.weatherOverlay.remove();
		LatLng southwest = null;
		LatLng northeast = null;

		if (cache.zoom <= 9) {
			southwest = new LatLng(20.00107, 111.68321);
			northeast = new LatLng(24.60560, 116.66013);
		}

		if (cache.zoom <= 11) {
			southwest = new LatLng(21.15220, 112.92745);
			northeast = new LatLng(23.45446, 115.41589);
		}

		else {
			southwest = new LatLng(21.72777, 113.54956);
			northeast = new LatLng(22.87890, 114.79378);
		}

		float transparency = appInstance.getWeatherOverlayTransparency();
		LatLngBounds bounds = new LatLngBounds(southwest, northeast);
		cache.weatherOverlay = googleMap
				.addGroundOverlay(new GroundOverlayOptions()
						.positionFromBounds(bounds).transparency(transparency)
						.image(BitmapDescriptorFactory.fromBitmap(bmp)));
	}

	// ======= Network Opertaions =======
	private class NetworkTask extends GetDataTask {
		@Override
		protected void onPostExecute(Boolean result) {
			renderTargets();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(refreshValsRunnable);
				}
			};
			timer.schedule(task, cache.update_rate);
		}
	}

	private class WeatherTask extends GetWeatherOvelay {
		@Override
		protected void onPostExecute(Void result) {

			File root = Environment.getExternalStorageDirectory();
			root.mkdir();
			File appDir = new File(root, "Time2Fly");
			appDir.mkdir();
			File dir = new File(appDir, "256");
			dir.mkdir();
			final File[] files = dir.listFiles();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!weatherPlayed) {
						return;
					}
					round_robin++;
					round_robin = round_robin % files.length;
					BitmapFactory.Options opts = new Options();
					opts.inSampleSize = 2;
					weather_bmp = BitmapFactory.decodeFile(
							files[round_robin].getPath(), opts);
					runOnUiThread(playWeatherRunnable);
				}
			};
			timer.scheduleAtFixedRate(task, 0, 2000);
		}
	}

	Runnable playWeatherRunnable = new Runnable() {
		@Override
		public void run() {
			if (cache.weatherOverlay != null)
				cache.weatherOverlay.remove();

			LatLng southwest = new LatLng(20.00107, 111.68321);
			LatLng northeast = new LatLng(24.60560, 116.66013);
			float transparency = appInstance.getWeatherOverlayTransparency();
			LatLngBounds bounds = new LatLngBounds(southwest, northeast);
			cache.weatherOverlay = googleMap
					.addGroundOverlay(new GroundOverlayOptions()
							.positionFromBounds(bounds)
							.transparency(transparency)
							.image(BitmapDescriptorFactory
									.fromBitmap(weather_bmp)));
		}
	};

}
