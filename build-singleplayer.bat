@echo off

mkdir Single-Player

echo Building Client...
cd Client
call gradle jar
cd ..
copy /Y "Client\build\libs\client-1.0.0.jar" "Single-Player/client.jar"

echo Building Management-Server...
cd Management-Server
call gradle jar
cd ..
copy /Y "Management-Server\build\libs\managementserver-1.0.0.jar" "Single-Player/ms.jar"

echo Building Server...
cd Server
call gradle jar
cd ..
copy /Y "Server\build\libs\server-1.0.0.jar" "Single-Player/server.jar"

echo Copying server data...
del Single-Player/data
xcopy /E /I /Y "Server/data" "Single-Player/data"
del Single-Player/worldprops
xcopy /E /I /Y "Server/worldprops" "Single-Player/worldprops"
copy /Y "Client\config.json" "Single-Player\config.json"

echo Done!