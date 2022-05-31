package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Comment {
	@ApiModelProperty(value = "댓글번호")
	private int commentNum;
	@ApiModelProperty(value = "글번호")
	private int articleNo;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	public Comment(int commentNum, int articleNo, String userid, String content, String regtime) {
		super();
		this.commentNum = commentNum;
		this.articleNo = articleNo;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
	}	

}
