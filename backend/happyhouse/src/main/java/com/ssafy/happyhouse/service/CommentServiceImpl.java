package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Comment;
import com.ssafy.happyhouse.repo.CommentRepo;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	@Autowired
	public CommentRepo commentRepo;
	
	@Override
	public List<Comment> retrieveComment() {
		return commentRepo.selectComment(); 
	}

	@Override
	public List<Comment> detailComment(int articleNo) {
		return commentRepo.selectCommentByNo(articleNo);
	}

	@Override
	public boolean writeComment(Comment comment) {
		return commentRepo.insertComment(comment) == 1;
	}

	@Override
	public boolean updateComment(Comment comment) {
		return commentRepo.updateComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentNum) {
		return commentRepo.deleteComment(commentNum) == 1;
	}

}
