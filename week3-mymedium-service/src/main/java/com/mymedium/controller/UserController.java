package com.mymedium.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedium.model.User;
import com.mymedium.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAll() {
		return userService.getAllUser();
	}
	
	/**
	 * @Note : Kullanıcıyı oluşturur.
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return userService.createUser(user) ? new ResponseEntity<>(user, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @Note: girilen Id'ye ait kullanıcı silinir
	 * @param userId
	 * @return
	 */
	@PostMapping(value = "/{userId}")
	public ResponseEntity<User> findById(@PathVariable Integer userId) {
		User findUserById = userService.findUserById(userId);
		return  Objects.nonNull(findUserById) ?  new ResponseEntity<>(findUserById,HttpStatus.OK)  : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * @Note: girilen Id'ye ait kullanıcı silinir
	 * @param userId
	 * @return
	 */
	@PostMapping(value = "/delete/{userId}")
	public ResponseEntity<User> delete(@PathVariable Integer userId) {
		return userService.removeUser(userId) ?  new ResponseEntity<>(HttpStatus.OK)  : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	/**
	 * @Note: girilen Id'li user , Id'si girilen user'i takip eder
	 * @param followerId
	 * @param followingId
	 * @return
	 */
	@PostMapping(value = "/followById/{followerId}/{followingId}")
	public ResponseEntity<User> followById(@PathVariable Integer followerId,@PathVariable Integer followingId) {
		return  userService.followUserById(followerId, followingId) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @Note:  girilen name'e ait user , name'i girilen user'i takip eder
	 * @param followerName
	 * @param followingName
	 * @return
	 */
	@PostMapping(value = "/followByName/{followerName}/{followingName}")
	public ResponseEntity<User> followByName(@PathVariable String followerName,@PathVariable String followingName) {
		return  userService.followUserByName(followerName, followingName) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @Note : Id'si girilen user girilen tag 'a abone olur.
	 * @param userId
	 * @param tag
	 * @return
	 */
	@PostMapping(value = "/subscribe/{userId}/{tag}")
	public ResponseEntity<User> subscribe(@PathVariable Integer userId,@PathVariable String tag) {
		return  userService.subscribeTag(userId, tag) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
}
