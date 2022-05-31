package com.ssafy.happyhouse.repo;

import com.ssafy.happyhouse.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface UserRepo {
	
	int join(User user);
	int update(User user)throws NoSuchElementException;
	int delete(String id) throws NoSuchElementException;
	User select(String id)throws NoSuchElementException;
	User login(User user) throws SQLException;
	List<User> selectAll();

}
