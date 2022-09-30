package com.rpm.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DVC_DEVICE_SIM_DETAILS")
public class DeviceDataSimEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "SERIAL_NUMBER")
	private String SERIAL_NUMBER;

	@Column(name = "SIM")
	private String SIM;

	@Column(name = "IMEI")
	private String IMEI;

	@Column(name = "IS_DEVICE_ACTIVE")
	private String IS_DEVICE_ACTIVE;

	@Column(name = "IS_SIM_SUSPENDED")
	private String IS_SIM_SUSPENDED;

	@Column(name = "IMSI")
	private String IMSI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSERIAL_NUMBER() {
		return SERIAL_NUMBER;
	}

	public void setSERIAL_NUMBER(String sERIAL_NUMBER) {
		SERIAL_NUMBER = sERIAL_NUMBER;
	}

	public String getSIM() {
		return SIM;
	}

	public void setSIM(String sIM) {
		SIM = sIM;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getIS_DEVICE_ACTIVE() {
		return IS_DEVICE_ACTIVE;
	}

	public void setIS_DEVICE_ACTIVE(String iS_DEVICE_ACTIVE) {
		IS_DEVICE_ACTIVE = iS_DEVICE_ACTIVE;
	}

	public String getIS_SIM_SUSPENDED() {
		return IS_SIM_SUSPENDED;
	}

	public void setIS_SIM_SUSPENDED(String iS_SIM_SUSPENDED) {
		IS_SIM_SUSPENDED = iS_SIM_SUSPENDED;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
