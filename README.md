# Conference Management System

This application has two controller modules: Back-Off-Gateway and Conference-Gateway, each of which is responsible for their own logic. Back-Off-Gateway is responsible to create new conferences and conference rooms, to update them and to find existed feedback to selected conferece. Conference-Gateway is responsible to send new feedback to selected conference by using unique participant code, to registrate for conference and to find available conferences.

To see Swagger API for each of modules after start go to:

Back-Office-Gateway:
>
>    http://localhost:8081/swagger-ui.html
>

Conference-Gateway:
>
>    http://localhost:8082/swagger-ui.html
>

Platform module is a library module, which used to connect to database and which include entity-classes, dto-classes and some several service-classes related to model.

With the Liquibase database migration was configured, there is one test dataset to run integration test.

# Technologies used: 

- Java 15 (you need JDK 9 or higher to use modules)
- PostgreSQL 10
- Spring Boot, Spring Web MVC, Spring Data JPA
- Liquibase 
- Swagger 2.9.2

# Before start:

- Create PostgreSQL database named "conference-system" with username "postgres" and password "postgres". You can change username and password in properties files.
- At back-office-gateway module with using of liquibase plugin update DB. There are such datasets and changelog files in other modules, but they are disabled. 
- Run before starting an application:
> mvn clean install