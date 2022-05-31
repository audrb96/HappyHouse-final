package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ssafy.happyhouse.model.Store;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("getStore/{storeNo}")
    private ResponseEntity<?> getStore(@PathVariable("storeNo") String storeNo){
        try {
            Store store = storeService.getStore(storeNo);
            return new ResponseEntity<>(store, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("getStoreByDong/{dongcode}")
    private ResponseEntity<?> getStoreByDong(@PathVariable("dongcode") String dongcode){
        try {
            List<Store> storeList = storeService.getStoresByDong(dongcode);
            return new ResponseEntity<>(storeList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("getStoreByDistance/{lat}/{lng}")
    private ResponseEntity<?> getStoreByDistance(@PathVariable("lat") double lat, @PathVariable("lng") double lng){
        try {
            System.out.println("Controller : "+lat +" " + lng);
            List<Store> storeList = storeService.getStoresByDistance(lat, lng);
            for (Store store : storeList) {
                double distance = store.getDistance();
                store.setDistance(Math.round(distance*10*1000)/10.0);
            }
            return new ResponseEntity<>(storeList,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}

