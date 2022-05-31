package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.*;
import com.ssafy.happyhouse.repo.APTRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class APTServiceImpl implements APTService {

	private final APTRepo aptRepo;
	
	@Override
	public List<APTSidoGugun> getSido() throws Exception {
		return aptRepo.getSido();
	}

	@Override
	public List<APTSidoGugun> getGugunInSido(String sido) throws Exception {
		return aptRepo.getGugunInSido(sido);
	}

	@Override
	public List<Map<String, String>> getDongInGugun(String gugun) throws Exception {
		return aptRepo.getDongInGugun(gugun);
	}

	@Override
	public List<APT> getAptInDong(String dong) throws Exception {
		return aptRepo.getAptInDong(dong);
	}

	@Override
	public BaseAddress dongLocation(String dong) {
		return aptRepo.dongLocation(dong);
	}

	@Override
	public int insertInterestApt(InterestApt apt) {
		return aptRepo.insertInterestApt(apt);
	}

	@Override
	public List<APT> getAptListByInterest(String userid) {
		return aptRepo.getAptListByInterest(userid);
	}

	@Override
	public List<InterestApt> getInterestApt(String userid) {
		return aptRepo.getInterestApt(userid);
	}

	@Override
	public int deleteInterestApt(Map<String,String> map) {
		return aptRepo.deleteInterestApt(map);
	}

	@Override
	public List<APT> top5Apt() {
		return aptRepo.top5Apt();
	}

	@Override
	public List<AvgByDong> avgByDong(String sigugun) {
		return aptRepo.avgByDong(sigugun);
	}


	
/*
	@Override
	public List<APT> search(String dong) {
		return aptRepo.selectAll(dong);
	}

	@Override
	public List<APT> getAPTByPricePerArea(String dong) {
		return aptRepo.selectByPricePerArea(dong);
	}

	@Override
	public BaseAddress dongLocation(String dong) {
		return aptRepo.dongLocation(dong);
	}
*/

}
