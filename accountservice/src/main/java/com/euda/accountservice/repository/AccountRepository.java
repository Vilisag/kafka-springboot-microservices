package com.euda.accountservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euda.accountservice.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    
}
