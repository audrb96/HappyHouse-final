package com.ssafy.happyhouse.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> NewsList(Document document) throws IOException {
		Elements selected = document.select("#main_content > div.list_body.newsflash_body >ul.type06_headline >li>dl");
		System.out.println(selected.size());
		String title; //제목
		String content; //글
		String img; //이미지
		String link; //기사 링크
		List<News> newslist = new LinkedList<News>();
		
		for(int i =0; i< selected.size(); i++) {
			Elements titles = selected.get(i).select("dt.photo >a>img");
			if(!titles.isEmpty()) { //사진이 있을 때
				title = selected.get(i).select("dt.photo >a >img").attr("alt");
				link = selected.get(i).select("dt.photo >a").attr("href");
				content = selected.get(i).select("span.lede").text();
				Connection con = Jsoup.connect(link);
				Document doctemp = null;
				doctemp = con.get();
				Elements selectedimg = doctemp.select(".end_photo_org img");
				img = (selectedimg.attr("data-src"));
			}else {
				System.out.println("사진 없믕");
				link = selected.get(i).select("dt>a").attr("href");
				img = "http://cdn.ksilbo.co.kr/news/photo/202008/770672_439915_052.jpg";
				title = selected.get(i).select("dt>a").text();
			}
			
			News news = new News(title, "", img, link);
			newslist.add(news);
			
		}
		
		return(newslist);
	}

}
