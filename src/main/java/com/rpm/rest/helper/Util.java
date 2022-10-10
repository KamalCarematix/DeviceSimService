package com.rpm.rest.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
  
  public static String getHttpClientCall(String postUrl) throws IOException {
    StringBuilder sBuilder = new StringBuilder();
    URL url = new URL(postUrl);
    HttpURLConnection http = (HttpURLConnection)url.openConnection();
    http.setDoInput(true);
    http.setDoOutput(true);
    http.setUseCaches(false);
    http.setRequestMethod("GET");
    BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
    String response = null;
    while ((response = rd.readLine()) != null)
      sBuilder.append(response); 
    rd.close();
    http.disconnect();
    return sBuilder.toString();
  }
  
  public static String postAndGetJSON(String postUrl, String postBody) throws IOException {
	    StringBuilder sBuilder = new StringBuilder();
	    URL url = new URL(postUrl);
	    HttpURLConnection http = (HttpURLConnection)url.openConnection();
	    http.setDoInput(true);
	    http.setDoOutput(true);
	    http.setUseCaches(false);
	    http.setRequestMethod("POST");
	    http.setRequestProperty("Content-Length", String.valueOf(postBody.length()));
	    http.setRequestProperty("Content-Type", "application/json");
	    http.setRequestProperty("Pragma", "public");
	    http.setRequestProperty("Cache-Control", "max-age=0");
	    OutputStreamWriter wr = new OutputStreamWriter(http.getOutputStream());
	    wr.write(postBody);
	    wr.flush();
	    wr.close();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
	    String response = null;
	    while ((response = rd.readLine()) != null)
	      sBuilder.append(response); 
	    rd.close();
	    http.disconnect();
	    return sBuilder.toString();
	  }
	 
}