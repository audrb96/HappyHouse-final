package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class Store {
    private String storeNo;
    private String storeName;
    private String storeLargeKind;
    private String storeSmKind;
    private String dongCode;
    private String doroadadd;
    private double lng;
    private double lat;
    private double distance;

    public Store(String storeNo, String storeName, String dongCode, double lng, double lat, double distance) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.dongCode = dongCode;
        this.lng = lng;
        this.lat = lat;
        this.distance = distance;
    }

    public Store() {
    }
}
