package com.euda.imageservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euda.imageservice.model.Image;

public interface ImageRepository extends MongoRepository<Image, String>{
    
}
