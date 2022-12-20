package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;

@Repository
public class MessageRepository {
		
	private static List<Message> messageList = new ArrayList<>();
	
	/**
	 * @Note: oluşturulan mesaj kayıt edilir
	 * @return
	 */
	public boolean saveMessage(Message message) {
		return messageList.add(message);
	}
	
	/**
	 * @Note: butun mesajları verir.
	 * @return
	 */
	public List<Message> getAllMessage() {
		return messageList;
	}
	
	/** @Note: Gelen yeni mesaj'in propertyleri eski mesajın propertylerine aktarılır.*/
	public Message updateMessageContent(Message newContent,Message oldMessage) {
		oldMessage.setContent(newContent.getContent());
		oldMessage.setTitle(newContent.getTitle());
		return oldMessage;
	}
	
	/**
	 * @Note: mesaj listesinden mesaj silinir
	 * @param message
	 * @return
	 */
	public boolean deleteMessage(Message message) {
		return messageList.remove(message);
	}
	
	/**
	 * @Note: id'si girilen mesaj bulunur
	 * @param id
	 * @return
	 */
	public Message findById(Integer id) {
		Optional<Message> optional = messageList.stream().filter(it -> it.getId().intValue() == id).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
}
