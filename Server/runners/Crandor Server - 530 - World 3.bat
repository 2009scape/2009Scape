@echo off
@title World 3
java -server -Xms512m -Xmx1536m -XX:NewSize=32m -XX:MaxPermSize=128m -XX:+UseConcMarkSweepGC -XX:+ExplicitGCInvokesConcurrent -XX:+AggressiveOpts -cp ../bin;../data/libs/*;../data/libs/slf4j/*; org.crandor.Server ../worldprops/server3.properties
pause
