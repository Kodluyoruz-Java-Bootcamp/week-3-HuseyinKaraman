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

import com.mymedium.model.Blog;
import com.mymedium.model.User;
import com.mymedium.service.BlogService;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {
	
	@Autowired
	BlogService blogService;

	/**
	 * @Note: butun blogları verir.
	 * @return
	 */
	@GetMapping
	public List<Blog> getAll() {
		return blogService.getAllBlog();
	}

	/**
	 * @Note: blog oluşturulur.
	 * @param blog
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Blog> create(@RequestBody Blog blog) {
		blogService.createBlog(blog);
		return new ResponseEntity<>(blog, HttpStatus.CREATED);
	}

	/**
	 * @Note : Id'si girilen user'in blog listesini verir.
	 * @param userId
	 * @param tag
	 * @return
	 */
	@GetMapping(value = "/{userId}")
	public ResponseEntity<List<Blog>> listUserBlog(@PathVariable Integer userId) {
		List<Blog> blogsByUserId = blogService.findBlogsByUserId(userId);
		return Objects.nonNull(blogsByUserId) ? new ResponseEntity<>(blogsByUserId, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Note : girilen blogId 'e ait blog silinir.
	 * @param blogId
	 * @return
	 */
	@PostMapping("/delete/{blogId}")
	public ResponseEntity<Blog> delete(@PathVariable Integer blogId) {
		Blog blogByBlogId = blogService.findBlogByBlogId(blogId);
		if (Objects.isNull(blogByBlogId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return blogService.removeBlog(blogByBlogId) ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
