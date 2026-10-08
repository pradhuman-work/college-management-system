# 🎓 College Management System

[![Java](https://img.shields.io/badge/Java-22-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-green.svg)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-7.x-blue.svg)](https://hibernate.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-red.svg)](https://maven.apache.org/)

A RESTful **College Management System API** built with **Java, Spring Boot, Spring Data JPA, Hibernate and PostgreSQL**.

The project manages **students, professors, subjects and admission records**, while demonstrating the major JPA relationship types:

- One-to-One
- One-to-Many
- Many-to-One
- Many-to-Many

It also demonstrates DTO-based API design, transaction management, validation, lazy loading, EntityGraph optimization and centralized exception handling.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [Entity Relationships](#-entity-relationships)
- [Technology Stack](#-technology-stack)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Request Examples](#-request-examples)
- [Response Format](#-response-format)
- [Database Design](#-database-design)
- [Error Handling](#-error-handling)
- [Design Decisions](#-design-decisions)
- [What I Learned](#-what-i-learned)
- [Future Improvements](#-future-improvements)

---

# 📌 Project Overview

The **College Management System** is a backend REST API designed to manage common college entities and their relationships.

The application is built using a layered architecture:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

The main entities are:

- 👨‍🎓 **Student** – stores student information
- 👨‍🏫 **Professor** – stores professor information
- 📚 **Subject** – stores subject information
- 📝 **Admission Record** – stores admission and fee information

---

# 🚀 Key Features

### 👨‍🎓 Student Management

- Create students
- View all students
- View a student by ID
- Update students using PUT/PATCH
- Delete students
- Enroll students in subjects
- Link students with professors
- Manage admission records

### 👨‍🏫 Professor Management

- Create professors
- View professors
- Update professors
- Delete professors
- Assign subjects
- Manage student-professor relationships

### 📚 Subject Management

- Create subjects
- Assign subjects to professors
- Reassign professors
- Enroll students
- Remove student enrollments

### 📝 Admission Management

- Create admission records
- View admission details
- Update fees
- Delete admission records
- Maintain a one-to-one relationship between student and admission record

### ⚙️ Backend Features

- RESTful API design
- DTO-based request/response handling
- Bean Validation
- Global exception handling
- Transaction management
- Lazy loading
- EntityGraph optimization
- Hibernate dirty checking
- Idempotent relationship APIs
- PostgreSQL persistence

---

# 🏗️ System Architecture

The application follows a layered architecture.

```text
                    REST Client
                        │
                        ▼
               ┌─────────────────┐
               │   Controller    │
               │ HTTP Layer      │
               └────────┬────────┘
                        │
                        ▼
               ┌─────────────────┐
               │    Service      │
               │ Business Logic  │
               └────────┬────────┘
                        │
                        ▼
               ┌─────────────────┐
               │   Repository    │
               │ Data Access     │
               └────────┬────────┘
                        │
                        ▼
               ┌─────────────────┐
               │   PostgreSQL    │
               │    Database     │
               └─────────────────┘
```

### Responsibilities

| Layer | Responsibility |
|---|---|
| Controller | Handles HTTP requests and responses |
| Service | Business logic and transactions |
| Repository | Database operations using Spring Data JPA |
| Entity | Database mapping and relationships |
| DTO | API request and response models |
| Exception | Centralized error handling |

---

# 🔗 Entity Relationships

The project demonstrates four major relationship patterns.

## 1. Professor → Subject

**One-to-Many / Many-to-One**

```text
Professor
    │
    │ 1
    │
    │
    ▼
   N
Subject
```

One professor can be associated with multiple subjects.

The relationship is stored using:

```text
cms_subjects.professor_id
```

---

## 2. Student ↔ Subject

**Many-to-Many**

```text
Student
   │
   │
   ▼
student_subject
   ▲
   │
   │
Subject
```

A student can enroll in multiple subjects, and a subject can have multiple students.

The relationship is maintained through:

```text
student_subject
```

---

## 3. Student ↔ Professor

**Many-to-Many**

```text
Student
   │
   │
   ▼
student_professor
   ▲
   │
   │
Professor
```

The relationship is maintained through:

```text
student_professor
```

---

## 4. Student → Admission Record

**One-to-One**

```text
Student
   │
   │ 1
   │
   ▼
Admission Record
```

Each student can have at most one admission record.

The relationship is enforced using a unique `student_id`.

---

# 🗄️ Database Design

### Main Tables

| Table | Purpose |
|---|---|
| `cms_students` | Student information |
| `cms_professors` | Professor information |
| `cms_subjects` | Subject information |
| `admission_record` | Student admission and fees |
| `student_subject` | Student-subject relationship |
| `student_professor` | Student-professor relationship |

### Relationship Overview

```text
cms_professors
      │
      │ 1:N
      ▼
cms_subjects


cms_students
      │
      ├──────── N:N ──────── cms_subjects
      │
      ├──────── N:N ──────── cms_professors
      │
      └──────── 1:1 ──────── admission_record
```

---

# 🛠️ Technology Stack

| Technology | Version | Purpose |
|---|---:|---|
| Java | 22 | Programming language |
| Spring Boot | 4.1.1 | Backend framework |
| Spring Web MVC | — | REST API |
| Spring Data JPA | — | Persistence layer |
| Hibernate | 7.4.5 | ORM |
| PostgreSQL | — | Relational database |
| HikariCP | — | Database connection pool |
| Jakarta Bean Validation | — | Request validation |
| ModelMapper | — | Entity ↔ DTO mapping |
| Lombok | — | Boilerplate reduction |
| Maven | — | Build tool |
| Postman | — | API testing |
| DBeaver | — | Database management |
| IntelliJ IDEA | — | Development environment |

---

# 🚀 Getting Started

## Prerequisites

Before running the project, make sure you have:

- Java 22
- PostgreSQL
- Git
- Maven or Maven Wrapper
- IntelliJ IDEA (recommended)
- Postman (recommended)
- DBeaver (optional)

---

## 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd YOUR_PROJECT_FOLDER
```

---

## 2. Create the PostgreSQL Database

Open PostgreSQL and create the database:

```sql
CREATE DATABASE college_db;
```

---

## 3. Configure Database Connection

Open:

```text
src/main/resources/application.properties
```

Configure your PostgreSQL connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/college_db
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:root}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

### Configuration Notes

`DB_PASSWORD` can be supplied as an environment variable.

For example:

```bash
DB_PASSWORD=your_password
```

For local development, the fallback value can be used.

> ⚠️ Do not commit real production database credentials to GitHub.

---

## 4. Build the Project

Using Maven Wrapper:

### Windows

```bash
mvnw.cmd clean install
```

### Linux/macOS

```bash
./mvnw clean install
```

Or using Maven:

```bash
mvn clean install
```

---

## 5. Run the Application

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Or run the main application class from IntelliJ IDEA.

---

## 6. Verify the Application

Once the application starts, the API will be available at:

```text
http://localhost:8080
```

Test:

```http
GET http://localhost:8080/professors
```

Expected response:

```json
[]
```

A `200 OK` response confirms that the application is running.

---

# 📡 API Endpoints

## 👨‍🏫 Professors

Base URL:

```text
/professors
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/professors` | Create professor |
| GET | `/professors` | Get all professors |
| GET | `/professors/{id}` | Get professor |
| PUT | `/professors/{id}` | Replace professor |
| PATCH | `/professors/{id}` | Partially update professor |
| DELETE | `/professors/{id}` | Delete professor |

---

## 📚 Subjects

Base URL:

```text
/subjects
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/subjects` | Create subject |
| POST | `/subjects/professor/{professorId}` | Create subject with professor |
| GET | `/subjects` | Get all subjects |
| GET | `/subjects/{id}` | Get subject |
| PUT | `/subjects/{id}` | Replace subject |
| PATCH | `/subjects/{id}` | Partially update subject |
| DELETE | `/subjects/{id}` | Delete subject |
| PUT | `/subjects/{subjectId}/professor/{professorId}` | Assign/reassign professor |
| DELETE | `/subjects/{subjectId}/professor` | Remove professor |

---

## 👨‍🎓 Students

Base URL:

```text
/students
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students` | Create student |
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get student |
| PUT | `/students/{id}` | Replace student |
| PATCH | `/students/{id}` | Partially update student |
| DELETE | `/students/{id}` | Delete student |
| POST | `/students/{studentId}/subjects/{subjectId}` | Enroll student |
| DELETE | `/students/{studentId}/subjects/{subjectId}` | Unenroll student |
| PUT | `/students/{studentId}/professors/{professorId}` | Link professor |
| DELETE | `/students/{studentId}/professors/{professorId}` | Unlink professor |

---

## 📝 Admission Records

Base URL:

```text
/students/{studentId}/admission-record
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students/{studentId}/admission-record` | Create admission record |
| GET | `/students/{studentId}/admission-record` | Get admission record |
| PUT | `/students/{studentId}/admission-record` | Replace fees |
| PATCH | `/students/{studentId}/admission-record` | Update fees |
| DELETE | `/students/{studentId}/admission-record` | Delete admission record |

A student can have **at most one admission record**.

---

# 🧪 API Request Examples

## Create Professor

```http
POST /professors
Content-Type: application/json
```

```json
{
  "title": "Dr. Sharma"
}
```

Response:

```json
{
  "id": 1,
  "title": "Dr. Sharma",
  "studentIds": []
}
```

---

## Create Subject

```http
POST /subjects
Content-Type: application/json
```

```json
{
  "title": "Mathematics"
}
```

---

## Create Student

```http
POST /students
Content-Type: application/json
```

```json
{
  "name": "Rahul"
}
```

Response:

```json
{
  "id": 1,
  "name": "Rahul",
  "subjectIds": [],
  "professorIds": [],
  "admissionRecordId": null
}
```

---

## Enroll Student in Subject

```http
POST /students/1/subjects/2
```

This creates the relationship between student `1` and subject `2`.

---

## Create Admission Record

```http
POST /students/1/admission-record
Content-Type: application/json
```

```json
{
  "fees": 50000
}
```

---

# 📤 Response Format

The API returns related entity IDs instead of complete nested objects.

### Student

```json
{
  "id": 1,
  "name": "Rahul",
  "subjectIds": [1, 2],
  "professorIds": [1],
  "admissionRecordId": 1
}
```

### Subject

```json
{
  "id": 2,
  "title": "Physics",
  "professorId": 1
}
```

### Admission Record

```json
{
  "id": 1,
  "fees": 50000,
  "studentId": 1
}
```

This keeps the API response compact and prevents infinite JSON recursion caused by bidirectional relationships.

---

# ⚠️ Error Handling

The application uses centralized exception handling through `@RestControllerAdvice`.

### 404 — Resource Not Found

```json
{
  "status": 404,
  "message": "Student not found with id 999",
  "timestamp": "2026-10-06T16:34:31"
}
```

### 400 — Validation Error

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "name": "must not be blank"
  },
  "timestamp": "2026-10-06T16:36:02"
}
```

### 409 — Conflict

```json
{
  "status": 409,
  "message": "Student 1 already has an admission record",
  "timestamp": "2026-10-06T16:38:45"
}
```

### HTTP Status Codes

| Status | Meaning |
|---:|---|
| 200 | Successful request |
| 201 | Resource created |
| 204 | Resource deleted |
| 400 | Invalid request / validation failure |
| 404 | Resource not found |
| 409 | Resource conflict |

---

# 📁 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com.collegeManagementSystem.learning/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── entity/
    │       ├── dto/
    │       ├── exception/
    │       ├── ModelMapperConfig
    │       └── LearningApplication
    │
    └── resources/
        └── application.properties
```

### Package Responsibilities

```text
controller/
    REST endpoints

service/
    Business logic
    Transactions
    DTO mapping

repository/
    Spring Data JPA repositories

entity/
    JPA entities
    Relationship mappings

dto/
    Request DTOs
    PATCH DTOs
    Response DTOs

exception/
    Custom exceptions
    Error response
    Global exception handler
```

---

# 🧠 Design Decisions

### DTOs

The controllers do not directly expose JPA entities.

Instead:

```text
Request
   ↓
Request DTO
   ↓
Service
   ↓
Entity
   ↓
Database
```

This prevents clients from directly modifying IDs or relationship collections.

---

### PUT vs PATCH

**PUT**

Used when replacing the complete resource.

```http
PUT /students/1
```

**PATCH**

Used when only specific fields need to be changed.

```http
PATCH /students/1
```

---

### Transactions

Business operations are handled inside service-layer transactions.

```text
Controller
    ↓
@Transactional Service
    ↓
Repository
```

This ensures multiple database operations belonging to one business operation are executed consistently.

---

### Lazy Loading and EntityGraph

Relationships are kept lazy by default.

For endpoints that require related data, `EntityGraph` is used to load the required relationships efficiently and reduce unnecessary queries.

---

### Set Instead of List

Relationship collections use `Set` to:

- Prevent duplicate relationships
- Represent unique associations
- Avoid unnecessary collection replacement behavior in Hibernate

---

### Centralized Exception Handling

Exceptions are converted into a consistent API response through a global exception handler.

This keeps error handling out of individual controllers.

---

# 📚 What I Learned

## JPA & Hibernate

- One-to-One relationships
- One-to-Many / Many-to-One relationships
- Many-to-Many relationships
- Owning and inverse sides
- `mappedBy`
- `@JoinColumn`
- `@JoinTable`
- Cascade and orphan removal
- Lazy loading
- EntityGraph
- Hibernate dirty checking
- N+1 query problem

## REST API Development

- REST HTTP methods
- PUT vs PATCH
- HTTP status codes
- Request validation
- DTO-based API design
- Nested resources
- Idempotent operations
- Global exception handling

## Spring Boot

- Layered architecture
- Dependency injection
- `@Transactional`
- Spring Data JPA
- Bean Validation
- Service/repository separation

---

# 🔮 Future Improvements

The following improvements can be added in future versions:

- [ ] Automated unit tests
- [ ] Integration tests with Testcontainers
- [ ] Swagger / OpenAPI documentation
- [ ] Spring Security
- [ ] Authentication and role-based authorization
- [ ] Pagination and sorting
- [ ] Flyway/Liquibase database migrations
- [ ] Production configuration profiles
- [ ] Improved query optimization
- [ ] `BigDecimal` for fee/money values
- [ ] Docker support
- [ ] CI/CD pipeline

---

# 🚀 Deployment

The backend can be deployed using a cloud platform that supports Java/Spring Boot applications and PostgreSQL.

Recommended architecture:

```text
GitHub
   │
   ▼
Spring Boot Application
   │
   ▼
PostgreSQL
```

For local development:

```text
Spring Boot
     │
     ▼
localhost:5432
PostgreSQL
```

For production, database credentials should be supplied through environment variables rather than stored in `application.properties`.

---

# 👨‍💻 Author

**Pradhuman Singh Rathore**

Backend Software Engineer  
Java | Spring Boot | REST APIs | SQL

---

⭐ If you found this project useful, consider giving the repository a star.
