#!/bin/sh

cd ../
## build
mvn clean package

## copy to the released folder
rm -rf ./released/
mkdir ./released/
cp ./NIOServer/target/NIOServer.jar ./released/NIOServer.jar
cp ./UDPServer/target/UDPServer.jar ./released/UDPServer.jar
