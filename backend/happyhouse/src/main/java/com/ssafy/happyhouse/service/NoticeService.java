package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.Notice;
import com.ssafy.happyhouse.repo.NoticeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    Notice getNotice(int articleNo);
    List<Notice> listNotice();
    boolean updateNotice(Notice notice);
    boolean deleteNotice(int articleNo);
    boolean registNotice(Notice notice);
    void updateViews(int articleNo);
    List<Notice> searchNotice(Map<String, String> keyword);
}
