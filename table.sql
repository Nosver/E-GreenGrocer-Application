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
    `role` VARCHAR(50) NOT NULL
);

-- Create a products table
CREATE TABLE IF NOT EXISTS `products` (
    `product_id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_name` VARCHAR(255) NOT NULL UNIQUE,
    `stock` DOUBLE NOT NULL,
    `price` DOUBLE NOT NULL
);

-- Create a chart table
CREATE TABLE IF NOT EXISTS `chart` (
    `user_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity` DOUBLE NOT NULL,
    PRIMARY KEY (`user_id`, `product_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`)
);

-- ADD PRODUCT ?!??!?!

-- Insert a sample user
INSERT INTO `users` (`name`, `password`, `email`, `role`) VALUES
('John Doe', '123456', 'example@mail.com', 'admin'),
('Jane Doe', '123456', 'example1@mail.com', 'customer'),
('John Smith', '123456', 'example2@mail.com', 'carrier');

-- Insert sample products 
INSERT INTO `products` (`product_name`, `stock`, `price`) VALUES
('Apple', 10.4, 100.35),
('Banana', 20.5, 50.25),
('Orange', 30.6, 25.15),
('Mango', 40.7, 1),
('Pineapple', 50.8, 5.05),
('Grapes', 60.9, 1.05);

-- Associate products with the user
INSERT INTO `chart` (`user_id`, `product_id`, `quantity`) VALUES
(2, 1, 1.4),
(2, 2, 2.5),
(2, 3, 3.6),
(2, 4, 4.7),
(2, 5, 5.8),
(2, 6, 6.0);

-- add chart table some exmples NOT WORKING
INSERT INTO `chart` (`user_id`, `product_id`, `quantity`) VALUES
(2, 1, 1.4),
(2, 2, 2.5),
(2, 3, 3.6),
(2, 4, 4.7),
(2, 5, 5.8),
(2, 6, 6.0);