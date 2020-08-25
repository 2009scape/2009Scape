# 2009Scape selfhosting guide

## Requirements
You will need:
```
git
gradle
java 8
```

Recommended:
```
Linux
Debugging ability
```

## Getting the code:
Git clone this repository, or download the latest source with the clone or download button

## Building the server:
In a terminal, enter both the Management-Server and Server folders and run `gradle build`

#### Optional: Setting the client target IP address for non-local servers
In Client/src/main/java/org/runite/Configurations.java set LOCAL_MS and LOCAL_SERVER to false

In Client/src/main/java/org/runite/Client.java set PUBLIC_IP_ADDRESS to your server's ip address

## Building the client:
In a terminal, enter the Client folder and run `gradle build`

## Installing the databases:

### Windows & Linux GUI:
- Download and install [xampp](https://www.apachefriends.org/download.html)
- Start the `Apache` and `MySQL` modules
- Navigate to http://localhost/phpmyadmin/
- Create a database `global`
- Import `Server/global.sql` into that database
  - _Refer [here](https://www.thecodedeveloper.com/import-large-sql-files-xampp/) for help importing the `.sql` files_

### Linux Command Line
- Get and install mariadb or some other sql compatible database from your repository
- Login and create the global database:
Create:
```
CREATE DATABASE global;
```
- Import the global database from the Server/ directory:
```
mysql -u root -p global < global.sql
```

## Running the server/client
In a terminal, navigate to the Management-Server folder and run `gradle run`

Then navigate to the Server folder and run `gradle run`

The client can also be run with `gradle run`. Gradle also generates jars that you can distribute inside Client/build/distributions/Client.zip

#### (Optional) Customizing the Server rules
Edit `default.json` in `Server/worldprops`

#### (Optional) Adding Plugins
- See our [Plugins Repository](https://github.com/2009scape/Plugins)

#### (Optional) Port Forwarding for non-local users
Port forwarding instrcutions depend on your router, but you'll want to forward the following ports:
- 5050 (??? management server I think?)
- 5555 (World list, or something?)
- 43945 (World 1)
- 43946 (World 2)
- 43947 (World 3)
