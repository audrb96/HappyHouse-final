package com.ssafy.happyhouse.model;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
public class BaseAddress {
    private double lat;
    private double lng;
    public BaseAddress() throws IOException {
    }

}
