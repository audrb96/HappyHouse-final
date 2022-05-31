package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.QABoard;
import com.ssafy.happyhouse.service.QABoardService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/QA")
public class QABoardController {

	private final Logger logger = LoggerFactory.getLogger(QABoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
    private QABoardService qaboardService;
	
	@ApiOperation(value = "모든 Q&A의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QABoard>> selectQABoard() throws Exception {
		logger.debug("selectQABoard - 호출");
		return new ResponseEntity<List<QABoard>>(qaboardService.selectQABoard(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자에 해당하는 Q&A의 정보 리스트를 반환한다.", response = List.class)    
	@GetMapping("{id}")
	public ResponseEntity<List<QABoard>> selectQABoardById(@PathVariable String id) {
		logger.debug("selectQABoardById - 호출");
		return new ResponseEntity<List<QABoard>>(qaboardService.selectQABoardById(id), HttpStatus.OK);
	}

//    @ApiOperation(value = "글번호에 해당하는 Q&A의 정보를 반환한다.", response = QABoard.class)    
//	@GetMapping("{boardNum}")
//	public ResponseEntity<QABoard> selectQABoardByNo( @PathVariable int boardNum) {
//		logger.debug("selectQABoardByNo - 호출");
//		return new ResponseEntity<QABoard>(qaboardService.selectQABoardByNo(boardNum), HttpStatus.OK);
//	}

    @ApiOperation(value = "글번호에 해당하는 Q&A의 정보를 반환한다.", response = QABoard.class)    
	@GetMapping("{id}/{boardNum}")
	public ResponseEntity<QABoard> selectQABoardByIdNo(@PathVariable("id") String id, @PathVariable("boardNum") int boardNum) {
		logger.debug("selectQABoardByIdNo - 호출");
		Map<String, Integer> keyword = new HashMap<>();
		keyword.put("boardNum", boardNum);
		return new ResponseEntity<QABoard>(qaboardService.selectQABoardByIdNo(keyword), HttpStatus.OK);
	}

    
    @ApiOperation(value = "새로운 Q&A 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertQABoard(@RequestBody QABoard qaboard) {
		logger.debug("insertQABoard - 호출");
		if (qaboardService.insertQABoard(qaboard)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{boardNum}")
	public ResponseEntity<String> updateQABoard(@RequestBody QABoard qaboard) {
		logger.debug("updateQABoard - 호출");
		logger.debug("" + qaboard);
//		qaboard.setBoardNum(boardNum);
		if (qaboardService.updateQABoard(qaboard)) {
//			System.out.println(""+qaboard.getTitle());
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{boardNum}")
	public ResponseEntity<String> deleteQABoard(@PathVariable int boardNum) {
		logger.debug("deleteQABoard - 호출");
		if (qaboardService.deleteQABoard(boardNum)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
