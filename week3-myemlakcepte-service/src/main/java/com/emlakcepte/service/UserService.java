package com.emlakcepte.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;
import com.emlakcepte.repository.UserRepository;

@Service
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @Note:Oluşturulan user kayıt edilir.
	 */
	public void createUser(User user) {						
		userRepository.createUser(user);		
	}
	
	/**
	 * @Note: butun user'ları verir
	 * @return
	 */
	public List<User> getAllUser() {
		return userRepository.findAllUsers();
	}
	
	public void printAllUser() {
		getAllUser().forEach(user -> System.out.println(user.getName()));
	}

	/**
	 * @Note: Girilen userId'e ait user'i doner
	 * @return
	 */
	public User findById(Integer userId) {
		return userRepository.findUserById(userId);
	}
	
	/**
	 * @Note: fromUser ve toUser'in messagelistesine message kayıt edilir
	 * @return
	 */
	public boolean saveMessage(User from,User to,Message message) {
		return userRepository.saveMessage(from, to, message);
	}
	
	/**
	 * @Note: null kontrolü yapılır
	 * @return
	 */
	public boolean checkUser(User user) {
		return Objects.isNull(user);
	}
}
