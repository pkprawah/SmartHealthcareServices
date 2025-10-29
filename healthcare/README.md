# 🏥 Healthcare Appointment System

A Spring Boot-based backend system for managing doctors, patients, appointments, availability slots, and medical specializations — with integrated Flyway DB migrations and Twilio SMS notification support.

---

## 🚀 Features

- 👨‍⚕️ Doctor & 👩‍💼 Patient registration with unique identifiers
- 📅 Appointment booking with time slot validation
- ⏱️ Doctor availability management
- 🩺 Specialization-based filtering
- 🔄 Many-to-many mapping between doctors and specializations
- 💬 Twilio SMS API integration
- 🐘 PostgreSQL + Flyway for versioned schema migrations
- 📡 RESTful APIs (Spring Web + JPA)

---

## 🧱 Tech Stack

| Layer      | Technology                     |
|------------|--------------------------------|
| Backend    | Java 17, Spring Boot           |
| Database   | PostgreSQL                     |
| ORM        | Hibernate (JPA)                |
| Migration  | Flyway                         |
| Messaging  | Twilio (optional)              |
| Build Tool | Maven                          |

---

## 🗃️ Project Structure

src/
└── main/
├── java/com/teembinsys/core/healthcare/
│ ├── controller/
│ ├── service/
│ ├── repository/
│ ├── model/
│ └── dto/
└── resources/
├── application.properties
└── db/migration/ <-- Flyway SQL scripts

---

## 🛠️ Setup Instructions

### 1. 🐘 Set Up PostgreSQL

```bash
CREATE DATABASE health-development;
spring.datasource.url=jdbc:postgresql://localhost:5432/health-development
spring.datasource.username=postgres
spring.datasource.password=Postgres

spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true

mvn clean install
mvn spring-boot:run


📦 Flyway Migration Examples
SQL files go in: src/main/resources/db/migration


✅ Naming Convention
Flyway migration files must follow this pattern:

V<version>__<description>.sql

run flyway command
 mvn flyway:migrate
 
V1_1__create_specialization_table.sql
V1_2__create_doctor_table.sql
V1_3__create_patient_table.sql
V1_4__create_doctor_slots_table.sql
V1_5__create_appointment_table.sql
V1_6__create_doctor_specializations_table.sql



👥 Contributors
 Pawan Kumar


📄 License
This project is open-source under the MIT License


---

Would you like me to:
- Export this as a downloadable `README.md` file?
- Add curl/postman examples?
- Include test coverage or Swagger/OpenAPI instructions?

Let me know!
