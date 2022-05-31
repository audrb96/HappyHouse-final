package com.ssafy.happyhouse.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.APT;
import com.ssafy.happyhouse.model.APTSidoGugun;
import com.ssafy.happyhouse.model.BaseAddress;
import com.ssafy.happyhouse.model.Comment;

public interface CommentRepo {
	
	public List<Comment> selectComment();
	public List<Comment> selectCommentByNo(int articleNo); //해당 게시글의 댓글들
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int commentNum); //comment의 순번

}
