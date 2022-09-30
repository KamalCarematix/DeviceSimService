package com.rpm.rest.model;

import java.io.Serializable;
import java.util.List;

public class DeviceEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<String> serailNumbers;
  private String action;
  private List<String> sims;
public List<String> getSerailNumbers() {
	return serailNumbers;
}
public void setSerailNumbers(List<String> serailNumbers) {
	this.serailNumbers = serailNumbers;
}
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

public List<String> getSims() {
	return sims;
}
public void setSims(List<String> sims) {
	this.sims = sims;
}
@Override
public String toString() {
	return "DeviceEntity [serailNumbers=" + serailNumbers + ", action=" + action + "]";
}


}
