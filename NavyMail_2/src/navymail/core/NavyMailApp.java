package navymail.core;

import java.util.ArrayList;

import navymail.modules.Topic;
import navymail.modules.User;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class NavyMailApp extends Application {
	private static Context context;
	protected SharedPreferences prefs;
	public User currentUser = (User) LookUp.getInstance().usersHash.get(2);
	public ArrayList<Topic> topics = new ArrayList<Topic>();

	 @Override
	    public void onCreate() {
	        super.onCreate();
	        context = getApplicationContext();
	        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	        
	    }
	 
	 @Override
	    public void onTerminate() {
	        super.onTerminate();
	    }

	    public static Context getAppContext() {
	        return context;        
	    }
	    
//===== Shared Prefs Storage =====
	    public void setPassword(String password){
	    	 Editor editor = prefs.edit();
	         editor.putString(Constants.PASSWORD, password);
	         editor.commit();   	
	    }
	    
	    public boolean checkPassword(String password){
	    	String savedPassword = prefs.getString(Constants.PASSWORD, "0000");
	    	if(password.equalsIgnoreCase(savedPassword))
	    		return true;
	    	else
	    		return false;
	    }

	
//===== Arabization ====
	public String arabization(String original) {
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
