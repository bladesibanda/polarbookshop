#!/bin/sh

chmod +x gradlew

rm -rf microservices/catalog-service/build/libs/*
./gradlew :microservices:catalog-service:bootJar
cd microservices/catalog-service
docker rmi catalog-service
docker build -t catalog-service .
cd ../../

rm -rf microservices/order-service/build/libs/*
./gradlew :microservices:order-service:bootJar
cd microservices/order-service
docker rmi order-service
docker build -t order-service .
cd ../../

rm -rf spring-cloud/edge-service/build/libs/*
./gradlew :spring-cloud:edge-service:bootJar
cd spring-cloud/edge-service
docker rmi edge-service
docker build -t edge-service .
cd ../../

rm -rf spring-cloud/config-service/build/libs/*
cd spring-cloud/edge-service
./gradlew :spring-cloud:config-service:bootJar
docker rmi config-service
docker build -t config-service .
cd ../../



