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
    `address` VARCHAR(100) ,
    `chartId` INT 
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
('Ahmet Can Soral ', '123456', 'example2@mail.com', 'carrier', null);

-- Inserting sample products
INSERT INTO `Product` ( `name`, `stock`, `price`, `threshold`,`imagePath`) VALUES
( 'Apple', 100, 34.90, 20, 'images/apple.png'),
( 'Pineapple', 40, 79.90, 30, 'images/Pineapple.png'),
( 'Patato', 400, 19.90, 300, 'images/Patates.jpg'),
( 'Peach', 70, 39.90 , 50, 'images/Peach.png'),
( 'Spinach', 50, 44.90, 35, 'images/Ispanak.png'),
( 'Pepper', 200, 49.90, 150, 'images/Biber.png'),
( 'Coconut', 40, 39.90, 30, 'images/Coconut.png'),
( 'Carrot', 130, 37.90, 20, 'images/Havuc.png'),
( 'Grape', 20, 74.90, 10, 'images/Grape.png'),
( 'Waterlemon', 10, 8.90, 5, 'images/Watermelon.png'),
( 'Broccoli', 10, 49.90, 5, 'images/brokoli.png'),
( 'Zucchini', 60, 49.90, 10, 'images/Kabak.png'),
( 'Lettuce', 150, 29.90, 100, 'images/Marul.png'),
( 'Cabbage', 120, 7.99, 90, 'images/Lahana.jpg'),
( 'Cucumber', 150, 38.90, 100, 'images/Salatalık.png'),
( 'Onion', 300, 9.90, 200, 'images/Sogan.png'),
( 'Strawberry', 15, 89.90, 10, 'images/Strawberry.png'),
( 'Tomato', 200, 45.90, 100, 'images/Tomato.png'),
( 'Leek', 30, 29.90, 25, 'images/Pırasa.png'),
( 'Passion Fruit', 30, 29.90, 25, 'images/carkifelek.png'),
( 'Dragon Fruit', 30, 29.90, 25, 'images/ejder.png'),
( 'Plum', 30, 29.90, 25, 'images/erik.png'),
( 'Cherry', 30, 29.90, 25, 'images/kiraz.png'),
( 'Cauliflower', 30, 29.90, 25, 'images/karnibahar.jpg.png'),
( 'Mandarine', 30, 29.90, 25, 'images/mandalina.png');







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
