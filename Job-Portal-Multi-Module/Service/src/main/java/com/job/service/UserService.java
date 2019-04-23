package com.job.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.job.dao.UserRepository;
import com.job.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean checkPassword(Long id, String password) {
		Optional<User> user= userRepository.findById(id);
		if (passwordEncoder.matches(password, user.get().getPassword())) {
			return true;
		}
		return false;
	}
	
}
