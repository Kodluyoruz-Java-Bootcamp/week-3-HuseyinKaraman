package com.emlakcepte.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.emlakcepte.model.enums.UserType;
import com.emlakcepte.model.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private long userId;
	private String name;
	private String mail;
	private String password;
	private UserType type;
	private LocalDateTime createDate;
	private List<Realty> realtyList= new ArrayList<>();;
	private List<Realty> favoriteRealtyList= new ArrayList<>();;
	private List<Message> messages = new ArrayList<>();;
	private List<String> search = new ArrayList<>();;
	
	public User() {
		this("", "", "", UserType.INDIVIDUAL);
	}

	public User(String name, String mail, String password, UserType type) {
		this.userId = IdGenerator.getUserIdGenerator();
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<Realty> getRealtyList() {
		return realtyList;
	}

	public void setRealtyList(List<Realty> realtyList) {
		this.realtyList = realtyList;
	}

	public List<Realty> getFavoriteRealtyList() {
		return favoriteRealtyList;
	}

	public void setFavoriteRealtyList(List<Realty> favoriteRealtyList) {
		this.favoriteRealtyList = favoriteRealtyList;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public long getUserId() {
		return userId;
	}

	public List<String> getSearch() {
		return search;
	}

	public void setSearch(List<String> search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", mail=" + mail + ", type=" + type ;
	}
}
