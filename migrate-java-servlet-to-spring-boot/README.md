# Migrate Java Servlet to Spring Boot

This project demonstrates the migration of a Java Servlet application using Hibernate to a modern Spring Boot application with Spring Data JPA. The example application manages buildings and rooms, where a building can have many rooms.

## Project Overview

This repository contains both the original Servlet-based implementation and the migrated Spring Boot version to showcase the differences and improvements gained through migration.

### Domain Model

The application has two simple domain models with a one-to-many relationship:

- **Building**: Represents a building with properties like name.
- **Room**: Represents a room within a building with properties like name.

A building can have many rooms, and each room belongs to a single building.

## Migration Benefits

By migrating from Java Servlet/Hibernate to Spring Boot/JPA, this project demonstrates:

- Simplified configuration with less XML and more annotations
- Automatic REST API generation using Spring Data REST
- Improved dependency injection and lifecycle management
- Better testing capabilities with Spring Test
- Integrated security with Spring Security
- Streamlined database operations with Spring Data JPA repositories

## Project Structure

```
migrate-java-servlet-to-spring-boot/
├── 1-WebServlet/            # Original Servlet application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/        # Java source code
│   │   │   ├── resources/   # Configuration files
│   │   │   └── webapp/      # Web resources and JSPs
│   │   └── test/            # Test code
│   └── pom.xml              # Maven build file
│
└── 2-WebSpring/             # Migrated Spring Boot application
    ├── src/
    │   ├── main/
    │   │   ├── java/        # Java source code with Spring Boot components
    │   │   └── resources/   # Application properties and config
    │   └── test/            # Test code
    └── pom.xml              # Maven build file
```

## Key Migration Changes

### 1. Dependency Management

- **Servlet App**: Individual dependencies manually configured
- **Spring Boot**: Uses Spring Boot starters and auto-configuration

### 2. Entity Mapping

- **Servlet App**: Hibernate mapping files (XML) or annotations
- **Spring Boot**: JPA annotations with simplified configuration

### 3. Database Access

- **Servlet App**: Custom DAO classes with Hibernate Session management
- **Spring Boot**: Spring Data JPA repositories with automatic implementation

### 4. Web Layer

- **Servlet App**: Servlet classes handling HTTP requests directly
- **Spring Boot**: Spring MVC controllers or Spring Data REST endpoints

### 5. Deployment Configuration

- **Servlet App**: Web.xml and other configuration files
- **Spring Boot**: Uses `ServletInitializer` to deploy as a WAR within a traditional servlet container if needed

## Running the Applications

### Servlet Application (1-WebServlet)

```bash
cd 1-WebServlet
mvn clean install
mvn tomcat7:run
```

### Spring Boot Application (2-WebSpring)

```bash
cd 2-WebSpring
mvn clean install
mvn spring-boot:run
```

## API Examples

### Spring Boot REST Endpoints

- Get all buildings: `GET /api/buildings`
- Get a specific building: `GET /api/buildings/{id}`
- Create a building: `POST /api/buildings`
- Update a building: `PUT /api/buildings/{id}`
- Delete a building: `DELETE /api/buildings/{id}`
- Get rooms in a building: `GET /api/buildings/{id}/rooms`

## Technical Requirements

- Java 8 or higher
- Maven 3.6+
- Database (H2, MySQL, or PostgreSQL)
