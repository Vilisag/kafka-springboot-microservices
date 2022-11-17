#!/bin/sh
rm -rf statisticservice/target/* && mvn clean package -Dmaven.test.skip && docker build . -t navuitag/statistic-service:latest && docker push navuitag/statistic-service:latest
