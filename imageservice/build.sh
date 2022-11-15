#!/bin/sh
rm -rf imageservice/target/* && mvn clean package -Dmaven.test.skip && docker build . -t navuitag/image-service:latest && docker push navuitag/image-service:latest
