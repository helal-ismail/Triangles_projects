package com.kreatitdesign.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.kreatitdesign.core.Constants;

public class RequestTask extends AsyncTask<Void, Void, Boolean> {
	String params = "";
	String result = "";



	public String setupParams(String bname, int task, String bindid,
			String susername, String spassword, JSONArray msg) {

		try {
			JSONObject jObject = new JSONObject();
			jObject.put("bname", bname);
			jObject.put("task", task);
			jObject.put("bindid", bindid);
			jObject.put("susername", susername);
			jObject.put("spassword", spassword);
			jObject.put("msg", msg);
			params = jObject.toString();
			
//			params = params.replace("\"", "");
			Log.d(Constants.TAG, "PARAMS : " + params);
			
			return jObject.toString();
		} catch (Exception e) {
			Log.d(Constants.TAG, "Exception : " + e.getMessage());
			return "";
		}
	}

	@Override
	protected Boolean doInBackground(Void... arg0) {

		try {
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			
			HttpPost httpPost = new HttpPost(Constants.URL);
			httpPost.setHeader("Connection", "keep-alive");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept", "*/*");
			httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
			httpPost.setHeader("Accept-Language", "ar,en-US;q=0.8,en;q=0.6");
			httpPost.setHeader("Accept-Charset", "UTF-8,*;q=0.5");
			
//			httpPost.addHeader("Authorization", "Basic "+Base64.encodeToString("rat#1:rat".getBytes(),Base64.DEFAULT));
			
			if (!params.equals("")) {
				StringEntity se = new StringEntity(params);
				httpPost.setEntity(se);
			}
			
			
			httpResponse = httpClient.execute(httpPost);
			int status = httpResponse.getStatusLine().getStatusCode();
			Log.d(Constants.TAG, "STAUS = " + status);
			if (status == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = convertStreamToString(httpEntity.getContent());
				return true;
			}
			else
				return false;

		}

		catch (Exception e) {
			Log.d(Constants.TAG, "Exception : "+e.getMessage());
			return false;
		}
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
			Log.d(Constants.TAG, "RESULT : " + this.result);
		
			
		params = "";
		this.result = "";
		
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
