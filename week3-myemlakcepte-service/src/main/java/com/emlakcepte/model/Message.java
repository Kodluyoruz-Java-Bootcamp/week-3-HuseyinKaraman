package com.emlakcepte.model;

import com.emlakcepte.model.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message {
	
	private Long id;
	private String title;
	private String content;
	private User from;
	private String fromName;
	private User to;
	private String toName;
	
	public Message(String title, String content) {
		this.id = IdGenerator.getMessageIdGenerator();
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonIgnore
	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.fromName = from.getName();
		this.from = from;
	}

	@JsonIgnore
	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.toName = to.getName();
		this.to = to;
	}
	
	public String getFromName() {
		return fromName;
	}

	public String getToName() {
		return toName;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", content=" + content + ", from=" + from.getName() + ", to=" + to.getName()
				+ "]";
	}

}
