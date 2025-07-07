# 📚 Course Management System

A backend application built using **Spring Boot** to manage courses, instructors, and student registrations. This project demonstrates the use of REST APIs, JPA, and layered architecture for an educational platform.

---

## 🚀 Features

- 🔹 Instructor Management (Add, Get, Update, Delete)
- 🔹 Course Management with Instructor assignment
- 🔹 Student Registration (WIP)
- 🔹 Enum usage for Course Categories and Levels
- 🔹 Custom pagination response for all list APIs
- 🔹 Structured with best practices: Controller, Service, DTOs, Repositories
- 🔹 PostgresSQL Integration

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgresSQL**
- **Maven**
- **Lombok**
- **ModelMapper**
- **Docker** *(planned)*

---

## 🧾 API Endpoints

> Swagger/OpenAPI documentation can be integrated with SpringDoc or Swagger-UI.

### Instructor APIs

- `GET /api/instructors` – List all instructors (paginated)
- `POST /api/instructors` – Add a new instructor
- `PUT /api/instructors/{id}` – Update instructor
- `DELETE /api/instructors/{id}` – Delete instructor

### Course APIs

- `GET /api/courses` – List all courses
- `POST /api/courses` – Add a new course (assign instructor)
- `PUT /api/courses/{id}` – Update course
- `DELETE /api/courses/{id}` – Delete course

> Student APIs are under development.

---

## 📦 Getting Started

### ✅ Prerequisites

- Java 17
- Maven
- PostgresSQL

### ⚙️ Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/n-akib/course-management-system.git
   cd course-management-system
