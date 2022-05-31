package com.ssafy.happyhouse.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.APT;
import com.ssafy.happyhouse.model.APTSidoGugun;
import com.ssafy.happyhouse.model.BaseAddress;
import com.ssafy.happyhouse.model.QAComment;

public interface QACommentRepo {
	
	public List<QAComment> selectQAComment();
	public List<QAComment> selectQACommentByNo(int boardNum); //해당 게시글의 댓글들
	public int insertQAComment(QAComment qacomment);
	public int deleteQAComment(int qacommentNum); //comment의 순번
	public int updateQAComment(int boardNum); //해당 게시물에 있는 board 댓글 수 업뎃

}
