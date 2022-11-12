package com.euda.statisticservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euda.statisticservice.model.Statistic;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    
}
