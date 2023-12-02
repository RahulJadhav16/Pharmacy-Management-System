package com.pms.doctor.service.Service;

import java.util.List;

import com.pms.doctor.service.Models.Chat;

public interface DoctorChatService {
    
	
	List<Chat> getAllMsg();
	
	Chat createMsg(Chat obj);

}
