# Car Dealership API

Find the perfect car for you!

---

## Table of Contents

- [Project Overview](#project-overview)
- [Instructions for Initial Setup](#instructions)
- [RESTful Routes](#restful-routes)
- [Testing](#testing)
- [Dependencies](#dependencies)

---

## Project Overview

- This API was created using **Java, Spring Boot, PostgreSQL, and Hibernate**.
- The API contains a **one-to-many relationship** between models.
- Models include: **Car, Customer, Dealer, Dealership, and Purchase**.
- Users can filter cars based on **make, model, year, and price**.
- RESTful endpoints allow users to **create, retrieve, update, and delete records**.

---

## Instructions

Ensure you have the following installed:

- **Java 23**
- **A Java IDE** (e.g., IntelliJ IDEA, Eclipse)
- **PostgreSQL** (Database Manager)
- **Postman** (for API testing)

### Application properties

Ensure your `application.properties` file contains the following configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/car_dealership
spring.datasource.username=postgres
spring.datasource.password=2309
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database=postgresql
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

## RESTful Routes

### Base URL: `localhost:8080/api`

#### **GET:** Retrieve records

- Cars: `/api/cars?make=Toyota&model=Camry&year=2020&price=25000`
- Customers: `/api/customers?name=John`
- Dealers: `/api/dealers?name=Sam`
- Dealerships: `/api/dealerships?location=London`
- Purchases: `/api/purchases?date=2024-02-01`

#### **SHOW:** Retrieve a single record

- `/api/cars/{id}`
- `/api/customers/{id}`
- `/api/dealers/{id}`
- `/api/dealerships/{id}`
- `/api/purchases/{id}`

#### **POST:** Create a new record

- `/api/cars`
- `/api/customers`
- `/api/dealers`
- `/api/dealerships`
- `/api/purchases`

#### **PUT:** Update an existing record

- `/api/cars/{id}`
- `/api/customers/{id}`
- `/api/dealers/{id}`
- `/api/dealerships/{id}`
- `/api/purchases/{id}`

#### **DELETE:** Remove a record

- `/api/cars/{id}`
- `/api/customers/{id}`
- `/api/dealers/{id}`
- `/api/dealerships/{id}`
- `/api/purchases/{id}`

---

## Testing

- **TDD (Test-Driven Development)** was followed using **JUnit and Mockito**.
- Each entity (**Car, Customer, Dealer, Dealership, and Purchase**) has its own test suite.
- Example **CarServiceTest** includes:
    - Saving a car
    - Retrieving all cars
    - Updating a car
    - Deleting a car
    - Filtering cars by attributes

### Example test:

```java
@Test
public void testSaveCar() {
    Dealership dealership = new Dealership("Toyota Dealership", "123 Main St");
    Car car = new Car("Toyota", "S Series", 2019, 20000, dealership);

    when(carRepository.save(car)).thenReturn(car);

    var savedCar = carService.saveCar(car);

    assertEquals(car, savedCar);
    verify(carRepository, times(1)).save(car);
}
```

---

## Dependencies

The project uses the following **Maven dependencies**:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.0.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## Author

This API was developed as a backend project using **Spring Boot, PostgreSQL, and Hibernate**, implementing **TDD** principles and ensuring **RESTful API design**.