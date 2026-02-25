# Employee Management System

Spring Boot project with:

* Layered Architecture
* DTO Pattern
* JPA Relationships
* Validation
* REST APIs

## Workflow

Client - Sends request
|
Controller - Handles Request
|
Service - Logic
|
Repository - Communicates with database
|
Database - Stores data

## Layer ---- Responsibility

* Entity - Table Definition
* Repository - Communicates with DB
* Service - Contains Logic
* Controller - Handles Request
* DTO - Data format control (Request format/Response format)
* ResponseEntity - Status Control

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL



## 📸 API Testing

| Create Employee | Get Employee |
|----------------|--------------|
| ![](images/POST.png) | ![](images/GET.png) |

