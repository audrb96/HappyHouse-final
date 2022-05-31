package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.News;
import com.ssafy.happyhouse.service.NewsService;

@Controller
@RequestMapping("/News")
public class NewsController {
	
	public static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	NewsService newsService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> retrieveNews() throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		final String stockList = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
		Connection conn = Jsoup.connect(stockList);
		Document document = null;
		document = conn.get();
		try {
			List<News> temp = newsService.NewsList(document);
			logger.debug("news : {} ", temp);
			resultMap.put("news", temp);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error("오류발생" );
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
}
