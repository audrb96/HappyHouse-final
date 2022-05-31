package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.QAComment;

public interface QACommentService {

	public List<QAComment> retrieveQAComment();
	public List<QAComment> detailQAComment(int boardNum);
	public boolean writeQAComment(QAComment qacomment);
	public boolean deleteQAComment(int qacommentNum);
	public boolean updateQAComment(int boardNum);
}
