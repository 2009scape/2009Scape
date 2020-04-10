@echo off
java -server -Xms512m -Xmx1536m -XX:NewSize=32m -XX:MaxPermSize=128m -XX:+UseConcMarkSweepGC -XX:+ExplicitGCInvokesConcurrent -XX:+AggressiveOpts -cp bin;data/libs/*; org.arios.Runner
pause