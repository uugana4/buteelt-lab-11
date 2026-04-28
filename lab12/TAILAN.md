# Лабораторийн ажлын тайлан

**Хичээл:** F.CSA311 Программ хангамжийн бүтээлт  
**Лаб №:** 11b / Lab12  
**Сэдэв:** Spring Boot Framework ашиглан хэрэглэгчийн бүртгэл ба нэвтрэх REST API хөгжүүлэлт

**Оюутны нэр:** ____________  
**Групп:** ____________  
**Огноо:** ____________

---

## 1. Ажлын зорилго

Java Spring Boot ашиглан backend талд:
- хэрэглэгч бүртгэх (`POST /api/register`)
- хэрэглэгч нэвтрэх (`POST /api/login`)

REST API-уудыг хэрэгжүүлэх.

## 2. Ашигласан технологи

- Java 17
- Spring Boot 3
- Spring Web
- Spring Security
- Spring Data JPA
- H2 in-memory database
- Maven

## 3. Хийж хэрэгжүүлсэн ажил

### 3.1 Төслийн бүтэц

Төслийг стандарт Spring Boot бүтэцтэй болгосон:
- `src/main/java/sict/edu/mn/lab12/...`
- `src/main/resources/application.properties`
- `pom.xml`

### 3.2 Entity ба Repository

- `User` entity: `id`, `username`, `password`, `role`
- `username` талбарыг unique болгосон
- `UserRepository` дээр `findByUsername(...)` метод ашигласан

### 3.3 Service давхарга

- `registerUser(...)` функцээр хэрэглэгч бүртгэдэг
- Давхардсан username-ийг шалгадаг
- Нууц үгийг BCrypt-р hash хийж хадгалдаг
- `findByUsername(...)` функцээр хэрэглэгч хайдаг

### 3.4 Security тохиргоо

- `/api/register`, `/api/login` endpoint-уудыг `permitAll` болгосон
- Бусад endpoint-ууд authentication шаарддаг
- `httpBasic`, `formLogin`-ийг унтраасан
- CORS дээр `http://localhost:3000`-г зөвшөөрсөн
- `BCryptPasswordEncoder` bean нэмсэн

### 3.5 Controller давхарга

- `AuthController`:
  - `POST /api/register` — хэрэглэгч бүртгэх
  - `POST /api/login` — `AuthenticationManager` ашиглан нэвтрэх шалгах
- Request биед `AuthRequest` DTO ашигласан

### 3.6 Application тохиргоо

`application.properties` дээр:
- `server.port=8080`
- H2 datasource
- JPA тохиргоо
- H2 console (`/h2-console`)

## 4. Туршилт ба үр дүн

### 4.1 Build шалгалт

```bash
mvn clean compile
```

**Үр дүн:** `BUILD SUCCESS`

### 4.2 Endpoint туршилт

**Бүртгэл:**

```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

Жишээ хариу:

```json
{"username":"testuser","role":"USER","id":1}
```

**Нэвтрэх (зөв нууц үг):**

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

Хариу:

```json
{"message":"Login successful"}
```

**Давхардсан хэрэглэгч:**
- HTTP `400`
- `{"error":"Username already exists"}`

**Буруу нууц үг:**
- HTTP `401`
- `{"error":"Invalid credentials"}`

## 5. Дүгнэлт

Spring Boot дээр хэрэглэгчийн бүртгэл ба нэвтрэх API-г амжилттай хэрэгжүүлж, BCrypt password hashing, endpoint хамгаалалт, CORS зэрэг аюулгүй байдлын үндсэн шаардлагуудыг хангаж гүйцэтгэлээ. Төсөл амжилттай compile хийгдэж, `/api/register` болон `/api/login` endpoint-ууд зөв ажилласан.
