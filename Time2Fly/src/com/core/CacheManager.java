package com.core;

import java.util.HashMap;

import com.modules.Tab;

public class CacheManager {
	
	private static CacheManager instance = new CacheManager();
	public static CacheManager getInstance(){
		return instance;
	}
	
	public HashMap<String, Tab> tabs_hash = new HashMap<String, Tab>();
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
