# retailRewardProgram
This is a REST API for a Retail Reward Program, which allows customers to earn reward points based on their transaction history. The API provides endpoints to manage customers, transactions, and retrieve reward information.

## Technologies Used
Java \
Spring Boot \
Spring MVC \
Spring Data JPA \
H2 Database \
Maven

## Database Initialization
The application automatically initializes the database with the following schema and sample data:

### Customers Table
````
CREATE TABLE customers (
   customer_id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(100),
   email VARCHAR(100)
);
````

### Transactions Table
````
CREATE TABLE transactions (
  transaction_id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  created_date DATE,
  amount DECIMAL(10, 2),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
````

## API Endpoints
The Retail Reward Program REST API exposes the following endpoints:

* GET /customers/{id}: Retrieves customer details by ID.\
* GET /customers: Retrieves a list of all customers.\
* POST /customers: Creates a new customer.\
* PUT /customers/{id}: Updates a customer by ID.\
* DELETE /customers/{id}: Deletes a customer by ID.\
* GET /transactions/{id}: Retrieves transactions for a customer by ID.\
* POST /transactions: Creates a new transaction.\

## Error Handling
The API handles different types of exceptions and returns appropriate error responses. Some common error scenarios include:
* Resource not found\
* Internal server error\

The GlobalExceptionHandler class provides centralized exception handling and logs the errors.