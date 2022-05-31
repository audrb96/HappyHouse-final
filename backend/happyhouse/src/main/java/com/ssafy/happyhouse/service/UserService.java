package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.User;

import java.util.NoSuchElementException;


public interface UserService {
	User select(String id) throws NoSuchElementException;
	boolean join(User user);
	boolean Modify(User user);
	boolean DeleteId(String id);
	User login(User user) throws Exception;
}
