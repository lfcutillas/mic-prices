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

### Code Coverage
See the Jacoco reports for each module after compile in the target folder

### Solution criteria for query performance
The implementation was analyzed in different ways: the use of a JPA findTop or findFirst query, 
or a sub-query, or Pageable.

And the winner is: Efficiency + Flexibility + Legibility (JPQL query). In the hypothetical case that the pricing table is large and the query is frequent, efficiency may be an important factor.

Here are some of the considerations I took into account when deciding:

- Using Pageable: Can make your code simpler and easier to understand, so you can reuse the same query and method with 
different page sizes if you ever need to get more than one result, but can add a little overhead, since you are using 
a structure intended for pagination even when you only need one result.
- Using JPA: With all those parameters and conditions will end up with a very laaaaaargeMethodName, affecting readability and maintainability.
- Using Sub-query: The MAX sub-query is specifically designed to find the maximum value, which could make the query more efficient if you only need one result.
  - Less Overhead: There may be less overhead since you are focusing directly on finding the maximum without considering pagination. 
  - Disadvantages: Less Flexible and Complexity of the query.
- JPQL query: Is the preferred solution, the goal is to execute a single query and add order by priority, then in java take only the first element of the returned list.

Ultimately, choosing between these options depends on the specific situation and design preferences. 
It is advisable to perform performance testing in a production-like environment to evaluate performance under real-world conditions.

