package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.QABoard;
import com.ssafy.happyhouse.repo.QABoardRepo;

@Service
public class QABoardServiceImpl implements QABoardService {

	@Autowired
	private QABoardRepo qaboardRepo;
	
	@Override
	public List<QABoard> selectQABoard() {
		return qaboardRepo.selectQABoard();
	}

	@Override
	public List<QABoard> selectQABoardById(String id) {
		return qaboardRepo.selectQABoardById(id);
	}

	@Override
	public QABoard selectQABoardByNo(int boardNum) {
		return qaboardRepo.selectQABoardByNo(boardNum);
	}
	
	@Override
	public QABoard selectQABoardByIdNo(Map<String, Integer> keyword) {
		return qaboardRepo.selectQABoardByIdNo(keyword);
	}

	@Override
	public boolean insertQABoard(QABoard qaboard) {
		return qaboardRepo.insertQABoard(qaboard) == 1;
	}

	@Override
	@Transactional
	public boolean updateQABoard(QABoard qaboard) {
		return qaboardRepo.updateQABoard(qaboard) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQABoard(int boardNum) {
		return qaboardRepo.deleteQABoard(boardNum) == 1;
	}

}
