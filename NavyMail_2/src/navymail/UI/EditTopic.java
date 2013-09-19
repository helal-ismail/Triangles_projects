package navymail.UI;

import java.io.File;
import java.io.FileOutputStream;

import navymail.UI2.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class EditTopic extends Activity {
	String imgPath = "";
	CustomView customView;
	FrameLayout parent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		imgPath = (String) getIntent().getExtras().get("path");
		
		customView  = new CustomView(this);
		
		parent = (FrameLayout)findViewById(R.id.parent);
		parent.addView(customView);
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		customView.save(parent);
		finish();
	}
	
	

	private class CustomView extends View {
		private static final float STROKE_WIDTH = 2f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		//Canvas canvas;
		private Paint paint = new Paint();
		private Path path = new Path();
		Bitmap mBitmap;
		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();
		

		public CustomView(Context context) {
			super(context);
			paint.setAntiAlias(true);
			paint.setColor(Color.parseColor("#14694C"));
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
			mBitmap = BitmapFactory.decodeFile(imgPath);
			BitmapDrawable d = new BitmapDrawable(mBitmap);
			setBackgroundDrawable(d);
			/*Bitmap mutableBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
			canvas = new Canvas(mutableBitmap);*/
		}

		
		public void save(View v){
			try{
				parent.setDrawingCacheEnabled(true);
				parent.buildDrawingCache();
				
				Bitmap bmp =  Bitmap.createBitmap (parent.getWidth(), parent.getHeight(), Bitmap.Config.RGB_565);;
				Canvas canvas = new Canvas(bmp);
				v.draw(canvas);

				//Bitmap bm = parent.getDrawingCache();
				File newImage = new File(imgPath);
				FileOutputStream out = new FileOutputStream(newImage);
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
				parent.destroyDrawingCache();
			}
			catch (Exception e) {
				Log.d("exception", e.getMessage());
			}
		}
		
		public void clear() 
        {
            path.reset();
            invalidate();
        }
		
		@Override
        protected void onDraw(Canvas canvas) 
        {
            canvas.drawPath(path, paint);
        }
		
		
		
		@Override
        public boolean onTouchEvent(MotionEvent event) 
        {
            float eventX = event.getX();
            float eventY = event.getY();
 
            switch (event.getAction()) 
            {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                lastTouchX = eventX;
                lastTouchY = eventY;
                return true;
 
            case MotionEvent.ACTION_MOVE:
 
            case MotionEvent.ACTION_UP:
 
                resetDirtyRect(eventX, eventY);
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) 
                {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    expandDirtyRect(historicalX, historicalY);
                    path.lineTo(historicalX, historicalY);
                }
                path.lineTo(eventX, eventY);
                break;
 
            default:
                return false;
            }
 
            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));
 
            lastTouchX = eventX;
            lastTouchY = eventY;
 
            return true;
        }
		
		
		 private void expandDirtyRect(float historicalX, float historicalY) 
	        {
	            if (historicalX < dirtyRect.left) 
	            {
	                dirtyRect.left = historicalX;
	            } 
	            else if (historicalX > dirtyRect.right) 
	            {
	                dirtyRect.right = historicalX;
	            }
	 
	            if (historicalY < dirtyRect.top) 
	            {
	                dirtyRect.top = historicalY;
	            } 
	            else if (historicalY > dirtyRect.bottom) 
	            {
	                dirtyRect.bottom = historicalY;
	            }
	        }
	 
	        private void resetDirtyRect(float eventX, float eventY) 
	        {
	            dirtyRect.left = Math.min(lastTouchX, eventX);
	            dirtyRect.right = Math.max(lastTouchX, eventX);
	            dirtyRect.top = Math.min(lastTouchY, eventY);
	            dirtyRect.bottom = Math.max(lastTouchY, eventY);
	        }
		
	}

}
