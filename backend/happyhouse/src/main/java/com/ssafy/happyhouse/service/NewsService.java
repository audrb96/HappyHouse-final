package com.ssafy.happyhouse.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.ssafy.happyhouse.model.News;

public interface NewsService {

	List<News> NewsList(Document document) throws IOException;
	
}
