#!/bin/sh
rm -rf notificationservice/target/* && mvn clean package -Dmaven.test.skip && docker build . -t navuitag/notification-service:latest && docker push navuitag/notification-service:latest
