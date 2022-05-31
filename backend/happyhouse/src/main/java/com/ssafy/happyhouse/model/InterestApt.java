package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class InterestApt {
    private String id;
    private long aptCode;
    private String userid;

    public InterestApt(String id, long aptCode, String userid) {
        this.id = id;
        this.aptCode = aptCode;
        this.userid = userid;
    }

    public InterestApt() {
    }
}
