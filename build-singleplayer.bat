@echo off

mkdir Single-Player

echo Building Client...
cd Client
call gradle jar
cd ..
copy /Y "Client\build\libs\client-1.0.0.jar" "Single-Player\client.jar"
echo.

echo Building Management-Server...
cd Management-Server
call gradle jar
cd ..
copy /Y "Management-Server\build\libs\managementserver-1.0.0.jar" "Single-Player\ms.jar"
echo.

echo Building Server...
cd Server
call gradle jar
cd ..
copy /Y "Server\build\libs\server-1.0.0.jar" "Single-Player\server.jar"
echo.

echo Copying server data...
del /S /Q "Single-Player/data"
xcopy /E /I /Y "Server\data" "Single-Player\data"
xcopy /E /I /Y "Server\db_exports\*.sql" "Single-Player\data"
del /S /Q "Single-Player/worldprops"
xcopy /E /I /Y "Server\worldprops" "Single-Player\worldprops"
: Set Debug/Dev mode to false on single player server config
powershell -Command "(gc Single-Player\worldprops\default.json) -replace '\"debug\": true', '\"debug\": false' -replace '\"dev\": true', '\"dev\": false' | Out-File -Encoding Default Single-Player\worldprops\default.json"

: Replace Live server addresses with localhost
powershell -Command "(gc Client\config.json) -replace 'play.2009scape.org', 'localhost' | Out-File -Encoding Default Single-Player\config.json"
echo.

echo Done!
pause