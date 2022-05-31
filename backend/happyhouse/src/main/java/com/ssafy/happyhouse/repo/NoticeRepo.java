package com.ssafy.happyhouse.repo;

import com.ssafy.happyhouse.model.Notice;
import java.util.List;
import java.util.Map;

public interface NoticeRepo {
    Notice getNotice(int articleNo);
    List<Notice> listNotice();
    int updateNotice(Notice notice);
    int deleteNotice(int articleNo);
    int registNotice(Notice notice);
    void updateViews(int articleNo);
    List<Notice> searchNotice(Map<String, String> keyword);
}
