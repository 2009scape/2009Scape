# 2009Scape - The most complete Runescape Emulation server. Pull requests welcome! ![Gameplay image](https://i.imgur.com/31b6KpU.png)

Join our Discord server: https://discord.gg/43YPGND

We also have a live game in the [Releases](https://github.com/dginovker/RS-2009/releases) section that always runs the latest code.

# Running your own instance
Please follow this [self hosting guide](selfhosting.md)

# Setting up the project for debugging
Requirements:
- General knowledge of git
- General knowledge of SQL
- Intellij

Be sure to check the debugging section when something goes wrong.

## Step 1:
Fork this repository to your repo

## Step 2: Setting up IntelliJ
- Open IntelliJ
- Click `File` > `New` > `Project from Version Control`
  - Paste the URL of your forked repository in the URL field, click Done
  - Wait for the repository to clone
- Click `File` > `Project Structure` > `Modules` > `RS-2009` > `Paths` > `Inherit project compile output path`
  - Hit `OK`
- Click `File` > `Project Structure` > `Project` > Change the Project Compiler Output to the folder where the project exists on your computer
- In the bottom right of IntelliJ, where it specifies the branch, 
**make sure you are developing on the development branch.**
  
## Step 3: Setting up the Database
Since this portion of the guide is operating-system specific, you will either need basic database knowledge or a bit of help. Below are the things that need to be configured.

### Windows & Linux GUI:
- Download and install [xampp](https://www.apachefriends.org/download.html)
- Start the `Apache` and `MySQL` modules
- Navigate to http://localhost/phpmyadmin/
- Create a new database called `global`
- Import`Server/global.sql` into that database
  - _Refer [here](https://www.thecodedeveloper.com/import-large-sql-files-xampp/) for help importing the `.sql` files_

### Linux Command Line
- Instructions for various Linux distros can be found [here](https://github.com/dginovker/2009Scape/tree/b7bffda1c787f0ad8e9da83f9de7616990927878/CompiledServer/Guides)


## Step 4: Running the Server & Client
- Run the management-server
  - In IntelliJ, navigate to `Management-Server/src/main/java/ms/Management.java`, right click > Run [Image](https://i.imgur.com/KXxxeYK.png)
- Run the server
  - In IntelliJ, navigate to `Server/src/main/java/Server/core/Server.kt`, right click > Run [Image](https://i.imgur.com/XOSN7LK.png)
  - At the top right, click the Server config > edit > set the Working directory to the Server/ folder [Image](https://i.imgur.com/Kn6fyxz.png)
- Run the client
  - In IntelliJ, navigate to `Client/src/main/java/org/runite/GameLaunch.java`, right click > Run [Image](https://i.imgur.com/ZZE7FU1.png)
  
You should now be set up!

## Debugging

Errors could not find library:
- Click `File` > `Project Structure` > `Modules` > `RS-2009` > `Paths` > `Inherit project compile output path`
  - Now click `Dependencies`
  - Click the `+` on the right-hand side > `JARs or directories`
  - Add the following things:
    - `Client/lib` directory
    - `Client/clientlibs.jar` file
    - `Management-server/lib` directory

Errors regarding java.nio.BufferUnderflowException in server:
- Reclone the repository. This happened to me (Red Bracket) once, could never reproduce.

Bridges not working/general compilation issues
- This project uses JDK/OpenJDK 8. Make sure you are on version 8. Anything besides JDK or OpenJDK we will not help you troubleshoot.
