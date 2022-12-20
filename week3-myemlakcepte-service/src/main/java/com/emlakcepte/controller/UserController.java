package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.service.UserService;
import com.emlakcepte.model.User;


@RestController
@RequestMapping("/users")
public class UserController {

	// injection işlemi, spring tarafından oluşan objenin bağlanması. Default tanımı singleton.
	@Autowired 
	private UserService userService;
		
	/** 
	 * @Note: Kayıtlı kullanıcıları verecektir.
	 * @return
	 */
	@GetMapping
	public List<User> getAll() {
		return userService.getAllUser();
	}
	
	/** 
	 * @Note: yeni kullanıcı oluşturur.
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		userService.createUser(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	/** @Note: Id'si girilen kullanıcıyı verir */
	@GetMapping(value = "/{userId}")
	public User findUserById(@PathVariable Integer userId) {
		return userService.findById(userId);
	}
	
}
