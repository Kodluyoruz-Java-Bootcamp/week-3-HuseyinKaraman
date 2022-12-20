package com.emlakcepte.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;
import com.emlakcepte.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	private UserService userService;

	/**
	 * @Note: mesaj mesaj listesine ve gönderen ile alan user'in mesaj listesine kayıt edilir.
	 * @param fromId
	 * @param toId
	 * @param message
	 * @return
	 */
	public boolean save(Integer fromId, Integer toId, Message message) {
		/** Kullanıcı kontrolü */
		User fromUser = userService.findById(fromId);
		User toUser = userService.findById(toId);
		if (userService.checkUser(fromUser) || userService.checkUser(toUser)) {
			return false;
		}
		message.setFrom(fromUser);
		message.setTo(toUser);
		userService.saveMessage(fromUser, toUser, message);
		return messageRepository.saveMessage(message);
	}

	/**
	 * @Note: butun mesajları verir.
	 * @return
	 */
	public List<Message> getAll() {
		return messageRepository.getAllMessage();
	}

	/**
	 * @Note: Eger verilen id'e ait Message varsa yeni gelen message'in degerleri
	 *        ona atanır
	 * @param message
	 * @return
	 */
	public boolean update(Message message, Integer id) {
		Message byIdMessage = findById(id);
		if (checkMessage(byIdMessage)) {
			return false;
		}
		return !checkMessage(messageRepository.updateMessageContent(message, byIdMessage));
	}

	/**
	 * @Note: Id'si girilen user'in mesaj listesinden mesaj silinir. 
	 * @param userId
	 * @param messageId
	 * @return
	 */
	public boolean delete(Integer userId, Integer messageId) {
		User user = userService.findById(userId);
		Message message =findById(messageId);
		if (userService.checkUser(user) ||checkMessage(message)) {
			return false;
		}
		return messageRepository.deleteMessage(message) && user.getMessages().remove(message);
	}

	/**
	 * @Note: id'si girilen mesaj bulunur
	 * @param id
	 * @return
	 */
	public Message findById(Integer id) {
		return messageRepository.findById(id);
	}

	/**
	 *  @Note: null kontrolu yapılır.
	 * @param message
	 * @return
	 */
	public boolean checkMessage(Message message) {
		return Objects.isNull(message);
	}
}
