package com.wego.iwan.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wego.iwan.bean.MessageResponse;
import com.wego.iwan.bean.UserToken;
import com.wego.iwan.model.User;

import com.wego.iwan.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;


@CacheConfig(cacheNames = {
        "flipServices"
})
@Service("flipServices")
@RequiredArgsConstructor
public class WegoServices {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	
	public ResponseEntity<?>  getAllUser(){
		 MessageResponse messageResponse =  new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
		try {
			 List<User> users = userRepository.findAll();
			 messageResponse.setData(users);
			
			 return new ResponseEntity(messageResponse, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			   return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
 	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	public ResponseEntity<?>  createUser(User user) {
		try {
			MessageResponse response = new MessageResponse(MessageResponse.CODE_SUCCESS, MessageResponse.OK);
			
			if(user.getId()==null || user.getId().isEmpty()) {
				user.setGeneratedId();
			}
			
			if(!isValidUserPayload(user)) {
				response.setMessage(MessageResponse.INVALID_PAYLOAD);
				response.setStatus(MessageResponse.ERROR_CODE_400);
				return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
			}
			
			User existUser = userRepository.getContactByUserName(user.getUsername());
			if(existUser != null) {
				response.setMessage(MessageResponse.USERNAME_ALREADY_EXIST);
				response.setStatus(MessageResponse.ERROR_CODE_400);
				return new ResponseEntity(response, HttpStatus.CONFLICT);
			}
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String rawPassword = "password";
			if(user.getPassword()!=null && !user.getPassword().isEmpty()){
				rawPassword = user.getPassword();
			}
        	
        
			// Meng-enkripsi password
			String encodedPassword = passwordEncoder.encode(rawPassword);
			user.setPassword(encodedPassword);

			user.setCreateddate(Calendar.getInstance().getTime());
			userRepository.saveAndFlush(user);
			Date now = new Date();
			
			Map<String, Object> claims = new HashMap<>();
			String jwtToken =  Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(DateUtils.addDays(now, 1))
			.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

			UserToken userToken = new UserToken();
			userToken.setToken(jwtToken);
			
			List<UserToken> list = new ArrayList<UserToken>();
			list.add(userToken);
			response.setData(list);
			return new ResponseEntity(response, HttpStatus.CREATED);
		} catch (Exception e) {
			 return new ResponseEntity(new MessageResponse(MessageResponse.ERROR_CODE_INTERNAL_SERVER,
	                   e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean isValidUserPayload(User user) {
		if(user.getUsername()==null || user.getUsername().isEmpty()) {
			return false;
		}
		if(user.getName()==null || user.getName().isEmpty()) {
			return false;
		}
		if(user.getPhone()==null || user.getPhone().isEmpty()) {
			return false;
		}
		return true;
	}

	
}
