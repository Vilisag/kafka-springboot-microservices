#!/bin/sh
rm -rf authservice/target/* && mvn clean package -Dmaven.test.skip && docker build . -t navuitag/auth-service:latest && docker push navuitag/auth-service:latest
