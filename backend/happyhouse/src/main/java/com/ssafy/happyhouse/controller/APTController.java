package com.ssafy.happyhouse.controller;

import java.util.*;
import javax.servlet.http.HttpServlet;

import com.ssafy.happyhouse.model.*;
import com.ssafy.happyhouse.service.APTService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apt")
public class APTController {

	private final Logger logger = LoggerFactory.getLogger(APTController.class);
	
	@Autowired
	private APTService aptService;

	@ApiOperation(value = "시도 정보 보기", notes = "시도 코드, 시도 이름 정보를 반환한다.", response = List.class)    
	@GetMapping("/sido")
	public ResponseEntity<List<APTSidoGugun>> sido() throws Exception {
		return new ResponseEntity<List<APTSidoGugun>>(aptService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보 보기", notes = "시도코드에 해당하는 구군 정보를 반환한다.", response = List.class)   
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<APTSidoGugun>> gugun(@PathVariable("sido") String sido) throws Exception {
		return new ResponseEntity<List<APTSidoGugun>>(aptService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보 보기", notes = "구군코드에 해당하는 동 정보를 반환한다.", response = List.class)   
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<?> dong(@PathVariable("gugun") String gugun) throws Exception{
		return new ResponseEntity<>(aptService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 정보 보기", notes = "동코드에 해당하는 아파트 정보를 반환한다.", response = List.class)   
	@GetMapping("/aptlist/{dong}")
	public ResponseEntity<List<APT>> apt(@PathVariable("dong") String dong) throws Exception {
		return new ResponseEntity<List<APT>>(aptService.getAptInDong(dong), HttpStatus.OK);
	}

	@GetMapping("/baseAddress/{dong}")
	public ResponseEntity<?> baseAddress(@PathVariable("dong") String dong) throws Exception {
		return new ResponseEntity<>(aptService.dongLocation(dong), HttpStatus.OK);
	}

	@PostMapping("/interest")
	public ResponseEntity<?> insertInterest(@RequestBody InterestApt apt) {
		Map<String,String> result = new HashMap<>();
		System.out.println(apt);
		if(aptService.insertInterestApt(apt) == 1) {
			result.put("message", "success");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.put("message", "fail");
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/aptListByInterest/{userid}")
	public ResponseEntity<?> getAptListByInterest(@PathVariable("userid") String userid) {
		Map<String, Object> result = new HashMap<>();
		List<APT> list = new ArrayList<>();
		HttpStatus status;
		try {
			if(userid != null){
				list = aptService.getAptListByInterest(userid);
			}
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		result.put("aptList",list);

		return new ResponseEntity<>(list, status);
	}
	@GetMapping("/interest/{userid}")
	public ResponseEntity<?> getInterestApt(@PathVariable("userid") String userid) {
		List<InterestApt> list = aptService.getInterestApt(userid);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@DeleteMapping("/interest/{userid}/{aptCode}")
	public ResponseEntity<?> deleteInterestApt(@PathVariable("userid") String userid,@PathVariable("aptCode") String aptCode) {
		Map<String,String> input = new HashMap<>();
		input.put("userid", userid);
		input.put("aptCode", aptCode);
		Map<String,String> resultMap = new HashMap<>();
		HttpStatus status = null;

		if(aptService.deleteInterestApt(input) == 1) {
			resultMap.put("message", "success");
			status = HttpStatus.OK;
		}else {
			resultMap.put("message", "fail");
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<>(resultMap, status);
	}
	@GetMapping("/interest/top5Apt")
	public ResponseEntity<?> top5Apt() {
		try{
			List<APT> list = aptService.top5Apt();
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("avg/{sigugun}")
	public ResponseEntity<?> avgByDong(@PathVariable("sigugun") String sigugun) {
		List<AvgByDong> list = null;
		HttpStatus status = null;
		try {
			list = aptService.avgByDong(sigugun);
			status = HttpStatus.OK;
		}
		catch(Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(list,status);
	}

	/*
	@GetMapping("/{dong}")
	private ResponseEntity<?> doSearch(@PathVariable("dong") String dong) {

		BaseAddress baseAddress = aptService.dongLocation(dong);
		List<APT> aptList = aptService.search(dong);
		Map<String,Object> map = new HashMap<>();
		map.put("baseAddress", baseAddress);
		map.put("list", aptList);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	*/
}
