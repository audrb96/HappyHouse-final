package com.ssafy.happyhouse.model;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class QABoard {
    private int boardNum;
    private String ID;
    private String title;
    private String content;
    private String createTime;
    private int recnt;
    
	public QABoard(int boardNum, String iD, String title, String content, String createTime, int recnt) {
		super();
		this.boardNum = boardNum;
		ID = iD;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.recnt = recnt;
	}
}
