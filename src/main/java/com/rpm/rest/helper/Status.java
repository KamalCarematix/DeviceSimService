package com.rpm.rest.helper;

public enum Status {
  Pending("Your request is submitted and waiting to be processed."),
  Processing("Your request is under process. Please check later to download the report(s)."),
  Failure("Please contact Admin."),
  Deleted("Reports deleted from server."),
  Ready("Click the file icon to download the report(s). The file is available for 3 Days only.");
  
  private String action;
  
  public String getAction() {
    return this.action;
  }
  
  Status(String action) {
    this.action = action;
  }
}
