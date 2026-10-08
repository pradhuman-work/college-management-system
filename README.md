# 🎓 College Management System

A REST API built with **Java, Spring Boot, Spring Data JPA, Hibernate and PostgreSQL** for managing a college's:

- 👨‍🏫 Professors
- 📚 Subjects
- 👨‍🎓 Students
- 📝 Admission Records

The project demonstrates how to build a layered Spring Boot application and work with **One-to-Many, Many-to-One, Many-to-Many and One-to-One** JPA relationships.

## 🚀 Live API

**Base URL:** `YOUR_DEPLOYED_URL`

Example:

```text
https://your-project.up.railway.app
```

> The API is currently deployed for demonstration purposes.  
> PostgreSQL is used as the application's persistent database.

---

## 🧑‍💻 Quick Start

If you just want to run the project locally, follow these steps.

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
cd YOUR_REPOSITORY
```

### 2. Requirements

Make sure you have:

- Java 22
- PostgreSQL
- Git
- Maven (optional — the project includes Maven Wrapper)

### 3. Create the database

Open PostgreSQL and run:

```sql
CREATE DATABASE college_db;
```

### 4. Configure the database

Create/update:

```text
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/college_db
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:root}
```

> For local development, the default password is `root`.
> For deployment, always use an environment variable instead of committing a password.

### 5. Start the application

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Or run `LearningApplication` directly from IntelliJ IDEA.

The API will start at:

```text
http://localhost:8080
```

### 6. Test the API

Try:

```http
GET http://localhost:8080/professors
```

Expected response:

```json
[]
```

If you receive `200 OK`, the application is running successfully.

---

# 📌 What Can You Do With This API?

### Professors

Create, view, update and delete professors.

```http
POST   /professors
GET    /professors
GET    /professors/{id}
PUT    /professors/{id}
PATCH  /professors/{id}
DELETE /professors/{id}
```

### Subjects

Create subjects and assign professors.

```http
POST   /subjects
GET    /subjects
GET    /subjects/{id}
PUT    /subjects/{id}
PATCH  /subjects/{id}
DELETE /subjects/{id}

POST   /subjects/professor/{professorId}
PUT    /subjects/{subjectId}/professor/{professorId}
DELETE /subjects/{subjectId}/professor
```

### Students

Manage students and their subject/professor relationships.

```http
POST   /students
GET    /students
GET    /students/{id}
PUT    /students/{id}
PATCH  /students/{id}
DELETE /students/{id}
```

Enroll a student:

```http
POST /students/{studentId}/subjects/{subjectId}
```

Remove enrollment:

```http
DELETE /students/{studentId}/subjects/{subjectId}
```

Link a professor:

```http
PUT /students/{studentId}/professors/{professorId}
```

### Admission Records

Each student can have at most one admission record.

```http
POST   /students/{studentId}/admission-record
GET    /students/{studentId}/admission-record
PUT    /students/{studentId}/admission-record
PATCH  /students/{studentId}/admission-record
DELETE /students/{studentId}/admission-record
```

---

# 🧪 Example: Create a Student

### Request

```http
POST /students
Content-Type: application/json
```

```json
{
  "name": "Rahul"
}
```

### Response

```json
{
  "id": 1,
  "name": "Rahul",
  "subjectIds": [],
  "professorIds": [],
  "admissionRecordId": null
}
```

You can then use the returned `id` to create relationships.

For example:

```http
POST /students/1/subjects/2
```

This enrolls student `1` in subject `2`.

---

# 🏗️ Architecture

The application follows a layered architecture:

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

### Project structure

```text
controller/     → REST API endpoints
service/        → Business logic & transactions
repository/     → Database access
entity/         → JPA entities & relationships
dto/            → Request/response objects
exception/      → Error handling
```

---

# 🗄️ Database Relationships

```text
Professor
   │
   │ 1 : N
   ▼
Subject


Student
   │
   ├──── N : N ──── Subject
   │
   ├──── N : N ──── Professor
   │
   └──── 1 : 0..1 ──── AdmissionRecord
```

The project intentionally demonstrates all major JPA relationship types:

| Relationship | Type |
|---|---|
| Professor → Subject | One-to-Many / Many-to-One |
| Student ↔ Subject | Many-to-Many |
| Student ↔ Professor | Many-to-Many |
| Student ↔ Admission Record | One-to-One |

---

# ⚠️ Important

This project is primarily an **educational/demo REST API**.

For production use, consider adding:

- Authentication & authorization
- Spring Security
- Automated tests
- Flyway/Liquibase migrations
- Swagger/OpenAPI documentation
- Pagination and sorting
- Production database configuration
- Proper monetary representation using `BigDecimal`
- Separate `dev` and `prod` profiles

---

## 📚 More Documentation

For deeper technical details, see:

- Database schema
- JPA relationship mappings
- Request → database mapping
- Error handling
- Transaction management
- Lazy loading and EntityGraph
- Design decisions
- Lessons learned
- Future improvements
