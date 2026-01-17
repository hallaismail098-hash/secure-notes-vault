# Secure Notes Vault 🔐💖
**Application Security Project (Spring Boot + JWT + AES + RBAC)**

##  Project Overview
Secure Notes Vault is a secure web application that allows users to register, login, and manage private notes.
The system applies multiple security controls such as authentication, authorization, encryption, and secure password storage.

---

##  Features Implemented

###  Authentication & Session Management
- Register new users
- Login using email + password
- JWT Token-based authentication (stateless session)

###  Password Security
- Passwords are stored as **BCrypt hashes** (NOT plain text)

###  Authorization (Role Based Access Control)
- Two roles: **USER** and **ADMIN**
- Protected endpoints:
    - `/api/notes/**` → USER or ADMIN
    - `/api/admin/**` → ADMIN only

###  Notes Management (CRUD)
- Create notes
- View all notes (only your own)
- Update note
- Delete note

###  Data Protection (AES Encryption)
- Notes content is encrypted using **AES-GCM**
- Notes are stored in the database in encrypted form (ciphertext + IV)

###  Input Validation
- Validation annotations are used for user input such as:
    - valid email format
    - required fields
    - length limits

---

##  Frontend Pages
The project includes a simple web interface using HTML templates:

- Login page: `http://localhost:8080/login`
- Register page: `http://localhost:8080/register`
- Notes page: `http://localhost:8080/notes`

---

##  Technologies Used
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (io.jsonwebtoken)
- AES-GCM Encryption
- HTML + JavaScript frontend (templates)

---

##  How to Run the Project

### 1) Open the project in IntelliJ
### 2) Run the Spring Boot application:
Run:
- `Application.java`

Expected output:
- `Tomcat started on port 8080`

### 3) Open in Browser
Go to:
- `http://localhost:8080/login`

---

##  API Endpoints

###  Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

###  Notes (JWT Protected)
- `POST /api/notes`
- `GET /api/notes`
- `PUT /api/notes/{id}`
- `DELETE /api/notes/{id}`

###  Admin (ADMIN only)
- `GET /api/admin/users`
- `PUT /api/admin/users/{id}/enabled?enabled=true|false`

---

##  Admin Login
An admin account can be created using a seeder or by updating role in database (H2).

Example Admin:
- Email: `admin@test.com`
- Password: `admin123`

---

##  Documentation
Documentation files included in:

- `docs/STRIDE.md`
- `docs/DREAD.md`
- `docs/SecurityTools.md`

Screenshots folder (optional):
- `docs/screenshots/`

---

##  Screenshots (Optional)
Add screenshots here:
- `docs/screenshots/`

Example:
```md
![Login Page](desktop/Login)
![Notes Page](desktop/Register
)
