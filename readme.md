# Spring Microservices Boilerplate
## Includes the following dependencies:
- Spring API gateway
- Netflix Eureka discovery service
- Resilience4J circuit breaker
- Spring Actuator
- Prometheus
## Current services:
- api-gateway
- discovery-service
## How to add your own microservices
- Write all properties in a properties file in the "properties" directory for your microservice. You may choose to keep it private.
- Write properties you want to be common across all your microservices in ```common.properties```.
- Import your properties file using ```spring.config.import=file:./properties/<Your-File>```.
- Import ```common.properties``` file in all other properties files.
- The root pom.xml has the dependencies:
  - ```spring-cloud-starter-netflix-eureka-client```, for discovery service.
  - ```spring-cloud-starter-circuitbreaker-resilience4j```, for circuit breaker.
  - ```spring-boot-starter-actuator```, for monitoring health.
  - ```micrometer-registry-prometheus```, for monitoring service.
  - ```spring-boot-starter-test```, it can be common for testing across all modules.
- Use this pom.xml as parent of your new project and add dependencies.
- Add dependencies that you need across all your microservices in the root pom.xml.

## Future work
- Adding authorization service to api-gateway.
- Integrating Zipkin.