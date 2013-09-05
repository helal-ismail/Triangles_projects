package com.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.modules.Tab;
import com.ui.R;

public class Utils {

	private static Utils instance = new Utils();

	public static Utils getInstance() {
		return instance;
	}

	public int getResourceID(Tab tab, boolean isBlack){
		
		if(isBlack)
			return getResourceID_BLACK(tab.type);
		else
			return getResourceID_RED(tab.type);
	
	}
	public int getResourceID_BLACK(String name) {
		name = name.toLowerCase();
		if (name == null)
			return R.drawable.a320;
		
		if (name.contains("a32"))
			return R.drawable.a320;

		if (name.contains("a33"))
			return R.drawable.a330;

		if (name.contains("a34"))
			return R.drawable.a340;

		if (name.contains("a38"))
			return R.drawable.a380;

		if (name.contains("b73"))
			return R.drawable.b737;

		if (name.contains("b74"))
			return R.drawable.b747;

		if (name.contains("b76"))
			return R.drawable.b767;

		if (name.contains("b77"))
			return R.drawable.b777;

		if(name.contains("b200c"))
			return R.drawable.b200;
		
		if(name.contains("bombardier"))
			return R.drawable.b32;
		
		if(name.contains("cessna"))
			return R.drawable.c32;
				
		if(name.contains("md11"))
			return R.drawable.md11;

		if(name.contains("leariet"))
			return R.drawable.l;
		
		if(name.contains("fokker100"))
			return R.drawable.f100;
		
		if(name.contains("embraererj"))
			return R.drawable.erj;
		
		if(name.contains("e195"))
			return R.drawable.e195;
		
		
		

		
		return R.drawable.a320;

	}
	
	
	
	public int getResourceID_RED(String name) {
		name = name.toLowerCase();
		if (name == null)
			return R.drawable.a320_red;
		
		if (name.contains("a32"))
			return R.drawable.a320_red;

		if (name.contains("a33"))
			return R.drawable.a330_red;

		if (name.contains("a34"))
			return R.drawable.a340_red;

		if (name.contains("a38"))
			return R.drawable.a380_red;

		if (name.contains("b73"))
			return R.drawable.b737_red;

		if (name.contains("b74"))
			return R.drawable.b747_red;

		if (name.contains("b76"))
			return R.drawable.b767_red;

		if (name.contains("b77"))
			return R.drawable.b777_red;

		if(name.contains("b200c"))
			return R.drawable.b200_red;
		
		if(name.contains("bombardier"))
			return R.drawable.b_red;
		
		if(name.contains("cessna"))
			return R.drawable.c_red;
				
		if(name.contains("md11"))
			return R.drawable.md11_red;

		if(name.contains("leariet"))
			return R.drawable.l_red;
		
		if(name.contains("fokker100"))
			return R.drawable.f100_red;
		
		if(name.contains("embraererj"))
			return R.drawable.erj_red;
		
		if(name.contains("e195"))
			return R.drawable.e195_red;
		
		return R.drawable.a320_red;
	}

	public Bitmap rotateImage(Bitmap bitmap, int angle, int bearing_angle) {
		Matrix matrix = new Matrix();
		// matrix.postRotate(angle);
		matrix.setRotate(angle - bearing_angle);

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

	}
	
	public String convertStreamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
	public int searchByTitle(String title){
		int index = 0;
		Iterator itr = CacheManager.getInstance().tabs_hash.keySet().iterator();
		while(itr.hasNext()){
			String key = (String) itr.next();
			Tab t = (Tab) CacheManager.getInstance().tabs_hash.get(key);
			if(t.callSign.equalsIgnoreCase(title))
			{
				CacheManager.getInstance().selectedReg = t.addr;
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public String getDirectionFromAngle(float bearing){
		if (bearing < 15)
			return "N";
		if (bearing < 90)
			return "NE";
		
		if (bearing < 105)
			return "E";
		if (bearing < 180)
			return "SE";
		
		if (bearing < 195)
			return "S";
		if (bearing < 270)
			return "SW";
		
		if (bearing < 285)
			return "W";
		if (bearing < 345)
			return "NW";
		
		return "N";
	}

}
