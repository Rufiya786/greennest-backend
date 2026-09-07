# 🌿 GreenNest – Online Plant E-Commerce Backend

GreenNest is a **Spring Boot REST API backend** developed for an online plant e-commerce platform.

The backend provides APIs for managing plants, categories, users, cart items, and orders. It uses **Spring Boot, Spring Data JPA, Hibernate, and MySQL** to provide database-driven functionality for the GreenNest application.


## 📌 Project Overview

GreenNest is an online plant e-commerce platform that allows customers to browse plants, manage their shopping cart, place orders, and manage their wishlist.

The backend handles the application's business logic, database operations, and REST API communication with the frontend.

The project was developed to gain practical experience in **Java backend development and Spring Boot REST API development**.


## ✨ Features

### 🌱 Plant Management

- Add new plants
- View all plants
- View plant by ID
- Update plant details
- Delete plants
- Search and retrieve plant information
- Manage plant availability

### 🏷️ Category Management

- Create plant categories
- View categories
- Retrieve plants based on categories
- Manage category information
- Prevent duplicate categories

### 🛒 Cart Management

- Add plants to cart
- View cart items
- Update cart quantity
- Remove items from cart
- Calculate cart-related information

### 📦 Order Management

- Place orders
- Store order information
- Retrieve orders
- View order details
- Manage order status
- Admin order management

### 👤 User Management

- User registration
- User information management
- User-related operations
- Customer order management

### 🔐 REST APIs

The backend exposes RESTful APIs that allow the frontend to communicate with the application.


## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Backend development |
| Spring Boot | Application framework |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| REST API | Client-server communication |
| MySQL | Database |
| Maven | Dependency management |
| Postman | API testing |
| Git | Version control |
| GitHub | Source code management |

## 🏗️ Architecture

```text
Client / Frontend
       ↓
    REST API
       ↓
   Controller
       ↓
    Service
       ↓
   Repository
       ↓
 Spring Data JPA
       ↓
    Hibernate
       ↓
      MySQL
