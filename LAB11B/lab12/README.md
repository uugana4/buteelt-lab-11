# Lab12 Spring Boot Auth API

This project implements the lab requirements for:
- `POST /api/register`
- `POST /api/login`

## Tech Stack

- Java 17
- Spring Boot (Web, Security, Data JPA)
- H2 in-memory database
- BCrypt password hashing

## Run

```bash
mvn clean compile
mvn spring-boot:run
```

Server starts at `http://localhost:8080`.

## API Test Examples

Register:

```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

Login:

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

## Notes

- Duplicate usernames are rejected.
- Passwords are stored hashed using BCrypt.
- CORS allows `http://localhost:3000`.
- H2 console is enabled at `http://localhost:8080/h2-console`.
