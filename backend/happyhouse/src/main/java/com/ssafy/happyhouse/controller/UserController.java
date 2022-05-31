package com.ssafy.happyhouse.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import com.ssafy.happyhouse.model.User;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class UserController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController  {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;
	private final JwtService jwtService;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";


	@DeleteMapping("{id}")
	private ResponseEntity<?> doDeleteid(@PathVariable String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		if(userService.DeleteId(id)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		}
		else {
			resultMap.put("message",FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(resultMap,status);
	}

	@PutMapping
	private ResponseEntity<?> doModify(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if(userService.Modify(user)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/pwFind")
	private ResponseEntity<?> doPwFind(@RequestBody Map<String, String> map) {
		String id = map.get("id");
		String email = map.get("email");
		String phone = map.get("phone");
		User user = userService.select(id);
		System.out.println(map.get("id") +" "+ map.get("email") +" " + map.get("phone"));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		if (user != null) {
			if (email.equals(user.getEmail()) && phone.equals(user.getPhone())) {
				resultMap.put("password", user.getPassword());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			}
			else {
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(resultMap,status);

	}

	@PostMapping("join")
	private ResponseEntity<?> doJoin(@RequestBody User user) {
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		user.setCreate_time(timestamp);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		if(userService.join(user)) {
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} else {
			resultMap.put("message",FAIL);
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(resultMap,status);
	}

	@PostMapping("/login")
	private ResponseEntity<?> doLogin(@RequestBody User user)  {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(user);
		try {
			User loginUser = userService.login(user);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getID(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") String userid,
			HttpServletRequest request) {
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User memberDto = userService.select(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


}
