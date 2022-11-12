package com.euda.accountservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.euda.accountservice.model.Statistic;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    List<Statistic> findByStatus(Boolean status);
}
