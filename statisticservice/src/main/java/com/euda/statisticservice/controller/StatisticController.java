package com.euda.statisticservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euda.statisticservice.model.Statistic;
import com.euda.statisticservice.service.StatisticService;

@RestController
@RequestMapping("/api/v1")
public class StatisticController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/statistics")
    public List<Statistic> index() {
        logger.debug("List all statistic");
        return statisticService.getAll();
    }

    @GetMapping("/statistics/{id}")
    public Statistic show(@PathVariable(value = "id") String id) {
        logger.debug("Show statistic");
        return statisticService.getOne(id);
    }

    @PostMapping("/statistics")
    public Statistic store(@Valid @RequestBody Statistic statistic) {
        logger.debug("Add statistic");
        return statisticService.create(statistic);
    }

    @PutMapping("/statistics/{id}")
    public Statistic update(@PathVariable(value = "id") String id, @Valid @RequestBody Statistic statistic) {
        logger.debug("Update statistic");
        return statisticService.update(id, statistic);
    }

    @DeleteMapping("/statistics/{id}")
    public Map<String, String> delete(@PathVariable(value = "id") String id) {
        logger.debug("Delete statistic");
        statisticService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("code", "200");
        response.put("message", "Statistic was deleted successful.");
        return response;
    }
}
