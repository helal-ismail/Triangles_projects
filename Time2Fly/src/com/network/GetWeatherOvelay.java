package com.network;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.core.Constants;
import com.core.Utils;

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
				String result = Utils.getInstance().convertStreamToString(
						instream);
				JSONObject obj = new JSONObject(result);
			}
		} catch (Exception e) {

		}
		return null;
	}

}
