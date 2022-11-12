package com.euda.accountservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euda.accountservice.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByStatus(Boolean status);
}
