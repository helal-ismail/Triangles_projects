package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import com.db.TabsTable;
import com.modules.Tab;

public class GetDataTask extends AsyncTask<Void, Void, Boolean> {
	
	TabsTable tabsTable ;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		tabsTable = new TabsTable(Time2FlyApp.getAppContext());
		
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(Constants.JSON_URL);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
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
			
			

			JSONArray tabs = obj.getJSONArray("tab");
			for (int i = 0; i < num; i++) {
				JSONArray tabArray = tabs.optJSONArray(i);
				Tab t = new Tab();
				t.addr = tabArray.optString(0);
				t.alt = tabArray.optDouble(1);
				t.lat = tabArray.optDouble(2);
				t.lon = tabArray.optDouble(3);
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
				
				//Insert to DB
				tabsTable.insertTab(t);
				
				//Insert to Hash
				CacheManager.getInstance().addTab(t);
			//	Log.d(Constants.TAG, "hash size : " + CacheManager.getInstance().tabs_hash.size());
			
			}
			return true;
		} catch (Exception e) {
			Log.d(Constants.TAG, "Exception at InsertToDb "+e.getMessage());
			return false;
		}
	}

	private String convertStreamToString(InputStream is) throws IOException {
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
}
