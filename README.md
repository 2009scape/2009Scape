[![License: CC BY-NC-SA 4.0](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-sa/4.0/)

<br />
<p align="center">
  <a href="https://2009scape.org/">
    <img src="https://i.imgur.com/RsfVfkB.png" alt="Logo" width="300" height="67">
  </a>
  <h3 align="center">An open source MMORPG emulation server</h3>

  <h1 align="center"><a href="https://github.com/2009scape/2009Scape/releases/latest/download/2009Scape.jar"><strong>Play the live server »</strong></a></h1>
  <p align="center">
    <br />
    <br />
    <a href="https://github.com/2009scape/2009Scape/releases/latest/download/2009Scape.jar">Community Hosted Server</a>
    ·
    <a href="https://discord.gg/43YPGND">Discord Invite</a>
    ·    <a href="https://github.com/2009scape/2009Scape/issues">Report Bug</a>
    ·
    <a href="https://2009scape.org/">Community Website</a>
  </p>
<h3>ATTENTION: Starting October 5th, 2020 the development team will no longer be providing support for setting up your own server. Do not ping or private message Developers asking how to build the project. This in-depth guide will be continually updated if the building process changes. We are focusing all of our efforts on the development of features and bugfixes for the live server.</h3>
</p>


## Table of Contents

* [Live Server Information](#live-server-information)
* [About the Project](#about-the-project)
* [Contributing](#contributing)
* [Contributing without Building](#contributing-without-building)
* [Getting Started](#getting-started)
  * [Github Setup](#github-setup)
  * [Prerequisites](#prerequisites)
  * [Prereq Installation](#prereq-installation)
* [Project Setup](#project-setup)
* [Running the project](#running-the-project)
* [License](#license)
* [Contact](#contact)


## Live Server Information

Did you know that the 2009Scape Development Team hosts the main branch of this repo that you can connect to and play? Come join a growing community of people that love to grind out skills, quest, and hangout together. A download link for the launcher can be found <a href="https://github.com/2009scape/2009Scape/releases/latest/download/2009Scape.jar"><strong>here.</strong></a> Connecting to the live server is also one of the easiest ways to identify bugs/typos/missing features. Identifying these issues help developers, whether already on the project, or are even brand new to the project, fix bugs and issues in an expedited rate.

## About The Project

The 2009Scape you see today has gone through a magnitude of changes. Originally starting its life as Arios498, this server saw a lot of people playing it daily, unfortunately, it was for profit and closed source. It was later upgraded to Arios530, targeting the build 530 of runescape with content in and around January 1st, 2009. Development came to a halt when a developer of the closed source project released the source code. The original developers of this server went on to create Kratos 530 back in 2015.

This project was started out of love for the 530 revision. A small group of developers spent hundreds of hours improving on the existing source that was left to the curb. Over the past year, this project has seen many developers coming and going, fixing bugs that they find either through their own server, or bugs that they find in the live game that is currently hosted. We do not accept donations of any kind. The smiles and wonderful compliments are more than enough to keep us going! Content and bugfixes are always number one on our list, and we try our best to answer any questions that you may have, provided you have read through this readme and frequently asked questions on the discord.

## Contributing

<h4>Note: A live server that the 2009Scape development team runs is the best way to find and report bugs! Any changes made should be pushed to the Development Branch, Any changes that are pushed to the main branch will be denied, and you will have to submit another PR request. We are currently in the process of rewriting the codebase in Kotlin. Any new commits should be written in Kotlin Only.</h4>
<h4>More information on Kotlin can be found <a href="https://kotlinlang.org/">here.</a></h4>

We are always looking for fellow helpers and developers to help preserve history and contribute towards making the most complete emulation server! The beautiful part is you don't even have to download and build the project in order to contribute! Want to make simple edits to NPCs, Items, Music, Shops, Typos? Follow the [Contributing-without-Building](#contributing-without-building) guide below!

## Contributing without Building

First create a Github account if you do not have one already. Editing the repository directly is one of the easiest ways to contribute to the project. You will start off in the master/main branch. Switch to the **"Development Branch"** by clicking on the drop down menu which is on the left hand side where it says "Branches" and "Tags". Then navigate to the file you wish to change, for example, All definitions are located in **"Server/data/configs/"**. If you were to edit NPCSpawns.json, you would select NPCSpawns.json, and on the right hand side of the window there will be a screen icon, pencil icon, and trashcan icon. Click on the pencil icon to make changes. When you are finished with your changes, ensure you are in **development** by looking at the branch in the top left. Scroll down until you see "propose changes", and fill out the two info bars at the bottom to describe what you changed/fixed. 
<br>
**More information on repository file editing can be found <a href="https://docs.github.com/en/free-pro-team@latest/github/managing-files-in-a-repository/editing-files-in-another-users-repository">here.</a>**
<br>
<br>
**Editing files that are too large for Github in-browser editing**
<br>
First follow the [Github-Setup](#github-setup). After you have forked over your copy of the repo, swap over to the **Development branch**, and then download the file that you wish to edit. JSON format can be edited in any notepad program, however it is recommended to use either <a href="https://atom.io/">Atom</a> or <a href="https://notepad-plus-plus.org/">Notepad++</a>. After you are finished editing your file, navigate back to the file you wish to replace on your **Development Branch**. Select it and delete it off your branch. Then go back and on the right hand side click on "Add files" and then "Upload new files". Upload the file you are done editing and then submit a Pull request (PR). To submit a PR navigate to "Pull requests" and then click on "New pull request". On the top of the page you should see "Comparing changes". The left set of options is what you will be pushing to and the right side is where you are pushing from. On the right side, put your repo and compare to **Development**, On the left hand side put the repo as 2009scape/2009scape base: **Development**. If at anytime it tries to pull/push into its own repository, click "Compare across forks" and you should be able to select where you want to push to and from. 
<br>
**More information about submitting PR's can be found <a href="https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/proposing-changes-to-your-work-with-pull-requests">here.</a>**



## Getting Started

### Github Setup

<h4>Note: This allows you to commit changes to the main repo (with approval)! Also, always stay up to date with the most recent updates by pulling into your copy when 2009Scape updates!</h4>

1. Create a github account if you haven't done so already

2. Go to the 2009Scape <a href="https://github.com/2009scape/2009Scape">Github Page</a>

3. Click "Fork" in the top right hand side of Github

**If at anytime you have an issue with Github please refer to the** <a href="https://docs.github.com/en/free-pro-team@latest/github">Github help center.</a>


### Prerequisites

<h4>Note: It is required for a developer submitting a PR to use Intellij IDEA as your integrated development environment.</h4>

* Intellij IDEA
* Java SE Development Kit Version 1.8
* Gradle (optional - Installs with Intellij on project build)
* Xampp

## Prereq Installation

<h3>Windows</h3>
<details>
<summary> </summary>

1. Install <a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">JDK version 1.8</a>
    * Scroll down until you see Windows x86 and Windows x64
    * If you are running a 64bit verison of Windows (standard), select Windows x64
    * Accept the terms and conditions, after reading them of course, and login to oracle
    * Download and install like any normal application
    
2. Install <a href="https://www.jetbrains.com/idea/">Intellij IDEA</a>
    * Click "Download" on the main page
    * Ensure "Windows" is selected at the top
    * Select "Community download"
    * Download and install like any normal application
    
3. Install <a href="https://gradle.org/">Gradle</a>
    * Click on "Install Gradle" 
    * Scroll down to "Installing manually" 
    * Download "Binary-only"
    * Scroll down until you see "Microsoft Windows users"
    * Follow the instructions listed on the website
    
4. Install <a href="https://www.apachefriends.org/index.html">Xampp</a>
    * Click on "Xampp for Windows"
    * Download
    * Install as Administrator
</details>  
    
<h3>MacOS</h3>
<details>
<summary> </summary>

1. Install <a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">JDK version 1.8</a>
    * Scroll down until you see macOS x64
    * select macOS x64
    * Accept the terms and conditions, after reading them of course, and login to oracle
    * Download and install like any normal mac application

2. Install <a href="https://www.jetbrains.com/idea/">Intellij IDEA</a>
    * Click "Download" on the main page
    * Select "Mac" towards the top of the main page
    * Select "Community download"
    * Download and install like any normal application
    
3. Install <a href="https://gradle.org/">Gradle</a>
    * Click on "Install Gradle" 
    * Scroll down to "Installing manually" 
    * Download "Binary-only"
    * Scroll down until you see "Linux & MacOS users"
    * Follow the instructions listed on the website
    
4. Install <a href="https://www.apachefriends.org/index.html">Xampp</a>
    * Click on "Xampp for OS X"
    * Download
    * Install as Administrator
</details>

<h3>Linux</h3>
<details>
<summary> </summary>

1. Install JDK version 1.8 through <a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">Oracle</a> or through <a href="https://openjdk.java.net/install/">command line</a>
    * Debian, Ubuntu, etc.
    ```sh
    sudo apt-get install jre8-openjdk
    ```
    * Fedora, Oracle Linux, Red Hat Enterprise Linux, etc.
    ```sh
    su -c "yum install java-1.8.0-openjdk"
    ```  
  
2. Install Intellij IDEA through <a href="https://www.jetbrains.com/idea/">Intellij Website</a> or through <a href="https://www.jetbrains.com/help/idea/installation-guide.html#snap">Command Line</a>
    * Debian, Ubuntu, etc.
    ```sh
    sudo snap install intellij-idea-community --classic
    ```

3. Install Gradle through the <a href="https://gradle.org/">Gradle Website</a> or through <a href="https://gradle.org/">Command Line</a>
    * Click install Gradle
    * If you're installing through Command Line follow : Installing with a package manager
    * If you're installing through Download follow : Installing manually : Linux & MacOS users
    ```sh
    sdk install gradle 6.6.1
    ```
    
4. Install <a href="https://www.apachefriends.org/index.html">Xampp</a>
    * Click install "XAMPP for Linux"
    * Install like any normal Linux program
</details>


### Project Setup

1. Open Xampp
    * On Windows make sure you run Xampp as administrator
    * On the left hand side make sure you tick the two "Service" boxes for Apache and MySQL
    * For both Apache and MySQL click "Start" under Actions
    * After doing that navigate to the <a href="https://localhost/phpmyadmin/">PHP My Admin LOCAL SITE</a>
    * Once opened, on the left hand side click the three "disks" that say "New"
    * In the "Database name" bar type: global
    * Press the "Create" button
    * A three disk "global" should appear on the left hand side
    * Click it and on the top bar select "Import"
    * Under **FILE TO IMPORT** click "Browse...." 
    * Navigate to your 2009Scape project folder
    * Go to Server/db_exports/ and import global.sql
    * It may take a moment to import, when It is done importing Xampp is all set up!

2. Open Intellij
    * Select Get from Version Control
    * Click on "Github", and you will be asked to login
    * Change your directory to wherever you want the project to load, or keep it default
    * On the right hand side you should see your Github Repository for 2009Scape
    * Select it and hit "Clone"
    * The project should instantly start building on import. Give it some time because it is going through and compiling alot of files
    * In Intellij go to File -> Project Structure -> Project and verify your Project SDK is set to JDK "1.8"
    * Setup through Intellij should now be finished!
    
### Running the project

1. Navigate to the right hand side of Intellij where it says "Gradle"
    * Gradle is very useful when it comes to running and compiling the project
    * The only tabs we are concerned about are "Client", "Management-Server", and "Server"
    * Each of these have a "Tasks" folder and an "application" folder
    <br>
2. Click on the "application" folder for the Management-Server and double-click "run"
    * The management server is used for things such as player data(not saves), highscores, and world information
    <br>
3. Click on the "application" folder for the Server and double-click "run"
    * Please note this may take a minute or two to build, it is compiling a lot of files!
    * If you receive an error on server start, check and make sure that your worldprops/default.json is pointing to the correct paths
    <br>
4. Copy your cache from 2009Scape/Server/data/cache (.dat2/.idx0-255) to C:/Users/(your_name)/.runite_rs/runescape/
    * As of the writing of this readme, cache downloading is still broken. It is on the agenda to be fixed in the future
    * **If you don't have those folders then create them**
    * Server debug mode/other information can be changed in worldprops/default.json
    <br>
5. Click on the "application" folder for the Client and double-click "run"
    * If you receive an error on client run open your build.gradle to verify that the mainClassName is set to the correct location
    * Due to client work that is on going a lot of files get changed and new paths for the main class are created
    * **FIX** In your build.gradle change the mainClassName and Main-Class attributes to 'org.runite.jagex.Client'
    * If Gradle run still does not work, launch the Client by navigating to Client/src/main/java/org.runite/jagex/Client
    * Right click on the client and select Run 'Client.main()'
    * Client debugging options can be found inside of config.json

### License

We use the CC BY-NC-SA 4.0 license, which can be found <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">here.</a> Please read and understand the license. Failure to follow the guidelines outlined in the license will result in legal action. If you know or hear of anyone breaking this license, please send a report, with proof, to Red Bracket#8151, ceikry#2724, or woahscam#8535 on discord or email woahscam@hotmail.com. **We WILL NOT change the license to fit your needs.**

### Contact

<h4>Reminder: Developer support for setting up your own server ended October 5th, 2020. Do not ping/dm developers about setting up your server.</h4>



[license-shield]: https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg
[license-url]: https://creativecommons.org/licenses/by-nc-sa/4.0/
