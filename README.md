# ktor-jwt-auth

# 🔐 Ktor JWT Authentication Backend

This is a Kotlin Ktor-based backend that handles JWT authentication using access and refresh tokens. It supports:

- ✅ User registration
- ✅ User login
- ✅ Secure JWT access token generation
- ✅ Refresh token handling
- ✅ Token validation and protected routes

## Android Frontend
**ktor-jwt-auth** has a android mobile frontend that consumes all endpoints and auto-refresh expired token. It's available [here](https://github.com/jante-adebowale/AndroidJwtAuthClient)

## 🚀 Tech Stack

- [Ktor](https://ktor.io/)
- JWT (JSON Web Tokens)
- Exposed
- Request Validation
- H2 or Postgres
- Koin

## 📦 Endpoints

| Method | Endpoint          | Description                |
|--------|-------------------|----------------------------|
| POST   | `/register`       | Register a new user        |
| POST   | `/login`          | Login and get tokens       |
| POST   | `/refresh`        | Get new access token       |
| GET    | `/protected`      | Example protected route    |

## 🔐 JWT Structure

- **Access Token**: Short-lived 
- **Refresh Token**: Long-lived 
- Stored in secure storage on client side

**Connect with me on:**
* [Portfolio](https://www.janteadebowale.com)
* [Youtube](https://www.youtube.com/@jante-adebowale)
* [LinkedIn](https://www.linkedin.com/in/jante-adebowale)
* [Github](https://github.com/jante-adebowale)

<p >
  <a href="https://www.youtube.com/@jante-adebowale"><img alt="Youtube" src="https://github.com/jante-adebowale/data-capture-service/blob/master/src/main/resources/static/youtube.svg?raw=true"/></a>
  <a href="https://www.linkedin.com/in/jante-adebowale"><img alt="LinkedIn" src="https://github.com/jante-adebowale/data-capture-service/blob/master/src/main/resources/static/linkedin.svg?raw=true"/></a> 
  <a href="https://www.janteadebowale.com"><img alt="Portfolio" src="https://github.com/jante-adebowale/data-capture-service/blob/master/src/main/resources/static/portfolio.svg?raw=true"/></a>
</p>
