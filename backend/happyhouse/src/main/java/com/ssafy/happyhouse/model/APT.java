package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class APT {
	private String aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private String dealAmount;
	private double area;
	private String date;
	private double lat;
	private double lng;
	private String jibun;
	private int interestCount;

	public APT() {
	}

	public APT(String aptCode, String aptName, String dongCode, String dongName, String sidoName, String gugunName, String dealAmount, double area, String date, double lat, double lng, String jibun, int interestCount) {
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dealAmount = dealAmount;
		this.area = area;
		this.date = date;
		this.lat = lat;
		this.lng = lng;
		this.jibun = jibun;
		this.interestCount = interestCount;
	}
}
