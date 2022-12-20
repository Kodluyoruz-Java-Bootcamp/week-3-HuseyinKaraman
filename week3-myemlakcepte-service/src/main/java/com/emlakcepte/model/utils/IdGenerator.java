package com.emlakcepte.model.utils;

public class IdGenerator {

	private static long userIdGenerator = 100;
	private static long realtyIdGenerator = 1000;
	private static long messageIdGenerator = 1;

	private IdGenerator() {
	}

	public static long getUserIdGenerator() {
		return userIdGenerator++;
	}

	public static long getRealtyIdGenerator() {
		return realtyIdGenerator++;
	}

	public static long getMessageIdGenerator() {
		return messageIdGenerator++;
	}

}
