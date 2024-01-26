package com.chat.system.chatservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.system.chatservice.Impl.ChatServiceImpl;
import com.chat.system.chatservice.model.Chat;

@RestController
@RequestMapping("/chat")
public class Controller {
	
	@Autowired
	private ChatServiceImpl chatServiceImpl;
	
	@GetMapping("/getAllMsg")
	public ResponseEntity<List<Chat>> getallMsg()
	{
		return ResponseEntity.status(HttpStatus.OK).body(chatServiceImpl.getAllMsg());
		
		
	}
	
	@PostMapping("/createMsg")
	public ResponseEntity<Chat> createMsg(@RequestBody Chat obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(chatServiceImpl.createMsg(obj));
		
		
	}
	
	

}
