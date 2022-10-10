package com.rpm.rest.model;

import java.io.Serializable;
import java.sql.Timestamp;

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

	@Column(name = "IMSI")
	private String IMSI;

	@Column(name = "IS_DEVICE_ACTIVE")
	private String IS_DEVICE_ACTIVE;

	@Column(name = "IS_SIM_SUSPENDED")
	private String IS_SIM_SUSPENDED;

	@Column(name = "SIM_UPDATE_DATE")
	private Timestamp SIM_UPDATE_DATE;

	@Column(name = "DEVICE_UPDATE_DATE")
	private Timestamp DEVICE_UPDATE_DATE;

	@Column(name = "CREATED_DATE")
	private Timestamp CREATED_DATE;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getSIM_UPDATE_DATE() {
		return SIM_UPDATE_DATE;
	}

	public void setSIM_UPDATE_DATE(Timestamp sIM_UPDATE_DATE) {
		SIM_UPDATE_DATE = sIM_UPDATE_DATE;
	}

	public Timestamp getDEVICE_UPDATE_DATE() {
		return DEVICE_UPDATE_DATE;
	}

	public void setDEVICE_UPDATE_DATE(Timestamp dEVICE_UPDATE_DATE) {
		DEVICE_UPDATE_DATE = dEVICE_UPDATE_DATE;
	}

	public Timestamp getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Timestamp cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

}
