# Spring Unit Test Project

## Overview
This project demonstrates unit testing practices for Spring Boot applications. It includes examples of testing controllers, services, repositories, and other Spring components using JUnit, Mockito, and Spring Test frameworks.

## Prerequisites
- Java 11 or higher
- Maven or Gradle
- Spring Boot 2.x or higher

## Getting Started

1. Build the project
   ```
   mvn clean install
   ```

2. Run the tests
   ```
   mvn test
   ```

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           ├── controllers
│   │           ├── models
│   │           ├── repositories
│   │           ├── services
│   │           └── Application.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── example
                ├── controllers
                ├── services
                └── repositories
```

## Testing Strategies

### Unit Tests
- Controller tests: Test the endpoints and request mappings
- Service tests: Test business logic
- Repository tests: Test data access

### Integration Tests
- Testing the full request-response cycle
- Testing component interactions

## Best Practices Demonstrated
- Using @MockBean vs @Mock
- Setting up TestRestTemplate
- Database testing with H2
- Testing exceptions
- Using assertj for readable assertions

## Additional Resources
- [Spring Testing Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://site.mockito.org/)

