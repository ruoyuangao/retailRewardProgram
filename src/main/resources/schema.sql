/*
SQL Script to create database tables for customers and transactions
*/
-- Drop table if it exists
DROP TABLE if EXISTS customers;
-- Create customers table
CREATE TABLE customers (
   customer_id INT PRIMARY KEY AUTO_INCREMENT,
   `name` VARCHAR(100),
   email VARCHAR(100)
);

-- Drop table if it exists
DROP TABLE if EXISTS transactions;
-- Create transactions table
CREATE TABLE transactions (
  transaction_id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  created_date DATE,
  amount DECIMAL(10, 2),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
