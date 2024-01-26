package com.chat.system.chatservice.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.system.chatservice.Repo.chatRepo;
import com.chat.system.chatservice.Service.ChatService;
import com.chat.system.chatservice.model.Chat;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private chatRepo repo;

	@Override
	public Chat createMsg(Chat obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Chat> getAllMsg() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
