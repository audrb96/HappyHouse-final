package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QAComment {
	@ApiModelProperty(value = "qa댓글번호")
	private int qacommentNum;
	@ApiModelProperty(value = "qa글번호")
	private int boardNum;
	@ApiModelProperty(value = "작성자아이디")
	private String ID;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	
	public QAComment(int qacommentNum, int boardNum, String iD, String content, String regtime) {
		super();
		this.qacommentNum = qacommentNum;
		this.boardNum = boardNum;
		ID = iD;
		this.content = content;
		this.regtime = regtime;
	}

}
