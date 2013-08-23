package com.core;

import java.util.HashMap;

import android.graphics.Bitmap;

import com.modules.Tab;

public class CacheManager {
	
	private static CacheManager instance = new CacheManager();
	public static CacheManager getInstance(){
		return instance;
	}
	public int update_rate = 10000;
	public HashMap<String, Tab> tabs_hash = new HashMap<String, Tab>();
	public Bitmap weather_bmp;
	
	public void addTab(Tab t){
		Tab oldTab = tabs_hash.get(t.addr);
		if(oldTab !=null)
		{
			t.xLat = oldTab.lat;
			t.xLon = oldTab.lon;
		}	
		else{
			t.xLat = -1;
			t.xLon = -1;
		}
		tabs_hash.put(t.addr, t);
	}
}
