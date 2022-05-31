package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.Notice;
import com.ssafy.happyhouse.model.User;
import com.ssafy.happyhouse.service.NoticeService;

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
@RequestMapping("/notice")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
    private NoticeService noticeService;

	/*
	@ApiOperation(value = "", notes = "", response = String.class)
    @GetMapping("/mvNotice")
    private String mvNotice() {
        return "notice";
    }

	@ApiOperation(value = "", notes = "", response = String.class)
    @GetMapping("/mvRegist")
    private String mvRegist(){
        return "noticeRegist";
    }
	*/
	
	@ApiOperation(value = "게시판 글등록", notes = "새로운 게시글 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registNotice(@RequestBody Notice notice) {
		logger.debug("registNotice - 호출");
		if (noticeService.registNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	/*
    @PostMapping("/registNotice")
    private String registNotice(@RequestParam Map<String, String> map, HttpSession session,Model model) {
        String title = map.get("title");
        String text = map.get("text");
        User user = (User) session.getAttribute("userinfo");
        System.out.println(title+" " + text);
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        Notice notice = new Notice(title,user.getID(),timestamp,0,text);
        noticeService.registNotice(notice);
        return "redirect:/notice/mvNotice";
    }
	*/
	@ApiOperation(value = "게시판 전체 글목록 보기", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Notice>> getNoticeList() throws Exception {
		logger.debug("getNoticeList - 호출");
		return new ResponseEntity<List<Notice>>(noticeService.listNotice(), HttpStatus.OK);
	}
	
	/*
    @GetMapping("/noticeList")
    private @ResponseBody ResponseEntity<?>  getNoticeList() {
        try{
            List<Notice> noticeList = noticeService.listNotice();
            Map<String,Object> map = new HashMap<>();
            map.put("noticeList", noticeList);

            Collections.sort(noticeList, new Comparator<Notice>() {
                @Override
                public int compare(Notice o1, Notice o2) {
                    return o2.getViews() - o1.getViews();
                }
            });

            List<Notice> favorite = new ArrayList<>();
            if(noticeList.size() >=3){
                for(int i =0;i<3;i++){
                        favorite.add(noticeList.get(i));
                }
                map.put("favorite", favorite);
            }


            if(noticeList != null && !noticeList.isEmpty()) {
                return new ResponseEntity<>(map,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	 */

	@ApiOperation(value = "게시판 글 상세보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Notice.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<Notice> detailNotice(@PathVariable int articleno) {
		logger.debug("detailNotice - 호출");
		Notice notice = noticeService.getNotice(articleno);
		notice.setViews(notice.getViews()+1);
		noticeService.updateNotice(notice);
		return new ResponseEntity<Notice>(noticeService.getNotice(articleno), HttpStatus.OK);
	}
	
	/*
    @GetMapping("/mvResult/{articleNo}")
    private String mvResult(@PathVariable("articleNo") String articleNo,Model model){
        Notice notice = noticeService.getNotice(Integer.parseInt(articleNo));
        model.addAttribute("notice", notice);
        return "noticeResult";
    }
    */

    @ApiOperation(value = "게시글 삭제", notes = "글번호에 해당되는 게시글 삭제", response = String.class)
    @DeleteMapping("{articleno}")
    public ResponseEntity<String> deleteNotice(@PathVariable int articleno) {
		logger.debug("deleteNotice - 호출");
		if (noticeService.deleteNotice(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    /*
    @GetMapping("/delete/{articleNo}")
    private String deleteNotice(@PathVariable("articleNo") String articleNo) {
        noticeService.deleteNotice(Integer.parseInt(articleNo));
        return "redirect:/notice/mvNotice";
    }
    */

    @ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
   	@PutMapping("{articleno}")
   	public ResponseEntity<String> updateNotice(@RequestBody Notice notice) {
   		logger.debug("updateNotice - 호출");
   		if (noticeService.updateNotice(notice)) {
   			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
   		}
   		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
   	}
    /*
    @GetMapping("/modify/{articleNo}")
    private String modifyNotice(@PathVariable("articleNo") String articleNo, Model model) {
    	Notice notice = noticeService.getNotice(Integer.parseInt(articleNo));
    	model.addAttribute("notice", notice);
        return "noticeModify";
    }
    
    @PostMapping("/modify")
    private String updateNotice(@RequestParam Map<String, String> map, HttpSession session) {
    	String title = map.get("title");
        String text = map.get("text");
        String articleNo = map.get("articleNo");
        User user = (User) session.getAttribute("userinfo");
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        Notice notice = new Notice(title, user.getID(), timestamp, 0,text);
		notice.setArticleNo(Integer.parseInt(articleNo));
        noticeService.updateNotice(notice);
        return "redirect:/notice/mvNotice";
        
    }
    */
    
    @ApiOperation(value = "게시판 검색 글목록 보기", notes = "검색 게시글의 정보를 반환한다.", response = List.class)
    @GetMapping("{key}/{word}")
    public ResponseEntity<?> searchNotice(@PathVariable("key") String key, @PathVariable("word") String word) {
    	logger.debug("searchNotice - 호출");
    	Map<String, String> keyword = new HashMap<>();
    	keyword.put("key", key);
    	keyword.put("word", word);
    	return new ResponseEntity<>(noticeService.searchNotice(keyword), HttpStatus.OK);
    }
}
