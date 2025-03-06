package com.wego.iwan.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wego.iwan.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
		if ("iwan-test".equals(username)) {
			return new User("iwan-test", password,
					new ArrayList<>());
		} else {
			
			com.wego.iwan.model.User existUser = userRepository.getContactByUserName(username);
			if(existUser == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);	
			}

			password = existUser.getPassword();

			return new User(username, password,
					new ArrayList<>());
		}
	}
}

