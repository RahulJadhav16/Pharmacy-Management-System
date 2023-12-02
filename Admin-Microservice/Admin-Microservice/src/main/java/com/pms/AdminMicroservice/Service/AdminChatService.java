package com.pms.AdminMicroservice.Service;

import java.util.List;

import com.pms.AdminMicroservice.Model.Chat;

public interface AdminChatService {
	
	List<Chat> getAllMsg();
	
	Chat createMsg(Chat obj);
	

}
