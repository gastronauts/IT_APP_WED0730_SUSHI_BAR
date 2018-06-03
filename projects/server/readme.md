# Server

### Main goal
  * Server application main goal id

## Getting started

### Prerequisites
  * Java 8
  * PostgresSQL 9.5 - https://www.postgresql.org/download/ (Interactive installer by EnterpriseDB)
  
### DB configuration
   -  while installing, please select "postgres" for default password and "postgres" for default username
   -  using for example pgAdmin4 create new database named "sushi"
   
### Build project

#### By IntelliJ IDEA
  * In right panel chose `Maven Project`
  * Click on `AvocadoShusiRestaurant -> Lifecycle -> package` 

#### By the maven:
  * Run `mvn clean package` command to create the project JAR.
   
Instate of way to build, make sure there it was properly created - should be `target/Server-1.0-SNAPSHOT.jar`.

### Rebuild project

#### By IntelliJ IDEA
  * In right panel chose `Maven Project`
  * Click on `AvocadoShusiRestaurant -> Lifecycle -> clean`
  * Click on `AvocadoShusiRestaurant -> Lifecycle -> package` 

#### By the maven:
  * Run `mvn clean package` to re-create the project JAR.
  
### Run
  * Run `java -jar Server-1.0-SNAPSHOT.jar`