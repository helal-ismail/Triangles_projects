
package navymail.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.FrameLayout;

import navymail.modules.Topic;
import navymail.modules.User;

public class ApplicationController {
	
	private static ApplicationController instance = new ApplicationController();
	public static ApplicationController getInstance(){
		return instance;
	}
	
	public User currentUser = (User)LookUp.getInstance().usersHash.get(2);
	public ArrayList<Topic> topics = new ArrayList<Topic>();
	
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
	

}
