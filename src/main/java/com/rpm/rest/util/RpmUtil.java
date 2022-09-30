package com.rpm.rest.util;

import com.rpm.rest.Conf.ServiceInterfaceConf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RpmUtil {
  private static Logger logger = LogManager.getLogger(com.rpm.rest.util.RpmUtil.class.getName());
  
  public static String DATE_FORMAT_REPORT = "dd/MM/yyyy";
  
  public static String DATE_FORMAT_XML = "yyyy-MM-dd'T'HH:mm:ss";
  
  public static String getHttpRequestString(HttpServletRequest request) throws IOException {
    String reqString = null;
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader((InputStream)request.getInputStream()));
      String inputLine = null;
      StringBuilder sBuilder = new StringBuilder();
      while ((inputLine = in.readLine()) != null)
        sBuilder.append(inputLine); 
      reqString = sBuilder.toString();
      reqString = URLDecoder.decode(reqString.toString(), "UTF-8");
    } catch (IOException e) {
      logger.debug("Exception in getting request string from Request Stream. Exception Message is : " + e.getMessage());
      throw e;
    } finally {
      if (in != null)
        in.close(); 
    } 
    logger.trace("reqString --> " + reqString);
    return reqString;
  }
  
  public static String formatForReport(long millis) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(millis);
    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_REPORT);
    String dateString = formatter.format(cal.getTime());
    return dateString;
  }
  
  public static String formatForXML(Calendar cal) {
    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_XML);
    String dateString = formatter.format(cal.getTime());
    System.out.println(dateString);
    return dateString;
  }
  
  public static String formatForXML(long millis) {
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    cal.setTimeInMillis(millis);
    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_XML);
    String dateString = formatter.format(cal.getTime());
    System.out.println(dateString);
    return dateString;
  }
  
  public static String postAndGetData(String requestString, String serviceURL) throws IOException {
    StringBuilder sBuilder = new StringBuilder();
    URL url = new URL(serviceURL);
    HttpURLConnection http = (HttpURLConnection)url.openConnection();
    http.setDoInput(true);
    http.setDoOutput(true);
    http.setUseCaches(false);
    http.setRequestMethod("POST");
    http.setRequestProperty("Content-Length", String.valueOf(requestString.length()));
    http.setRequestProperty("Pragma", "public");
    http.setRequestProperty("Cache-Control", "max-age=0");
    OutputStreamWriter wr = new OutputStreamWriter(http.getOutputStream());
    wr.write(requestString);
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
  
  public static String postMail(String mailTo, String mailFrom, String subect, String content) throws IOException {
    return postMail(mailTo, "", mailFrom, subect, content);
  }
  
  public static String postMail(String mailTo, String mailCc, String mailFrom, String subect, String content) throws IOException {
    StringBuilder serviceURL = new StringBuilder(ServiceInterfaceConf.MAILER_URL + "?eventid=&mailto=" + mailTo + "&mailfrom=" + mailFrom + "&subject=" + URLEncoder.encode(subect, "UTF-8") + "&content=" + URLEncoder.encode(content, "UTF-8"));
    if (!mailCc.equalsIgnoreCase(""))
      serviceURL.append("&mailcc=" + mailCc); 
    StringBuilder sBuilder = new StringBuilder();
    URL url = new URL(serviceURL.toString());
    HttpURLConnection http = (HttpURLConnection)url.openConnection();
    http.setUseCaches(false);
    http.setRequestMethod("GET");
    http.setRequestProperty("accept", "application/json");
    http.getInputStream();
    http.disconnect();
    return sBuilder.toString();
  }
}
