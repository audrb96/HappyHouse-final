package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.*;

public interface APTService {
	
	List<APTSidoGugun> getSido() throws Exception;
	List<APTSidoGugun> getGugunInSido(String sido) throws Exception;
	List<Map<String,String>> getDongInGugun(String gugun) throws Exception;
	List<APT> getAptInDong(String dong) throws Exception;
	BaseAddress dongLocation(String dong);
	int insertInterestApt(InterestApt apt);
	List<APT> getAptListByInterest(String userid);
	List<InterestApt> getInterestApt(String userid);
	int deleteInterestApt(Map<String,String> map);
	List<APT> top5Apt();
	List<AvgByDong> avgByDong(String sigugun);
//	List<APT> search(String dong);
//	List<APT> getAPTByPricePerArea(String dong);
}
