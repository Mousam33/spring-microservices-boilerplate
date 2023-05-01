# Spring Microservices Boilerplate
## Includes the following dependencies:
- Spring API gateway
- Netflix Eureka discovery service
- Resilience4J circuit breaker
- Spring Actuator
- Prometheus
- Config server
- OAuth2
## Current services:
- api-gateway
- discovery-service
- config-server
## How to add your own microservices
- Write all properties in a properties file in the "properties" directory for your microservice. You may choose to keep it private.
- Write properties you want to be common across all your microservices in ```application.properties```.
- Write properties you want to be specific to a microservice in ```<Microservice-Name>.properties```.
- Import your properties file using ```spring.cloud.config.uri=<Your-Config-Server-URL>```.
- Import ```application.properties``` file in all other properties files or just use the config server (as it automatically imports this).
- For managing OAuth2 secrets I have used command line arguments ```--spring.security.oauth2.client.registration.google.clientId=<ClientId>``` and ```--spring.security.oauth2.client.registration.google.clientSecret=<ClientSecret>```
- The root pom.xml has the dependencies:
  - ```spring-cloud-starter-netflix-eureka-client```, for discovery service.
  - ```spring-cloud-starter-circuitbreaker-resilience4j```, for circuit breaker.
  - ```spring-boot-starter-actuator```, for monitoring health.
  - ```micrometer-registry-prometheus```, for monitoring service.
  - ```spring-cloud-starter-config```, for config clients.
  - ```spring-boot-starter-test```, it can be common for testing across all modules.
- Use this pom.xml as parent of your new project and add dependencies.
- Add dependencies that you need across all your microservices in the root pom.xml.

## Future work
- Integrating Zipkin.
