package com.job.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.dao.UserRepository;
import com.job.entity.User;
import com.job.message.request.PasswordForm;
import com.job.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestContoller {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	//get user
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userRepository.getOne(id);
	}
	
	//update user
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/users")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		userRepository.save(user);
		return ResponseEntity.ok().body("User updated successfully!");
		
	}
	

	//update password
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/users/password")
	public ResponseEntity<String> updatePassword(@RequestBody PasswordForm user) {
		if (userService.checkPassword(user.getId(), user.getOldPassword())) {
			//user.setPassword(passwordEncoder.encode(user.getOldPassword()));
			//userRepository.save(user);
			userRepository.updatePassword(user.getId(), passwordEncoder.encode(user.getNewPassword()));
			return ResponseEntity.ok().body("Password updated successfully!");
		}
		return new ResponseEntity<String>("Fail -> Password is not correct!",
                HttpStatus.BAD_REQUEST);
	}
	
	

//	@CrossOrigin("http://localhost:4200")
//	@PostMapping("/users/checkPassword")
//	public Boolean checkUserPassword(@RequestBody User user) {
//		if (userService.checkPassword(user.getId(), user.getPassword())) {
//			return ResponseEntity.ok().body("User registered successfully!");
//		}
//		return new ResponseEntity<String>("Fail -> Password is not correct!", HttpStatus.BAD_REQUEST);
//	}
//		return userService.checkPassword(user.getId(), user.getPassword());
//	}
}
