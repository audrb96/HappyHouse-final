package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.QABoard;

import java.util.List;
import java.util.Map;

public interface QABoardService {
    
    public List<QABoard> selectQABoard();
	public List<QABoard> selectQABoardById(String id);
	public QABoard selectQABoardByNo(int boardNum);
	public QABoard selectQABoardByIdNo(Map<String, Integer> keyword);
	public boolean insertQABoard(QABoard qaboard);
	public boolean updateQABoard(QABoard qaboard);
	public boolean deleteQABoard(int boardNum);

}
