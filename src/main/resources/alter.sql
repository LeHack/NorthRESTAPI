-- CREATE SEQUENCES FOR TABLES

CREATE SEQUENCE categories_id_seq;
ALTER TABLE categories ALTER COLUMN categoryid SET DEFAULT nextval('categories_id_seq');
ALTER SEQUENCE categories_id_seq OWNED BY categories.categoryid;
-- SELECT MAX(categoryid) from categories;
SELECT setval('categories_id_seq', 8);

CREATE SEQUENCE employees_id_seq;
ALTER TABLE employees ALTER COLUMN employeeid SET DEFAULT nextval('employees_id_seq');
ALTER SEQUENCE employees_id_seq OWNED BY employees.employeeid;
-- SELECT MAX(employeeid) from employees;
SELECT setval('employees_id_seq', 9);

CREATE SEQUENCE orders_id_seq;
ALTER TABLE orders ALTER COLUMN orderid SET DEFAULT nextval('orders_id_seq');
ALTER SEQUENCE orders_id_seq OWNED BY orders.orderid;
-- SELECT MAX(orderid) from orders;
SELECT setval('orders_id_seq', 11077);

CREATE SEQUENCE products_id_seq;
ALTER TABLE products ALTER COLUMN productid SET DEFAULT nextval('products_id_seq');
ALTER SEQUENCE products_id_seq OWNED BY products.productid;
-- SELECT MAX(productid) from products;
SELECT setval('products_id_seq', 77);

CREATE SEQUENCE shippers_id_seq;
ALTER TABLE shippers ALTER COLUMN shipperid SET DEFAULT nextval('shippers_id_seq');
ALTER SEQUENCE shippers_id_seq OWNED BY shippers.shipperid;
-- SELECT MAX(shipperid) from shippers;
SELECT setval('shippers_id_seq', 6);

CREATE SEQUENCE suppliers_id_seq;
ALTER TABLE suppliers ALTER COLUMN supplierid SET DEFAULT nextval('suppliers_id_seq');
ALTER SEQUENCE suppliers_id_seq OWNED BY suppliers.supplierid;
-- SELECT MAX(supplierid) from suppliers;
SELECT setval('suppliers_id_seq', 29);