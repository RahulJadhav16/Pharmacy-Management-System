package com.chat.system.chatservice.Service;

import java.util.List;

import com.chat.system.chatservice.model.Chat;

public interface ChatService {
	//Create
	Chat createMsg(Chat obj);
	
	//Read
	List<Chat>getAllMsg();
	
	

}
