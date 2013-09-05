package com.core;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import android.R.integer;
import android.graphics.Bitmap;

import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.modules.Tab;

public class CacheManager {
	
	private static CacheManager instance = new CacheManager();
	public static CacheManager getInstance(){
		return instance;
	}
	public int update_rate = 10000;
	public HashMap<String, Tab> tabs_hash = new HashMap<String, Tab>();
	
	public Bitmap weather_bmp;
	public String selectedReg = "";
	 
	
	public float zoom= 0;
	public GroundOverlay weatherOverlay;
	public int roundRobin = 0;
	public int cyclesCount = 0;
	
	public int first = 1;
	public boolean isRunning = false;
	
	public void addTab(Tab t){
		Tab oldTab = tabs_hash.get(t.addr);
		if(oldTab !=null)
		{
			t.xLat = oldTab.lat;
			t.xLon = oldTab.lon;
			t.marker = oldTab.marker;
			t.cycles = cyclesCount;
		}	
		else{
			t.xLat = -1;
			t.xLon = -1;
			t.cycles = 0;
			t.marker = null;
		}
		tabs_hash.put(t.addr, t);
	}
}
