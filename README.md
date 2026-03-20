# 🏋️‍♂️ Gym Tracker API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.10-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-blueviolet?style=for-the-badge&logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-black?style=for-the-badge&logo=jsonwebtokens)

**Gym Tracker** is a robust REST API designed to manage workouts and body metrics. It implements a **Stateless Architecture** using **JWT** (JSON Web Tokens) to ensure system security and scalability.

---

## 🚀 Core Features

* 🛡️ **Stateless Security:** Authentication based on JWT Tokens.
* 👥 **Role Management:** Clear distinction between standard Users and Administrators.
* 📊 **Body Tracking:** Detailed logging of over 15 physical metrics (BMI, Body Fat, Muscle Mass, etc.).
* 🏋️ **Exercise Catalog:** Management of global exercises curated by administrators.
* ⚡ **Clean Architecture:** Use of DTOs, Mappers (MapStruct), and decoupled services.

---

## 🛠️ Technology Stack

* **Backend:** Java 17, Spring Boot 3.5.10.
* **Security:** Spring Security, JWT (jjwt).
* **Persistence:** Spring Data JPA, Hibernate.
* **Productivity:** Lombok, MapStruct.
* **Dependency Management:** Maven.

---

## 📋 Prerequisites

Before running the project, ensure you have the following installed:

1.  **JDK 17** or higher.
2.  **Maven** 3.8+.
3.  **Database:** (Configured in your `application.properties`). If using MySQL, ensure the service is running.

---

## ⚙️ Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Adripoblado/gymtracker.git](https://github.com/Adripoblado/gymtracker.git)
    ```
2.  **Configure Environment Variables:**
    Create a `src/main/resources/application.properties` file (not included in the repo) with your credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/gym_tracker_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    # Secret Key for JWT (minimum 64 characters recommended)
    jwt.secret.key=your_super_secret_64_character_key
    ```
3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

---

## 🛣️ API Documentation

### 🔐 Authentication (`/auth`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/auth/register` | User registration. Requires `rolecode` for ADMIN privileges. |
| `POST` | `/auth/login` | Login with username/email. Returns the **Bearer Token**. |

### 👤 Users (`/users`)
| Method | Endpoint | Privileges | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/me` | User | Retrieves the authenticated user's profile. |
| `PUT` | `/me/update` | User | Updates `bodyMetrics` (weight, fat %, etc.). |
| `DELETE` | `/me/delete` | User | Deletes own account (Requires `?confirm=DELETE+ACCOUNT`). |
| `DELETE` | `/admin/delete/{username}` | **ADMIN** | Deletes any user by their username. |

### 🏃 Exercises (`/exercises`)
| Method | Endpoint | Privileges | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/create/global` | **ADMIN** | Creates an official system exercise. |
| `GET` | `/list/global` | User | Lists all available official exercises. |

---

## 🧪 Testing with Postman

1.  **Login:** Execute the login endpoint and copy the returned token.
2.  **Environment Variables:** In Postman, create a variable named `jwt_token`.
3.  **Test Scripts:** In the **Tests** tab of the login request, paste:
    ```javascript
    pm.environment.set("jwt_token", pm.response.text());
    ```
4.  **Auth:** In all other endpoints, select **Auth -> Bearer Token** and use `{{jwt_token}}`.

---

## ✒️ Author

* **Adrián Cervera** - *Lead Developer* - [Adripoblado](https://github.com/Adripoblado)

---
