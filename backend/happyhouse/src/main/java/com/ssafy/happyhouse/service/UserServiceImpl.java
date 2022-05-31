package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.User;
import com.ssafy.happyhouse.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	
	@Override
	public User select(String id) throws NoSuchElementException {
		return userRepo.select(id);
	}

	@Override
	public boolean join(User user) {
		return userRepo.join(user) == 1;
	}

	@Override
	public boolean Modify(User user) {
		return userRepo.update(user) == 1;
	}

	@Override
	public boolean DeleteId(String id) {
		return userRepo.delete(id) == 1;
	}

	@Override
	public User login(User user) throws Exception {
		if(user.getID() == null || user.getPassword() == null)
			return null;
		return userRepo.login(user);
	}


}
