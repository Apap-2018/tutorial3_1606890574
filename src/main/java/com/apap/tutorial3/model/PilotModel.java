package com.apap.tutorial3.model;
/**
 * Pilot
 */
public class PilotModel {
	private String id;
	private String licenseNumber;
	private String name;
	private Integer flyHour;
	public PilotModel(String idd, String licenseNumberr, String namee, Integer flyHourr) {
		id = idd;
		licenseNumber = licenseNumberr;
		name = namee;
		flyHour = flyHourr;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFlyHour() {
		return flyHour;
	}
	public void setFlyHour(Integer flyHour) {
		this.flyHour = flyHour;
	}
}
