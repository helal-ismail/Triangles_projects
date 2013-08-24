package com.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.core.Constants;
import com.core.Utils;
import com.modules.Weather;

public class GetWeatherOvelay extends AsyncTask<Void, Void, Void> {

	@Override
	protected Void doInBackground(Void... arg0) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(Constants.WEATHER_256_URL);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = Utils.getInstance().convertStreamToString(instream);
				processWeatherResponse(result,0);
			}
			
			httpget = new HttpGet(Constants.WEATHER_128_URL);
			response = httpclient.execute(httpget);
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = Utils.getInstance().convertStreamToString(instream);
				processWeatherResponse(result,1);
			}
			
			
			httpget = new HttpGet(Constants.WEATHER_064_URL);
			response = httpclient.execute(httpget);
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = Utils.getInstance().convertStreamToString(instream);
				processWeatherResponse(result,2);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	private Weather processWeatherResponse(String str, int type){
		String[] list = str.split("\n");
		String timestamp="";
		Weather w =new Weather();
		for(int i = 0 ; i < list.length ; i ++){
			String row = list[i];
			if(row.equalsIgnoreCase(""))
				continue;		
			timestamp = row.split("_")[0];
			String image_url =  "http://hk.time2fly.org/time2fly/current_radar/"+row;
			int year = Integer.parseInt(timestamp.substring(0, 4));
			int month = Integer.parseInt(timestamp.substring(4, 6));
			int day = Integer.parseInt(timestamp.substring(6, 8));
			int hr = Integer.parseInt(timestamp.substring(8, 10));
			int min = Integer.parseInt(timestamp.substring(10, 12));
			int sec = Integer.parseInt(timestamp.substring(12, 14));
			
			Date d = new Date();
			d.setYear(year-1900);
			d.setMonth(month-1);
			d.setDate(day);
			d.setHours(hr);
			d.setMinutes(min);
			d.setSeconds(sec);
			d.toGMTString();
			w.expireDate = d;
			
			
		
		}
		
		return w;
	}
	

}
