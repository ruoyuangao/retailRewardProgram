DELETE FROM customers;
INSERT INTO customers (`name`, email) VALUES
    ('Kelly', 'kelly@example.com'),
    ('Steven', 'steven@example.com'),
    ('Jack', 'jack@example.com');

DELETE FROM transactions;
INSERT INTO transactions (customer_id, created_date, amount) VALUES
     ((SELECT customer_id FROM customers WHERE `name` = 'Kelly'), '2022-04-12', 0.15),
     ((SELECT customer_id FROM customers WHERE `name` = 'Kelly'), '2023-02-05', 75.25),
     ((SELECT customer_id FROM customers WHERE `name` = 'Kelly'), '2023-03-12', 150.50),
     ((SELECT customer_id FROM customers WHERE `name` = 'Kelly'), '2023-04-12', 80.15),
     ((SELECT customer_id FROM customers WHERE `name` = 'Kelly'), '2023-05-12', 50.15),
     ((SELECT customer_id FROM customers WHERE `name` = 'Steven'), '2023-02-20', 120.80),
     ((SELECT customer_id FROM customers WHERE `name` = 'Jack'), '2023-04-15', 200.00),
     ((SELECT customer_id FROM customers WHERE `name` = 'Jack'), '2023-04-22', 50.75);