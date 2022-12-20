package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Message;
import com.emlakcepte.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/** @Note: kaydedilen mesajları verir */
	@GetMapping
	public ResponseEntity<List<Message>> getAll() {
		return new ResponseEntity<>(messageService.getAll(), HttpStatus.OK);
	}

	/** @Note: fromId'li User'dan toId'li User'a mesaj gönderilir. */
	@PostMapping(value = "/{fromId}/{toId}")
	public ResponseEntity<Message> sendMessage(@PathVariable Integer fromId, @PathVariable Integer toId,
			@RequestBody Message message) {
		return messageService.save(fromId,toId,message)
				? new ResponseEntity<>(message, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Note: girilen Id'ye ait mesaj -> mesaj listesinden ve istegi yapan kullanıcının mesaj listesinden silinir!
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@PostMapping("/delete/{userId}/{messageId}")
	public ResponseEntity<Message> deleteMessage(@PathVariable Integer userId, @PathVariable Integer messageId) {
		return messageService.delete(userId, messageId) ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Note: girilen Id'ye ait mesaj -> Güncellenir!
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@PostMapping("/{messageId}")
	public ResponseEntity<Message> updateMessage(@PathVariable Integer messageId,@RequestBody Message message) {
		return messageService.update(message,messageId) ? new ResponseEntity<>(message,HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
