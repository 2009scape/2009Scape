From the production/RS-2009/ directory, run:
printf 'Main-Class: org.runite.Client\n' > Manifest.txt ; jar cfm Client.jar Manifest.txt org/runite/*.class org/runite/jagex/*
(Note: This was only tested on Linux.)

Test with
java -jar Client.jar
