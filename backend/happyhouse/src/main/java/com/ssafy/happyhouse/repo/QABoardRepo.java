package com.ssafy.happyhouse.repo;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QABoard;

public interface QABoardRepo {
	
	public List<QABoard> selectQABoard();
	public List<QABoard> selectQABoardById(String id);
	public QABoard selectQABoardByNo(int boardNum);
	public QABoard selectQABoardByIdNo(Map<String, Integer> keyword);
	public int insertQABoard(QABoard qaboard);
	public int updateQABoard(QABoard qaboard);
	public int deleteQABoard(int boardNum);

}
