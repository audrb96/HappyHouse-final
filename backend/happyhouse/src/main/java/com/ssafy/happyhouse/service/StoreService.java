package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.Store;

import java.util.List;

public interface StoreService {

    Store getStore(String StoreNo);
    List<Store> getStoresByDong(String dongCode);
    List<Store> getStoresByDistance(double lat, double lng);
}
