package com.network;

import java.io.InputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.core.CacheManager;
import com.core.Constants;
import com.core.Time2FlyApp;
import com.core.Utils;
import com.db.TabsTable;
import com.google.android.gms.maps.model.LatLng;
import com.modules.Tab;

public class GetDataTask extends AsyncTask<Void, Void, Boolean> {
	
	//TabsTable tabsTable ;
	

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(Constants.JSON_URL);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				CacheManager.getInstance().cyclesCount++;
				InputStream instream = entity.getContent();
				String result = Utils.getInstance().convertStreamToString(instream);
				JSONObject obj = new JSONObject(result);
				insertIntoDb(obj);					
				
			}

			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	

	private boolean insertIntoDb(JSONObject obj) {
		try {
			String result = obj.optString("result");
			if (result == null || !result.equalsIgnoreCase("success"))
				return false;
			int num = obj.optInt("num");
			CacheManager.getInstance().num_targets = num;
			
			int update_rate = obj.optInt("update_rate");
			if(update_rate > 0)
				CacheManager.getInstance().update_rate = update_rate * 1000;
			
			JSONArray tabs = obj.getJSONArray("tab");
			for (int i = 0; i < num; i++) {
				JSONArray tabArray = tabs.optJSONArray(i);
				Tab t = new Tab();
				t.addr = tabArray.optString(0);
				t.alt = (float) tabArray.optDouble(1);
				t.lat = (float) tabArray.optDouble(2);
				t.lon = (float) tabArray.optDouble(3);
				t.track = tabArray.optInt(4);
				t.sqw = tabArray.optString(5);
				t.callSign = tabArray.optString(6);
				t.unix_ts = tabArray.optInt(7);
				t.user_id = tabArray.optString(8);
				t.vspd = tabArray.optInt(9);
				t.spd = tabArray.optInt(10);
				t.reg = tabArray.optString(11);
				t.type = tabArray.optString(12);
				t.owner = tabArray.optString(13);
				t.code = tabArray.optString(14);
				t.timeStamp = new Date();
//				if (t.marker != null){
//					LatLng loc = new LatLng(t.lat, t.lon);
//					t.marker.setPosition(loc);
//					t.marker.setTitle(t.callSign);
//				}
				CacheManager.getInstance().addTab(t);
			
			}
			return true;
		} catch (Exception e) {
			Log.d(Constants.TAG, "Exception at InsertToDb "+e.getMessage());
			return false;
		}
	}

	
}
