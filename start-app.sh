#!/bin/bash
./start-docker-containers.sh
mvn package
mvn liquibase:update
mvn clean install
mvn tomcat7:deploy