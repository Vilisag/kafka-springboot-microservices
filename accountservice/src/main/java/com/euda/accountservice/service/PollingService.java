package com.euda.accountservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.euda.accountservice.model.Message;
import com.euda.accountservice.model.Statistic;
import com.euda.accountservice.repository.MessageRepository;
import com.euda.accountservice.repository.StatisticRepository;

@Component
@Transactional
public class PollingService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Scheduled(fixedDelay = 1000)
    public void producer() {
        List<Message> messages = messageRepository.findByStatus(false);
        for (Message message : messages) {
            kafkaTemplate.send("notification", message).addCallback(new KafkaSendCallback<String, Object>() {
                @Override
                public void onFailure(KafkaProducerException ex) {
                    logger.error("KAFKA Notification Error:", ex);
                }

                @Override
                public void onSuccess(@Nullable SendResult<String, Object> result) {
                    logger.info("KAFKA Notification Success");
                    message.setStatus(true);
                    messageRepository.save(message);
                }
            });
        }

        List<Statistic> statistics = statisticRepository.findByStatus(false);
        for (Statistic statistic : statistics) {
            kafkaTemplate.send("statistic", statistic).addCallback(new KafkaSendCallback<String, Object>() {
                @Override
                public void onFailure(KafkaProducerException ex) {
                    logger.error("KAFKA Statistic Error:", ex);
                }

                @Override
                public void onSuccess(@Nullable SendResult<String, Object> result) {
                    logger.info("KAFKA Statistic Success");
                    statistic.setStatus(true);
                    statisticRepository.save(statistic);
                }
            });
        }
    }

    @Scheduled(fixedDelay = 6000)
    public void delete() {
        List<Message> messages = messageRepository.findByStatus(true);
        for (Message message : messages) {
            messageRepository.delete(message);
        }
        List<Statistic> statistics = statisticRepository.findByStatus(true);
        for (Statistic statistic : statistics) {
            statisticRepository.delete(statistic);
        }
    }
}
