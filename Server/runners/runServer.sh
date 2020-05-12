rm -rf org
cp -r ../CompiledServer/production/RS-2009/org .
rm -rf plugin/
cp -r ../CompiledServer/production/RS-2009/plugin/ .
java -server -cp ".:./bin;.:./data/libs/*" org.crandor.Server 
