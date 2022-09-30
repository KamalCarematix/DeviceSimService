package com.rpm.rest.helper;

import java.util.HashMap;

public class WebserviceResponse {
  public long code;
  
  public String message;
  
  public HashMap<String, Object> data = null;
  
  public WebserviceResponse() {
    this.code = 404L;
    this.message = "";
  }
  
  public WebserviceResponse(long code, String message, HashMap<String, Object> data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
  
  public long getCode() {
    return this.code;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public HashMap<String, Object> getData() {
    return this.data;
  }
  
  public void setCode(long code) {
    this.code = code;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public void setData(HashMap<String, Object> data) {
    this.data = data;
  }
}
