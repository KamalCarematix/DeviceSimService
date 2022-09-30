package com.rpm.rest.exception;

public class RpmAuthException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public RpmAuthException() {}
  
  public RpmAuthException(String str) {
    super(str);
  }
}