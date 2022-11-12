package com.euda.statisticservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.euda.statisticservice.exception.ResourceNotFoundException;
import com.euda.statisticservice.model.Statistic;
import com.euda.statisticservice.repository.StatisticRepository;

public interface StatisticService {
    Statistic create(Statistic statistic);

    Statistic update(String id, Statistic statistic);

    void delete(String id);

    List<Statistic> getAll();

    Statistic getOne(String id);
}

@Transactional
@Service
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    StatisticRepository statisticRepository;

    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {
        logger.info("Recieved: " + statistic.getMessage());
        statisticRepository.save(statistic);
    }

    @Override
    public Statistic create(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    @Override
    public void delete(String id) {
        Statistic statistic = this.getOne(id);
        statisticRepository.delete(statistic);
    }

    @Override
    public List<Statistic> getAll() {
        return statisticRepository.findAll();
    }

    @Override
    public Statistic getOne(String id) {
        return statisticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Statistic: %s, not found", id)));
    }

    @Override
    public Statistic update(String id, Statistic statistic) {
        Statistic originStatistic = this.getOne(id);
        originStatistic.setMessage(statistic.getMessage());
        return statisticRepository.save(originStatistic);
    }
}