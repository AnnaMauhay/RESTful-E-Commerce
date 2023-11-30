CREATE DATABASE EcommerceDB;
USE EcommerceDB;

CREATE TABLE Product
(
    product_id INTEGER UNIQUE NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)   NOT NULL UNIQUE,
    price      FLOAT          NOT NULL DEFAULT 0.0,
    category   VARCHAR(255)   NOT NULL,
    stock_qty  INTEGER                 DEFAULT 0,
    PRIMARY KEY (product_id)
);

INSERT INTO Product
VALUES (0, 'Tuna', 2.5, 'FISH', 30);
INSERT INTO Product
VALUES (0, 'Pork', 4.5, 'MEAT', 20);
INSERT INTO Product
VALUES (0, 'Egg (by the dozen)', 1.99, 'POULTRY', 10);
INSERT INTO Product
VALUES (0, 'Orange', 1.5, 'FRUIT', 30);
INSERT INTO Product
VALUES (0, 'Apple', 1.2, 'FRUIT', 30);


DELETE
FROM Product
WHERE product_id = 5;