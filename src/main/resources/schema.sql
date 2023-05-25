DROP TABLE if EXISTS customers;
CREATE TABLE customers (
   customer_id INT PRIMARY KEY AUTO_INCREMENT,
   `name` VARCHAR(100),
   email VARCHAR(100)
);

DROP TABLE if EXISTS transactions;
CREATE TABLE transactions (
  transaction_id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id VARCHAR(36),
  created_date DATE,
  amount DECIMAL(10, 2),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
