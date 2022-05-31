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

import com.ssafy.happyhouse.model.QAComment;
import com.ssafy.happyhouse.service.QACommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/QAComment")
public class QACommentController {

	private final Logger logger = LoggerFactory.getLogger(QACommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QACommentService qacommentService;
	
	@ApiOperation(value = "모든 QA 댓글 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QAComment>> retrieveQAComment() throws Exception {
		logger.debug("retrieveQAComment - 호출");
		return new ResponseEntity<List<QAComment>>(qacommentService.retrieveQAComment(), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 QA댓글의 정보를 반환한다.", response = QAComment.class)    
	@GetMapping("{boardNum}")
	public ResponseEntity<List<QAComment>> detailQAComment(@PathVariable("boardNum") int boardNum) {
		logger.debug("detailQAComment - 호출");
		return new ResponseEntity<List<QAComment>>(qacommentService.detailQAComment(boardNum), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 QA 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQAComment(@RequestBody QAComment qacomment) {
		logger.debug("writeQAComment - 호출");
		logger.debug(qacomment.toString());
		if (qacommentService.writeQAComment(qacomment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


    @PutMapping("{boardNum}")
	public ResponseEntity<String> updateQAComment(@PathVariable("boardNum") int boardNum) {
		logger.debug("updateQAComment - 호출");
		logger.debug("" + boardNum);
//		qacomment.setQacommentNum(qacommentNum);
		
		if (qacommentService.updateQAComment(boardNum)) {
//			System.out.println(""+qaboard.getTitle());
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
//	@PutMapping("{qacommentNum}")
//	public ResponseEntity<String> updateQAComment(@PathVariable("qacommentNum") int qacommentNum,@RequestBody QAComment qacomment) {
//		logger.debug("updateQAComment - 호출");
//		logger.debug("" + qacomment);
//		qacomment.setQacommentNum(qacommentNum);
//		
//		if (qacommentService.updateQAComment(qacomment)) {
////			System.out.println(""+qaboard.getTitle());
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}

    @ApiOperation(value = "글번호에 해당하는 QA 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{qacommentNum}")
	public ResponseEntity<String> deleteQAComment(@PathVariable int qacommentNum) {
		logger.debug("deleteQAComment - 호출");
		if (qacommentService.deleteQAComment(qacommentNum)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    
	
}
