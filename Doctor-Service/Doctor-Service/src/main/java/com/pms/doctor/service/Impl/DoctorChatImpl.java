package com.pms.doctor.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.doctor.service.Models.Chat;
import com.pms.doctor.service.Service.DoctorChatService;

@Service
public class DoctorChatImpl implements DoctorChatService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Chat> getAllMsg() {
		String url="http://CHAT-SYSTEM:9092/chat/getAllMsg";
		List<Chat>viewAllChat=restTemplate.getForObject(url, ArrayList.class);
		return viewAllChat;
	}

	@Override
	public Chat createMsg(Chat obj) {
		String url="http://CHAT-SYSTEM:9092/chat/createMsg";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Chat> requestEntity = new HttpEntity<>(obj, headers);
		
		Chat response=restTemplate.exchange(url, HttpMethod.POST, requestEntity, Chat.class).getBody();
		return response;
	}

}
