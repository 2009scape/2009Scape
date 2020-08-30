#!/bin/bash
echo "Make sure you have built the server before running this command."
if [ $# -eq 0 ]; then
    echo "Usage: $0 [--build] type"
    echo "Example: $0 ms"
    echo "Example: $0 server Server/worldprops/default.json"
    echo "Example: $0 client"
    echo "Example: $0 --build server Server/worldprops/default.json"
    exit
fi
readIndex=1
propsFile="$1"
doBuild=false
function run_server {
  Server/bin/Server "$1"
}
run_ms(){
  exec "Management-Server/bin/Management-Server"
}
run_client(){
  exec "Client/bin/Client"
}
if [ "$1" = "--build" ]; then
    readIndex="$((readIndex + 1))"
    doBuild=true
    if [ $# -lt 2 ]; then
        echo "You must supply a type."
        echo "If you just want to build without running, use build.sh"
        exit
    fi
fi

type="${!readIndex}"
if [ $doBuild = true ]; then
        ./build.sh "$type"
fi
if [ "$type" = "server" ]; then
    readIndex="$((readIndex + 1))"
    propsFile="${!readIndex}"
    echo $propsFile
    run_server "$propsFile"
elif [ "$type" = "ms" ]; then
    run_ms
elif [ "$type" = "client" ]; then
    run_client
fi
