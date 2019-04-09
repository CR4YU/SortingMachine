#!/usr/bin/env bash
mvn clean install
cd ui
echo ''
echo '/----------------------\'
echo '> Starting application <'
echo '\----------------------/'
echo ''
mvn exec:java
