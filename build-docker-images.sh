#!/bin/sh

chmod +x gradlew
./gradlew bootJar

cd microservices/catalog-service
docker rmi catalog-service
docker build -t catalog-service .
cd ../../

cd microservices/order-service
docker rmi order-service
docker build -t order-service .
cd ../../

cd spring-cloud/edge-service
docker rmi edge-service
docker build -t edge-service .
cd ../../

cd spring-cloud/config-service
docker rmi config-service
docker build -t config-service .
cd ../../



