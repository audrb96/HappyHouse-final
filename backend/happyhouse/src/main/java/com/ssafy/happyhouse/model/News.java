package com.ssafy.happyhouse.model;

import lombok.Data;

@Data
public class News {

	String title; //제목
	String content; //글
	String img; //이미지
	String link; //기사 링크
	
	public News(String title, String content, String img, String link) {
		super();
		this.title = title;
		this.content = content;
		this.img = img;
		this.link = link;
	}
	
}
