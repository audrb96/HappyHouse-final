package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.Store;
import com.ssafy.happyhouse.repo.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepo storeRepo;

    @Override
    public Store getStore(String StoreNo) {
        return storeRepo.selectOne(StoreNo);
    }

    @Override
    public List<Store> getStoresByDong(String dongCode) {
        return storeRepo.selectByDong(dongCode);
    }

    @Override
    public List<Store> getStoresByDistance(double lat, double lng) {
        Map<String, Object> map = new HashMap<>();
        map.put("lat",lat);
        map.put("lng",lng);
        System.out.println("Service" + map);
        return storeRepo.selectByDistance(map);
    }


}
