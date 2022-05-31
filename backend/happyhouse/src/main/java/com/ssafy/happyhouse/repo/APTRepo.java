package com.ssafy.happyhouse.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.*;

public interface APTRepo {
	
	List<APTSidoGugun> getSido() throws SQLException;
	List<APTSidoGugun> getGugunInSido(String sido) throws SQLException;
	List<Map<String,String>> getDongInGugun(String gugun) throws SQLException;
	List<APT> getAptInDong(String dong) throws SQLException;
	BaseAddress dongLocation(String dong);
	int insertInterestApt(InterestApt apt);
	List<APT> getAptListByInterest(String userid);
	List<InterestApt> getInterestApt(String userid);
	int deleteInterestApt(Map<String,String> map);
	List<APT> top5Apt();
	List<AvgByDong> avgByDong(String sigugun);
//	List<APT> selectAll(String dong);
//	List<APT> selectByPricePerArea(String dong);
}
