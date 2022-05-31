package com.ssafy.happyhouse.repo;

import com.ssafy.happyhouse.model.Store;

import java.util.List;
import java.util.Map;

public interface StoreRepo {
    Store selectOne(String storeNo);
    List<Store> selectByDong(String dongcode);
    List<Store> selectByDistance(Map<String, Object> map);
}
