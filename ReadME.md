# Course Management System

A comprehensive backend application built with Spring Boot for managing courses, instructors, and student registrations. This project demonstrates modern REST API development with JPA, layered architecture, and best practices for educational platform management.

## 🚀 Features

- **🔹 Instructor Management** - Complete CRUD operations (Add, Get, Update, Delete)
- **🔹 Course Management** - Full course lifecycle with instructor assignment
- **🔹 Student Registration** - Student enrollment system (Work in Progress)
- **🔹 Enum-based Categories** - Course categories and levels using Java enums
- **🔹 Custom Pagination** - Structured pagination response for all list APIs
- **🔹 Layered Architecture** - Clean separation with Controllers, Services, DTOs, and Repositories
- **🔹 PostgreSQL Integration** - Robust database integration with Spring Data JPA
- **🔹 RESTful APIs** - Well-structured REST endpoints following best practices

## 🛠️ Technologies Used

### Backend
- **Java 17** - Latest LTS version with modern features
- **Spring Boot** - Application framework and auto-configuration
- **Spring Data JPA** - Database access and ORM
- **PostgreSQL** - Primary database system
- **Maven** - Dependency management and build tool
- **Lombok** - Reduces boilerplate code
- **ModelMapper** - Object mapping between entities and DTOs

### Planned/Future
- **Docker** - Containerization support
- **Swagger/OpenAPI** - API documentation with SpringDoc or Swagger-UI

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **PostgreSQL** database
- **Git** for version control

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/n-akib/course-management-system.git
cd course-management-system
```

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE course_management;

-- Create user (optional)
CREATE USER course_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE course_management TO course_user;
```

### 3. Configure Database Connection
Update `application.properties` or `application.yml`:
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/course_management
spring.datasource.username=course_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 4. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 5. Access the Application
- **Base URL**: `http://localhost:8080`
- **API Base**: `http://localhost:8080/api`

## 🌐 API Endpoints

### Instructor Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/instructors` | List all instructors (paginated) |
| POST | `/api/instructors` | Add a new instructor |
| GET | `/api/instructors/{id}` | Get instructor by ID |
| PUT | `/api/instructors/{id}` | Update instructor |
| DELETE | `/api/instructors/{id}` | Delete instructor |

### Course Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/courses` | List all courses (paginated) |
| POST | `/api/courses` | Add a new course (assign instructor) |
| GET | `/api/courses/{id}` | Get course by ID |
| PUT | `/api/courses/{id}` | Update course |
| DELETE | `/api/courses/{id}` | Delete course |

### Student Management (Work in Progress)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | List all students (planned) |
| POST | `/api/students` | Register new student (planned) |
| POST | `/api/students/{id}/enroll` | Enroll in course (planned) |

## 🏗️ Project Structure

```
course-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── coursemanagement/
│   │   │           ├── CourseManagementApplication.java
│   │   │           ├── controller/
│   │   │           │   ├── InstructorController.java
│   │   │           │   ├── CourseController.java
│   │   │           │   └── StudentController.java
│   │   │           ├── service/
│   │   │           │   ├── InstructorService.java
│   │   │           │   ├── CourseService.java
│   │   │           │   └── StudentService.java
│   │   │           ├── repository/
│   │   │           │   ├── InstructorRepository.java
│   │   │           │   ├── CourseRepository.java
│   │   │           │   └── StudentRepository.java
│   │   │           ├── entity/
│   │   │           │   ├── Instructor.java
│   │   │           │   ├── Course.java
│   │   │           │   └── Student.java
│   │   │           ├── dto/
│   │   │           │   ├── InstructorDTO.java
│   │   │           │   ├── CourseDTO.java
│   │   │           │   └── StudentDTO.java
│   │   │           ├── enums/
│   │   │           │   ├── CourseCategory.java
│   │   │           │   └── CourseLevel.java
│   │   │           └── config/
│   │   │               └── ModelMapperConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (optional)
│   └── test/
├── pom.xml
└── README.md
```

## 📝 Core Components

### Entities
- **Instructor**: Manages instructor information and course assignments
- **Course**: Represents courses with categories, levels, and instructor relationships
- **Student**: Handles student data and course enrollments (WIP)

### DTOs (Data Transfer Objects)
- **InstructorDTO**: Data transfer for instructor operations
- **CourseDTO**: Course data representation for API responses
- **StudentDTO**: Student data structure (planned)

### Enums
- **CourseCategory**: Categories like PROGRAMMING, DESIGN, BUSINESS, etc.
- **CourseLevel**: Levels such as BEGINNER, INTERMEDIATE, ADVANCED

### Services
- **InstructorService**: Business logic for instructor management
- **CourseService**: Course operations and instructor assignment
- **StudentService**: Student enrollment logic (WIP)

## 🔧 Configuration

### Database Configuration
- **Connection Pool**: HikariCP (Spring Boot default)
- **JPA Provider**: Hibernate
- **Database**: PostgreSQL with full ACID compliance

### Pagination
- **Custom Pagination Response**: Structured response with metadata
- **Default Page Size**: Configurable page size for all list endpoints
- **Sorting Support**: Sort by various fields

## 📊 Sample API Usage

### Create an Instructor
```bash
curl -X POST http://localhost:8080/api/instructors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "expertise": "Java Development"
  }'
```

### Create a Course
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot Fundamentals",
    "description": "Learn Spring Boot from scratch",
    "category": "PROGRAMMING",
    "level": "BEGINNER",
    "instructorId": 1
  }'
```

### Get Paginated Courses
```bash
curl -X GET "http://localhost:8080/api/courses?page=0&size=10&sort=title,asc"
```

## 🧪 Testing

### Unit Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=InstructorServiceTest

# Run tests with coverage
mvn test jacoco:report
```

### Integration Tests
```bash
# Run integration tests
mvn test -Pintegration-tests
```

## 🐳 Docker Support (Planned)

### Docker Compose
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/course_management
  
  db:
    image: postgres:13
    environment:
      - POSTGRES_DB=course_management
      - POSTGRES_USER=course_user
      - POSTGRES_PASSWORD=password
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

## 📚 API Documentation

### Swagger Integration (Planned)
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>
```

Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🚀 Deployment

### Production Build
```bash
mvn clean package -Pprod
java -jar target/course-management-system-1.0.0.jar
```

### Environment Variables
```bash
export DB_HOST=your-db-host
export DB_PORT=5432
export DB_NAME=course_management
export DB_USER=course_user
export DB_PASSWORD=your-password
```

## 🔮 Future Enhancements

- **Student Registration System**: Complete student enrollment functionality
- **Authentication & Authorization**: JWT-based security
- **File Upload**: Course materials and instructor profile images
- **Email Notifications**: Course enrollment confirmations
- **Advanced Search**: Full-text search capabilities
- **Caching**: Redis integration for performance
- **Metrics & Monitoring**: Actuator endpoints and monitoring

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**n-akib** - [GitHub Profile](https://github.com/n-akib)

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- PostgreSQL team for reliable database system
- Open source contributors for various libraries used

---

**Building the future of education management! 🎓**