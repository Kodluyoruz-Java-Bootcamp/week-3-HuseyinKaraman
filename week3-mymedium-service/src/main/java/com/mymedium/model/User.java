package com.mymedium.model;

import java.util.ArrayList;
import java.util.List;

import com.mymedium.model.utils.IdGenerator;

public class User {

	private final Integer userId;
	private String username;
	private String email;
	private String password;

	private List<Integer> blogIds;
	private List<String> tags;
	private List<Integer> followingIds;
	private List<Integer> followersIds;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userId = IdGenerator.getUserIdGenerator();

		blogIds = new ArrayList<>();
		tags = new ArrayList<>();
		followingIds = new ArrayList<>();
		followersIds = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getBlogIds() {
		return blogIds;
	}

	public void setBlogIds(List<Integer> blogIds) {
		this.blogIds = blogIds;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Integer> getFollowingIds() {
		return followingIds;
	}

	public void setFollowingIds(List<Integer> followingIds) {
		this.followingIds = followingIds;
	}

	public List<Integer> getFollowersIds() {
		return followersIds;
	}

	public void setFollowersIds(List<Integer> followersIds) {
		this.followersIds = followersIds;
	}

	public Integer getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "User [userID=" + userId + ", username=" + username + ", email=" + email + ", tags=" + tags + "]";
	}

}
