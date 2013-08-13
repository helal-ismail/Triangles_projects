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
		if (name == null)
			return R.drawable.a320;
		
		if (name.equalsIgnoreCase("a320"))
			return R.drawable.a320;

		if (name.equalsIgnoreCase("a330"))
			return R.drawable.a330;

		if (name.equalsIgnoreCase("a340"))
			return R.drawable.a340;

		if (name.equalsIgnoreCase("a380"))
			return R.drawable.a380;

		if (name.equalsIgnoreCase("b737"))
			return R.drawable.b737;

		if (name.equalsIgnoreCase("b747"))
			return R.drawable.b747;

		if (name.equalsIgnoreCase("b767"))
			return R.drawable.b767;

		if (name.equalsIgnoreCase("b777"))
			return R.drawable.b777;

		if(name.equalsIgnoreCase("b200c"))
			return R.drawable.b200c;
		
		if(name.equalsIgnoreCase("bombardier"))
			return R.drawable.bombardier;
		
		if(name.equalsIgnoreCase("cessna"))
			return R.drawable.cessna;
				
		if(name.equalsIgnoreCase("md11"))
			return R.drawable.md11;

		if(name.equalsIgnoreCase("leariet"))
			return R.drawable.leariet;
		
		if(name.equalsIgnoreCase("fokker100"))
			return R.drawable.fokker100;
		
		if(name.equalsIgnoreCase("embraererj"))
			return R.drawable.embraererj;
		
		if(name.equalsIgnoreCase("e195"))
			return R.drawable.e195;
		
		
		

		
		return R.drawable.a320;

	}

	public Bitmap rotateImage(Bitmap bitmap, int angle) {
		Matrix matrix = new Matrix();
		// matrix.postRotate(angle);
		matrix.setRotate(angle);

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

	}

}
