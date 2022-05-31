package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Comment;
import com.ssafy.happyhouse.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Comment")
public class CommentController {

	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "모든 댓글 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Comment>> retrieveComment() throws Exception {
		logger.debug("retrieveComment - 호출");
		return new ResponseEntity<List<Comment>>(commentService.retrieveComment(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 댓글의 정보를 반환한다.", response = Comment.class)    
	@GetMapping("{articleNo}")
	public ResponseEntity<List<Comment>> detailComment(@PathVariable int articleNo) {
		logger.debug("detailComment - 호출");
		return new ResponseEntity<List<Comment>>(commentService.detailComment(articleNo), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeComment(@RequestBody Comment comment) {
		logger.debug("writeComment - 호출");
		logger.debug(comment.toString());
		if (commentService.writeComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 댓글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{commentNum}")
	public ResponseEntity<String> updateComment(@PathVariable("commentNum") int commentNum,@RequestBody Comment comment) {
		logger.debug("updateComment - 호출");
		logger.debug("" + comment);
		comment.setCommentNum(commentNum);
		
		if (commentService.updateComment(comment)) {
//			System.out.println(""+qaboard.getTitle());
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{commentNum}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentNum) {
		logger.debug("deleteComment - 호출");
		if (commentService.deleteComment(commentNum)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	
}
