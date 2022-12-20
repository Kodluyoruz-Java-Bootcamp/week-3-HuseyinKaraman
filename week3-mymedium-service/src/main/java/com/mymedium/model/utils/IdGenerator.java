package com.mymedium.model.utils;

public class IdGenerator {
	
	private static Integer userIdGenerator = 1;
	private static Integer blogIdGenerator = 100;
	
	private IdGenerator() {}
	
	public static Integer getUserIdGenerator() {
		return userIdGenerator++;
	}
	public static Integer getBlogIdGenerator() {
		return blogIdGenerator++;
	}
	
}
