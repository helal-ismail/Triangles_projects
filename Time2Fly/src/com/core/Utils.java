package com.core;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.modules.Tab;
import com.ui.R;

public class Utils {

	private static Utils instance = new Utils();

	public static Utils getInstance() {
		return instance;
	}

	public int getResourceID(Tab tab){
		Date now = new Date();
		long timeDiff = now.getTime() - tab.timeStamp.getTime();
		if(timeDiff < Constants.TS_THRESHOLD)
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

}
