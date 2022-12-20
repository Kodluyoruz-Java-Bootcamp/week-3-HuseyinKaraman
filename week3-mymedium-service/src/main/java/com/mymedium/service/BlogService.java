package com.mymedium.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymedium.model.Blog;
import com.mymedium.model.User;
import com.mymedium.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogDao;

	@Autowired
	private UserService userService;

	/**
	 * Yeni Blog olusturur
	 * 
	 * @param blog
	 */
	public boolean createBlog(Blog blog) {
		User findUserById = userService.findUserById(blog.getUserId());
		if (checkUser(findUserById)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunmamaktadır.");
			return false;
		}
		userService.addBlog(blog.getUserId(), blog); // Kullanıcının blog listesine eklenir!
		return blogDao.saveBlog(blog);
	}

	/**
	 * Var olan Blog'u siler.
	 * 
	 * @param blog
	 * @return
	 */
	public boolean removeBlog(Blog blog) {
		User findUserById = userService.findUserById(blog.getUserId());
		if (checkUser(findUserById)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunmamaktadır.");
			return false;
		}
		// eger girilen userId'e ait blog silinirse, blog listesinden o blog silinir.
		return userService.removeBlog(findUserById, blog.getBlogId()) ? blogDao.removeBlog(blog) : false;
	}

	/**
	 * Tüm Blogları verir.
	 * 
	 * @return
	 */
	public List<Blog> getAllBlog() {
		return blogDao.findAll();
	}

	/**
	 * Tüm Blogları yazdırır.
	 * 
	 * @return
	 */
	public void printBlogs() {
		getAllBlog().forEach(System.out::println);
	}

	/**
	 * BlogId göre istenilen Blog'u bulur.
	 * 
	 * @param blogId
	 * @return
	 */
	public Blog findBlogByBlogId(Integer blogId) {
		return blogDao.findBlogByBlogId(blogId);
	}

	/**
	 * Girilen userId'ye göre User'in Blog'larını verir.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Blog> findBlogsByUserId(Integer userId) {
		return blogDao.findBlogsByUserId(userId);
	}

	/**
	 * Girilen userId'ye göre User'in abone oldugu taglari verir.
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> findTagsByUserId(Integer userId) {
		return blogDao.findTagsByUserId(userId);
	}

	/**
	 * Girilen userId'ye göre User'in Blog'larının Id sini verir.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> findBlogsIdByUserId(Integer userId) {
		return blogDao.findBlogsIdByUserId(userId);
	}

	private boolean checkUser(User user) {
		return Objects.isNull(user);
	}

}
