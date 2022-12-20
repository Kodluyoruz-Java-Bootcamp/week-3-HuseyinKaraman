package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;

@Repository
public class UserRepository {

	private static List<User> userList = new ArrayList<>();

	public void createUser(User user) {
		userList.add(user);
	}

	public List<User> findAllUsers() {
		return userList;
	}

	public User findUserById(Integer userId) {
		List<User> list = userList.stream().filter(it -> it.getUserId() == userId).toList();
		return !list.isEmpty() ? list.get(0) : null;
	}
	
	public boolean saveMessage(User from,User to,Message message) {
		return from.getMessages().add(message) && to.getMessages().add(message);
	}

}
