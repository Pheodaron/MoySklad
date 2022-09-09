#!/bin/bash
docker network inspect moysklad >/dev/null 2>&1 || \
    docker network create moysklad

docker run \
-d \
-p 5432:5432 \
--network moysklad \
--network-alias postgres \
-v moysklad-postgres-data:/var/lib/postgresql \
--name moysklad-postgres \
-e POSTGRES_USER=moysklad \
-e POSTGRES_PASSWORD=moysklad \
-e POSTGRES_DB=moyskladdb \
postgres:14.5-alpine

docker run \
-d \
-p 8888:8080 \
--network moysklad \
--network-alias tomcat \
--name=moysklad-app \
pheodaron/moysklad-tomcat