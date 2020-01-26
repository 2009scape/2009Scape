rm -rf org
cp -r ../CompiledServer/production/RS-2009/org .
java -server -cp ".:./bin;.:./lib/*" org.keldagrim.Management false true false true true
