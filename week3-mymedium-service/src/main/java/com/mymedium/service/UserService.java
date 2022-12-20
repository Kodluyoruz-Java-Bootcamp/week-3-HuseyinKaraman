package com.mymedium.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymedium.model.Blog;
import com.mymedium.model.User;
import com.mymedium.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;

	/**
	 * User'i kayıt eder.
	 * 
	 * @param user
	 * @return 
	 */
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}

	/**
	 * User'i siler.
	 * 
	 * @param user
	 * @return 
	 */
	public boolean removeUser(int userId) {
		User user = findUserById(userId);
		if (checkUser(user)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunamadi!");
			return false;
		}
		return userDao.removeUser(user);
	}

	/**
	 * Butun User'ları verir.
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	/**
	 * istenilen id'ye göre User'i verir.
	 * 
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id) {
		return userDao.findById(id);
	}
	
	/**
	 * takip edecek olan user'a ait userId ve takip edilecek user'a id'ye ait
	 * kullanıcı takip edilir.
	 * 
	 * @param user
	 * @param userId
	 */
	public boolean followUserById(int followerId, int followingId) {
		if (followerId == followingId) return false;
		List<User> users = userDao.findByIds(followerId,followingId);		
		if (checkUser(users.get(0)) && checkUser(users.get(1))) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunamadi!");
			return false;
		}
		userDao.followUserById(users.get(0),users.get(1));
		return true;
	}

	public boolean followUserByName(String followerName, String followingName) {
		User user = findUserByName(followerName);
		User user2 = findUserByName(followingName);
		if (followerName.equals(followingName)) {
			System.err.println("Kendini takip edemezsin!");
			return false;
		}
		if (checkUser(user) && checkUser(user2)) {
			System.err.println("Girilen Name'e ait kullanıcı bulunamadi!");
			return false;
		}

		userDao.followUserById(user, user2);
		return true;
	}

	private User findUserByName(String name) {
		return userDao.findByName(name);
	}

	/**
	 * User Girilen tag'i takip eder.
	 * 
	 * @param user
	 * @param tag
	 */
	public boolean subscribeTag(int userId, String tag) {
		User user = findUserById(userId);
		if (checkUser(user)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunamadi!");
			return false;
		}
		return userDao.subscribeTag(user, tag);
	}

	/**
	 * OLuşturulmus blog'u kullanıcı property'sine atanır.
	 * 
	 * @param user
	 * @param blog
	 */
	public boolean addBlog(int userId, Blog blog) {
		User user = findUserById(userId);
		if (checkUser(user)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunamadi!");
			return false;
		}
		return userDao.addBlog(user, blog);
	}

	/**
	 * var olan blog'u kullanıcının blog listesinden id'si silinir.
	 */
	public boolean removeBlog(User user, Integer blogId) {
		return userDao.removeBlog(user, blogId);
	}

	private boolean checkUser(User user) {
		return Objects.isNull(user);
	}

}
