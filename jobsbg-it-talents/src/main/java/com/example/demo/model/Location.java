package com.example.demo.model;

public class Location {
	private long locationId;
	private String address;
	private int postCode;
	private long cityId;
	public Location(long locationId, String address, int postCode, long cityId) {
		super();
		this.locationId = locationId;
		this.address = address;
		this.postCode = postCode;
		this.cityId = cityId;
	}
	public long getLocationId() {
		return locationId;
	}
	public String getAddress() {
		return address;
	}
	public int getPostCode() {
		return postCode;
	}
	public long getCityId() {
		return cityId;
	}
	
}
