package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.Comment;

public interface CommentService {

	public List<Comment> retrieveComment();
	public List<Comment> detailComment(int articleNo);
	public boolean writeComment(Comment comment);
	public boolean updateComment(Comment comment);
	public boolean deleteComment(int commentNum);

}
