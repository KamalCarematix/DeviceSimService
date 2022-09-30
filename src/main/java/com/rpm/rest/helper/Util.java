package com.rpm.rest.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.http.HttpServletResponse;

public class Util {
  public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
  public static long getCurrentTimeInUTC() {
    return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
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
  
  public static synchronized String generateToken() {
    return nextToken();
  }
  
  protected static SecureRandom random = new SecureRandom();
  
  public static final int SECURE_TOKEN_LENGTH = 32;
  
  private static final char[] symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  
  private static final char[] buf = new char[32];
  
  public static String nextToken() {
    for (int idx = 0; idx < buf.length; idx++)
      buf[idx] = symbols[random.nextInt(symbols.length)]; 
    return new String(buf);
  }
  
  public static void main(String[] a) {
    System.out.println(nextToken());
  }
  
  public static void doResponse(HttpServletResponse response, String resStr) {
    try {
      OutputStreamWriter writer = new OutputStreamWriter((OutputStream)response.getOutputStream());
      writer.write(resStr);
      writer.flush();
      writer.close();
    } catch (Exception exception) {}
  }
  
  public static String postAndGetJSON(String postUrl) throws IOException {
    StringBuilder sBuilder = new StringBuilder();
    URL url = new URL(postUrl);
    HttpURLConnection http = (HttpURLConnection)url.openConnection();
    http.setDoInput(true);
    http.setDoOutput(true);
    http.setUseCaches(false);
    http.setRequestMethod("GET");
    http.setRequestProperty("Authorization", "Y2FyZW1hdGl4OnBhc3N3b3Jk");
    http.setRequestProperty("username", "carematix");
    http.setRequestProperty("password", "password");
    http.setRequestProperty("X-CARE-TOKEN", "tknDHYLLqjd11fclV5dVrN3OA0PyxDgF");
    http.setRequestProperty("X-CARE-API-KEY", "carematixWellnessNew");
    BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
    String response = null;
    while ((response = rd.readLine()) != null)
      sBuilder.append(response); 
    rd.close();
    http.disconnect();
    return sBuilder.toString();
  }
}