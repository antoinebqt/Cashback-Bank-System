#!/bin/bash

APP="${PWD##*/}"

# Compiling and buildpacking docker image
echo "** Compiling $APP"
docker image rm newbank/$APP
docker build -t "newbank/$APP" .
echo "** Done"