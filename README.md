# RS 2009 - The most complete Runescape Emulation server. Pull requests welcome!

Join our Discord server: https://discord.gg/4zrA2Wy

We also have a live game in the [Releases](https://github.com/dginovker/RS-2009/releases) section that always runs the latest code.

# Setting up the project
Requirements:
- General knowledge of git
- General knowledge of SQL
- Intellij

Be sure to check the debugging section when something goes wrong.

## Step 1:
- Fork this repository to your repo

## Step 2: Setting up IntelliJ
- Open IntelliJ
- Click `File` > `New` > `Project from Version Control`
  - Paste the URL of your forked repository in the URL field, click Done
  - Wait for the repository to clone
- Click `File` > `Project Structure` > `Modules` > `09HDscape` > `Paths` > `Inherit project compile output path`
  - Hit `OK`
- Click `File` > `Project Structure` > `Project` > Change the Project Compiler Output to the folder where the project exists on your computer
  
## Step 3: Setting up the Database
Since this portion of the guide is operating-system specific, you will either need basic database knowledge or a bit of help. Below are the things that need to be configured.

### Windows & Linux GUI:
- Download and install [xampp](https://www.apachefriends.org/download.html)
- Start the `Apache` and `MySQL` modules
- Navigate to http://localhost/phpmyadmin/
- Create 2 new tables named `server` and `global`
- Import `Server/server.sql` and `Server/global.sql` into their respective databases
  - _Refer [here](https://www.thecodedeveloper.com/import-large-sql-files-xampp/) for help importing the `.sql` files_

### Linux Command Line
- Instructions for various Linux distros can be found [here](https://github.com/dginovker/RS-2009/tree/master/CompiledServer/Guides)


## Step 4: Running the Server & Client
- Run the management-server
  - In IntelliJ, navigate to `09HDscape-management-server/src/org/keldagrim/Main.java`, right click > Run
- Run the server
  - In IntelliJ, navigate to `09HDscape-server/src/org/crandor/Main.java`, right click > Run
    - It will fail
  - Top right there should be `Main (1)` in a dropdown, click it then select `Edit Configurations`
  - Change the working directory to `09HDscape-server` so it can find `server.properties`
- Run the client
  - In IntelliJ, navigate to `09HDscape-client/src/org/runite/GameLaunch.java`, right click > Run
  
You should now be set up!

## Debugging

Errors could not find library:
- Click `File` > `Project Structure` > `Modules` > `09HDscape` > `Paths` > `Inherit project compile output path`
  - Now click `Dependencies`
  - Click the `+` on the right-hand side > `JARs or directories`
  - Add the following things:
    - `09HDscape-client/lib` directory
    - `09HDscape-client/clientlibs.jar` file
    - `09HDscape-management-server/lib` director 

Errors regarding java.nio.BufferUnderflowException in server:
- Reclone the repository. This happened to me (Red Bracket) once, could never reproduce.
