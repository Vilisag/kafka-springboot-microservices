#!/bin/sh
rm -rf accountservice/target/* && mvn clean package -Dmaven.test.skip && docker build . -t navuitag/account-service:latest && docker push navuitag/account-service:latest
