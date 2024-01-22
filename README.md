# Prices Rest API
Queries the price info of a branded product.
Persist the data in an in-memory database (h2).
Expose a REST API to perform the query.

## API Documentation
Check the API documentation importing the file api.yml in [Swagger Editor](https://editor.swagger.io/)
path to file:
```
code/infrastructure/src/main/resources/api.yml
```

## Database Console
Manage the database in [H2 Console](http://localhost:8080/h2-console)

## Code Coverage
Run the following command to generate Jacoco report
```
mvn clean install
```

The main test: "PriceIntegrationTest" is in the following path:
```
code/boot/src/test/java
```

## Actuator
- For operational information about the running application
  (health, metrics, info, dump, env, etc.) go to [Actuator](http://localhost:8080/actuator)
- To see some options it is necessary to configure / enable them

## Architecture Comments
This multi-module project was designed using Hexagonal Architecture, with port and adapters pattern.
To ensure that the layers of the onion are met, the maven modules were defined as dependencies on each other in the 
following order: infrastructure -> application -> domain.
An additional module was created to wrap all spring-boot related classes.

When building a multi-module application we manage to make it robust, but we encounter new configuration challenges.
Takes time of research and configuration to make the following work:

### Integration Test Location
The integration test is in the boot module, since the main class (Application.class) is not discoverable from another module.
May be there's a more elegant solution.

### Sonarqube
Export Jacoco reports for each module and connect it to Sonarqube

### Dockerized App
The parent module will not generate a single jar, because the packaging needs to be specified as "pom". 
See workarounds to make a simple Dockerfile with a single .jar

An example of Dockerfile with single jar
  ``` 
  FROM openjdk:17-jdk-slim
  COPY code/target/*.jar mic-prices.jar
  ENV SERVER_PORT 8080
  EXPOSE ${SERVER_PORT}
  ENTRYPOINT ["java","-jar","/mic-prices.jar"]
  ``` 
