package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class AvgByDong {
    private String dong;
    private Double avg;

    public AvgByDong(String dong, Double avg) {
        this.dong = dong;
        this.avg = avg;
    }

    public AvgByDong() {
    }
}
