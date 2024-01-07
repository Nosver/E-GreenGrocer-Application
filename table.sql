-- Create a new database if not exists
CREATE DATABASE IF NOT EXISTS `OOP3`;
USE `OOP3`;

-- USERS TABLE
-- Create a users table
CREATE TABLE IF NOT EXISTS `users` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `role`  VARCHAR(10) NOT NULL, 
    `address` VARCHAR(100) 
);


CREATE TABLE IF NOT EXISTS `Product` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255) NOT NULL,
    `stock` DOUBLE NOT NULL,
    `price` DOUBLE NOT NULL,
    `threshold` DOUBLE NOT NULL,
    `imagePath` VARCHAR(255) NOT NULL
    
);

CREATE TABLE IF NOT EXISTS `Chart` (
    `chartId` INT PRIMARY KEY AUTO_INCREMENT,
    `userId` INT,
    `totalPrice` DOUBLE,
    `state` VARCHAR(25),
    `date` DATETIME,
    FOREIGN KEY (userId) REFERENCES users(id) 
);

CREATE TABLE IF NOT EXISTS `ChartItem` (
    `chartItemId` INT PRIMARY KEY AUTO_INCREMENT,
    `chartId` INT,
    `productId` INT,
    `quantity` DOUBLE,
    FOREIGN KEY (chartId) REFERENCES Chart(chartId),
    FOREIGN KEY (productId) REFERENCES Product(id)
);


-- Inserting sample users
INSERT INTO `users` (`name`, `password`, `email`, `role`, `address`) VALUES
('John Doe', '123456', 'example@mail.com', 'owner', null),
('Jane Doe', '123456', 'example1@mail.com', 'customer', 'abc mahallesi def sokak'),
('Ahmet ', '123456', 'example2@mail.com', 'carrier', null);

-- Inserting sample products
INSERT INTO `Product` ( `name`, `stock`, `price`, `threshold`,`imagePath`) VALUES
( 'Product A', 50.0, 10.99, 5,'images/product_a.jpg'),
( 'Product B', 30.0, 5.99, 3,'images/product_b.jpg');

-- Inserting a sample chart
INSERT INTO `Chart` (`userId`, `totalPrice`, `state`,`date`) VALUES
(2, 25.0, 'active' ,'2024-01-05 12:30:00'),
(2, 40.0, 'purchased' ,'2024-01-05 12:40:00');


-- Getting the auto-generated chartId for the newly inserted chart
SET @chartId = LAST_INSERT_ID();

-- Inserting items for the chart
INSERT INTO `ChartItem` (`chartId`, `productId`, `quantity`) VALUES
(@chartId, 1, 2.0),  -- Adding 2 units of Product A
(@chartId, 2, 3.0);  -- Adding 3 units of Product B
