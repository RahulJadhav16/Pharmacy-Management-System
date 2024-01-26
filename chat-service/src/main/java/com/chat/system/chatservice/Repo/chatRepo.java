package com.chat.system.chatservice.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chat.system.chatservice.model.Chat;

@Repository
public interface chatRepo extends MongoRepository<Chat, String>{

}
