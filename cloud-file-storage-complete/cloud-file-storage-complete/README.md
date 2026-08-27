# Cloud File Storage System

Spring Boot + MySQL project for a secure cloud file storage demonstration.

Features:
- Registration and login
- Role-based access (USER / ADMIN)
- File upload, download and delete
- AES-256-GCM encryption before saving files
- MySQL metadata storage
- Admin dashboard

Requirements:
- JDK 17+
- MySQL 8+
- Maven 3.9+

Setup:
1. Create database: CREATE DATABASE cloud_file_storage;
2. Open src/main/resources/application.properties.
3. Replace YOUR_MYSQL_PASSWORD with your MySQL root password.
4. Run: mvn spring-boot:run
5. Open http://localhost:8080

Default admin:
username: admin
password: admin123

Change the admin password before real deployment. The encryption key in application.properties is a demo key; use FILE_ENCRYPTION_KEY in real deployment.
