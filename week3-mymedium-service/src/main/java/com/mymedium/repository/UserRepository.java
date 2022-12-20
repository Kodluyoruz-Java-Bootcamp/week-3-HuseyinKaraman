package com.mymedium.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.mymedium.model.Blog;
import com.mymedium.model.User;

@Repository
public class UserRepository {

	private static List<User> userList = new ArrayList<>();

	/** User'i kayıt eder.
	 * @param user
	 * @return 
	 */
	public boolean createUser(User user) {
		return userList.add(user);
	}
	
	/** 
	 * @Note : User'i siler.
	 * @param user
	 * @return 
	 */
	public boolean removeUser(User user) {
		return userList.remove(user);
	}

	/**
	 * @Note : Butun User'ları verir.
	 * @return
	 */
	public List<User> findAll() {
		return userList;
	}

	/** @Note : Girilen id'ye göre User'i verir.
	 * @param id
	 * @return
	 */
	public User findById(Integer id) {
		Optional<User> findFirst = userList.stream().filter(it -> Objects.equals(it.getUserId(), id)).findFirst();
		return !findFirst.isEmpty() ? findFirst.get() : null;
	}
	
	/** 
	 * @Note : Girilen idlere göre User listesi verir
	 * @param id
	 * @return
	 */
	public List<User> findByIds(Integer id,Integer id2) {
		return Arrays.asList(findById(id),findById(id2));
	}

	/** 
	  * @Note : Girilen name'e göre User'i verir.
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
		Optional<User> findFirst = userList.stream().filter(it -> Objects.equals(it.getUsername(), name)).findFirst();
		return !findFirst.isEmpty() ? findFirst.get() : null;
	}
	
	/** 
	 *  @Note : girilen ilk user'in takip ettikleri listesine ikinci user'in id'si eklenir
	 *  girilen ikinci user'in takipçi listesine ilk user'in id'si eklenir
	 * @param user
	 * @param userId
	 */
	public boolean followUserById(User user,User user2) {
		return user.getFollowingIds().add(user2.getUserId()) && user2.getFollowersIds().add(user.getUserId());
	}
	
	/**
 	 * @Note :User Girilen tag'i takip eder.
	 * @param user
	 * @param tag
	 */
	public boolean subscribeTag(User user, String tag) {
		return user.getTags().add(tag);
	}

	/** @Note : OLuşturulmus blog'u kullanıcı blog listesine eklenir.
	 */
	public boolean addBlog(User user,Blog blog) {
		return user.getBlogIds().add(blog.getBlogId());
	}

	/** @Note : var olan blog'u kullanıcının blog listesinden id'si silinir.
	 */
	public boolean removeBlog(User user,Integer blogId) {
		return user.getBlogIds().remove(blogId);
	}

}
