package com.core;

import com.ui.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;

public class Utils {

	private static Utils instance = new Utils();

	public static Utils getInstance() {
		return instance;
	}

	public int getResourceID(String name) {
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

	public Bitmap rotateImage(Bitmap bitmap, int angle, int bearing_angle) {
		Matrix matrix = new Matrix();
		// matrix.postRotate(angle);
		matrix.setRotate(angle - bearing_angle);

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

	}

}
