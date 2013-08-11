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
		tabs_hash.put(t.addr, t);
	}
}
