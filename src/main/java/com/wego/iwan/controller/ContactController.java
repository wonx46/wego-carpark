package com.wego.iwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.wego.iwan.model.User;
import com.wego.iwan.services.WegoServices;
import com.wego.iwan.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContactController {
	

	private final WegoServices services;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	 @PostMapping("/v1/create_user")
	 public ResponseEntity<?>  createContact(@RequestBody User user) {
	      return  services.createUser(user);
	  }
	 
	 @GetMapping("/v1/list_user")
	 public ResponseEntity<?>  getAllUser() {
	      return  services.getAllUser();
	  }
	 
}
