# Java Servlet + Hibernate Unit Testing Example

This project demonstrates how to write unit tests for a Java servlet application using Hibernate ORM.

## Project Overview

The application implements a simple web service with:
- A single `Building` model containing only building name
- Servlets for handling HTTP requests (GET/POST)
- Hibernate integration for database operations
- Unit tests to validate functionality

## Project Structure

- `Building.java`: Simple model class representing a building
- `BuildingServlet.java`: Servlet for handling building CRUD operations
- `AuthFilter.java`: Simple authentication filter
- `HibernateUtils.java`: Utility class for Hibernate session management
- `CommonUtils.java`: General utilities
- `BuildingServletTest.java`: Unit tests for the BuildingServlet

## Testing Approach

The project showcases how to write effective unit tests for Java servlets by:
- Mocking HTTP request/response objects
- Mocking Hibernate sessions and transactions
- Testing various scenarios including error handling
- Using Mockito for mocking dependencies

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Running the Project

1. Build and run tests:
   ```bash
   mvn clean install
   ```

### Test Cases

The unit tests cover several scenarios:
- Creating a new building with valid data
- Handling empty building names
- Retrieving the list of buildings
- Error handling for database exceptions
- Testing with various building collections (empty, single, multiple)
