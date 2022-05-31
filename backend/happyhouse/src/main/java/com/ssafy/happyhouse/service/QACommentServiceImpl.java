package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.QAComment;
import com.ssafy.happyhouse.repo.QACommentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QACommentServiceImpl implements QACommentService {
	
	@Autowired
	public QACommentRepo qacommentRepo;

	@Override
	public List<QAComment> retrieveQAComment() {
		return qacommentRepo.selectQAComment();
	}

	@Override
	public List<QAComment> detailQAComment(int boardNum) {
		return qacommentRepo.selectQACommentByNo(boardNum);
	}

	@Override
	public boolean writeQAComment(QAComment qacomment) {
		return qacommentRepo.insertQAComment(qacomment) == 1;
	}

	@Override
	public boolean deleteQAComment(int qacommentNum) {
		return qacommentRepo.deleteQAComment(qacommentNum) == 1;
	}
	
	@Override
	public boolean updateQAComment(int boardNum) {
		return qacommentRepo.updateQAComment(boardNum) == 1;
	}

}
