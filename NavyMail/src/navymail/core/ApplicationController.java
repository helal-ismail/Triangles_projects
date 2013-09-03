
package navymail.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import navymail.modules.Topic;
import navymail.modules.User;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

public class ApplicationController {
	
	private static ApplicationController instance = new ApplicationController();
	public static ApplicationController getInstance(){
		return instance;
	}
	
	public User currentUser = (User)LookUp.getInstance().usersHash.get(2);
	public ArrayList<Topic> da5ly_topics = new ArrayList<Topic>();
	public ArrayList<Topic> khargy_topics = new ArrayList<Topic>();
	public String cachedOrders = "";
	public String selectedUnits = "";
	public HashMap signedHash = new HashMap<String, Boolean>();
	
	
	public String arabization(String original){
		original = original.replaceAll("0", "٠");
		original = original.replaceAll("1", "١");
		original = original.replaceAll("2", "٢");
		original = original.replaceAll("3", "٣");
		original = original.replaceAll("4", "٤");
		original = original.replaceAll("5", "٥");
		original = original.replaceAll("6", "٦");
		original = original.replaceAll("7", "٧");
		original = original.replaceAll("8", "٨");
		original = original.replaceAll("9", "٩");
		return original;
	}
	
	public void copy(File src, File dst) throws IOException {
		File[] files = src.listFiles();
		for (int i = 0 ; i < files.length ; i ++)
		{
			File[] dst_files = dst.listFiles(); 
			File source = files[i];
			File destination = new File (dst, source.getName());
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}
	
	public Point getXY(int x, int y, Display d, int numLines ){
		int screenW = d.getWidth();
		int screenH = d.getHeight();
		
		Point point = new Point(x,y);
		
		if( (screenW - x) <  200)
			point.x = x - 225 ;
		
		
		int expectedHeight = 17 * numLines + 200;
		if( (screenH - y ) < expectedHeight )
			point.y = y - expectedHeight-25;
		
		return point;
	}

}
