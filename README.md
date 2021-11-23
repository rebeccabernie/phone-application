# phone-application
A basic user / phone management system, built with Java 11 and the Spring Framework.

## Running the Application
From the project root run:

`mvn clean install -DskipTests && cp target/*.jar src/main/docker/ && cd src/main/docker/ && docker-compose up --force-recreate`

This will clean and build the project into a jar, copy the jar to the src/main/docker directory, and start the database and application.

Debugging, for my own reference:

*Remove containers, images and volumes, then:*

`cd ../../../ && mvn clean install -DskipTests && cp target/*.jar src/main/docker/ && cd src/main/docker/ && docker-compose up --force-recreate`

*(Bad practice to be force removing the image but saving a few seconds by not removing the container first, gets recreated anyway)*.

## Access Database
**Note:** Postgres container must be running.

`docker exec -it [postgres-container-id] psql -U postgres -W`

---
#### Reference Material

https://www.baeldung.com/spring-boot-postgresql-docker
