#!/usr/bin/env bash
mvn clean install -DskipTests
cd ui
echo ''
echo '-------------------------'
echo 'Starting application'
echo '-------------------------'
echo ''
mvn exec:java
