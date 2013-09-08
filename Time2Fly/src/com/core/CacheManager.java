package com.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

import android.R.integer;
import android.graphics.Bitmap;
import android.util.Log;

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
	public ArrayList<Tab> list = new ArrayList<Tab>();
	public Bitmap weather_bmp;
	public String selectedReg = "";
	 
	
	public float zoom= 0;
	public GroundOverlay weatherOverlay;
	public int roundRobin = 0;
	public int cyclesCount = 0;
	
	public int first = 1;
	public boolean isRunning = false;
	public int num_targets;
	
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
	
	
	public void getSortedList(){
		list.clear();
		Collection<Tab> collection = tabs_hash.values();
		Iterator<Tab> itr = collection.iterator();
		while(itr.hasNext())
			list.add(itr.next());
		
		Collections.sort(list, new MyComparator());
		for(int i = 0 ; i < list.size() ; i ++){
			Log.d(Constants.TAG, list.get(i).callSign);
		}
	}

	
}
