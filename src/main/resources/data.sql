INSERT INTO clients (name, last_name, doc_number) VALUES
('Juan', 'Pérez', '12345678'),
('Ana', 'Gómez', '10987654'),
('Carlos', 'Rodríguez', '10293841'),
('Laura', 'Martínez', '11223341');

INSERT INTO products (description, code, stock, price) VALUES
('Laptop Lenovo', 'LN123', 10, 799.99),
('Smartphone Samsung', 'SSG456', 25, 499.50),
('Tablet Apple', 'APL789', 15, 329.99),
('Monitor Dell', 'DLL101', 5, 150.00);

INSERT INTO sales (client_id, product_id, quantity, sale_date) VALUES
(1, 2, 1, '2024-08-10'),
(2, 1, 2, '2024-08-11'),
(3, 3, 1, '2024-08-12'),
(4, 4, 1, '2024-08-13');
