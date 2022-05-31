package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.Notice;
import com.ssafy.happyhouse.repo.NoticeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepo noticeRepo;

    @Override
    public Notice getNotice(int articleNo) {
        return noticeRepo.getNotice(articleNo);
    }

    @Override
    public List<Notice> listNotice() {
        return noticeRepo.listNotice();
    }

    @Override
    public boolean updateNotice(Notice notice) {
        System.out.println("service");
        return noticeRepo.updateNotice(notice) == 1;
    }

    @Override
    public boolean deleteNotice(int articleNo) {
        return noticeRepo.deleteNotice(articleNo) == 1;
    }

    @Override
    public boolean registNotice(Notice notice) {
        return noticeRepo.registNotice(notice) == 1;
    }

	@Override
	public void updateViews(int articleNo) {
		noticeRepo.updateViews(articleNo);
		
	}

	@Override
	public List<Notice> searchNotice(Map<String, String> keyword) {
		return noticeRepo.searchNotice(keyword);
	}
}
