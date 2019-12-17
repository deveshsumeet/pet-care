Pet-Care Demo Project
=======================
Build and Deployment Steps
----------------------------

**1. Introduction**
---------------------

Pet Care project is a demo project developed to showcase the bootstrapping of a production ready web application using Spring Boot. Spring boot is an open source java based framework which can be used for creating database oriented, auto configurable Spring application. 

At a high level, Pet care application will allow a user to register a new Pet as a patient, new veterinarian as a doctor and help to setup an appointment between a Pet and a Vet.

Maven is used as a build tool to generate the executable jar file for this project.

**2. Steps to create build and deploy**
----------------------------------------

Below are prerequisite to build this project -
* Mac machine with MacOS (Mojave)
* Java version 8 or above 
* Maven Version 3.3 or above
* MongoDB 4.2

**Steps to Install and Configure Mongo DB**

* Download MongoDB 4.2 (Community Edition) from mongodb website
  ```
  https://fastdl.mongodb.org/osx/mongodb-macos-x86_64-4.2.2.tgz
  ```

* Extract the downloaded zip using below command  -
  ```
  tar -xvf mongodb-macos-x86_64-4.2.1.tgz
  ```

* Navigate inside the extracted folder (mongodb-macos-x86_64-4.2.1)
* Create a file with name mongod.cfg under above extracted mongodb folder with below content -

  ```
  port = 27017
  quiet = false
  dbpath = ../data/db
  logpath = ../log/mongod.log
  logappend = true
  journal = true
  directoryperdb = true
  profile=2
   ```

* Create a folder with name log and data under above extracted mongodb folder
* Create a folder with name db under data folder
* Navigate inside bin folder of extracted mongoDB folder location.
* Execute below command to start the mongoDB application
  ```
  ./mongod  --config  ../mongod.cfg &
  ```
  
* Verify mongodb is running in background using below command
  ```
  ps -ef | grep mongo
  ```

* Execute below command to connect to mongoDB shell from same bin location -
  ```
  ./mongo
  ```

* Fire below commands to get the list of mongo user and create a root user inside admin db -	

  ```
  show dbs
  show users
  use admin
  db.createUser( { "user" : "root", "pwd" : "root", "roles" : [{ "role" : "readWrite", "db" : "admin" }, { "role" : "dbAdmin", "db" : "admin" }, { "role" : "userAdmin", "db" : "admin" }, { "role" : "dbOwner", "db" : "admin" } ] })
  show dbs
  show users
  ```
  
**Steps to build the project**

* Download the code base or perform git clone to download the project 
```
https://github.com/deveshsumeet/pet-care
```

* Navigate to folder where code base is downloaded. Under pet-care folder (at same level where pom.xml file is present)

* Execute below maven commands to build the project -
  ```
  mvn clean install -Dmaven.test.skip=true
  ```


**Steps to start the application**

Navigate inside target folder of this project folder. 
Copy the jar file to appropriate folder from where application needs to be run.
Execute below command to start application -

```
java -jar pet-care-1.0.jar &
```

Access application using below URL

```
http://localhost:9090
```
