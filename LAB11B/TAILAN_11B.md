# Лабораторийн ажлын тайлан (11b / Lab12)

**Хичээл:** F.CSA311 Программ хангамжийн бүтээлт  
**Лаб №:** 11b (Lab12)  
**Сэдэв:** Spring Boot Framework ашиглан хэрэглэгчийн бүртгэл ба нэвтрэх REST API хөгжүүлэлт

---

## 1. Ажлын зорилго

Java Spring Boot дээр хэрэглэгчийн authentication-ийн суурь backend хөгжүүлэх:

- `POST /api/register` endpoint-оор хэрэглэгч бүртгэх
- `POST /api/login` endpoint-оор нэвтрэх шалгах
- Нууц үгийг BCrypt hash хэлбэрээр хадгалах

---

## 2. Ашигласан технологи

- Java 17
- Spring Boot 3
- Spring Web
- Spring Security
- Spring Data JPA
- H2 in-memory database
- Maven

---

## 3. Хийж хэрэгжүүлсэн ажил

## 3.1 Төслийн бүтэц

Төслийг стандарт Spring Boot бүтцээр байгуулсан:
- `lab12/src/main/java/sict/edu/mn/lab12/...`
- `lab12/src/main/resources/application.properties`
- `lab12/pom.xml`

## 3.2 Entity ба Repository

- `User` entity: `id`, `username`, `password`, `role`
- `username` талбарт unique нөхцөл өгсөн
- `UserRepository` дээр `findByUsername(...)` нэмсэн

## 3.3 Service давхарга

`UserService` дээр:
- `registerUser(...)`: хэрэглэгч бүртгэх
- duplicate username шалгах
- `PasswordEncoder` (BCrypt)-оор password hash хийх
- `findByUsername(...)`: login/auth урсгалд хэрэглэгч олох

## 3.4 Security тохиргоо

`SecurityConfig` дээр:
- `/api/register`, `/api/login` endpoint-уудыг `permitAll`
- бусад endpoint-ууд authentication шаарддаг
- `httpBasic`, `formLogin` disabled
- stateless session бодлого
- CORS: `http://localhost:3000` зөвшөөрсөн
- H2 console нэвтрэх боломж үлдээсэн

## 3.5 Controller ба DTO

`AuthController` дээр:
- `POST /api/register`
- `POST /api/login`

Request body-г `AuthRequest` DTO-р дамжуулж, `@NotBlank` validation ашигласан.

---

## 4. Endpoint тодорхойлолт

## 4.1 Register

**Request**
```json
{
  "username": "testuser",
  "password": "testpass"
}
```

**Success (201)**
```json
{
  "id": 1,
  "username": "testuser",
  "role": "USER"
}
```

**Fail (400)**
```json
{
  "error": "Username already exists"
}
```

## 4.2 Login

**Request**
```json
{
  "username": "testuser",
  "password": "testpass"
}
```

**Success (200)**
```json
{
  "message": "Login successful"
}
```

**Fail (401)**
```json
{
  "error": "Invalid credentials"
}
```

---

## 5. Туршилт ба үр дүн

## 5.1 Compile шалгалт

```bash
cd "lab12"
mvn clean compile
```

**Үр дүн:** `BUILD SUCCESS`

## 5.2 Endpoint шалгалт

Register:
```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

Login (success):
```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

Duplicate user:
- HTTP 400
- `{"error":"Username already exists"}`

Wrong password:
- HTTP 401
- `{"error":"Invalid credentials"}`

---

## 6. Дүгнэлт

Lab 11b-ийн хүрээнд Spring Boot ашиглан authentication-ийн суурь backend-ийг амжилттай хэрэгжүүлсэн. Бүртгэл, нэвтрэх endpoint-ууд ажиллаж, password hashing, security rule, CORS, persistence зэрэг шаардлагуудыг хангасан. Энэ нь цааш JWT/token болон role-based authorization нэмэх суурь болж байна.
