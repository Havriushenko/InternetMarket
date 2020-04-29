/********************************************/
/*Internet Market DB creation script for PostgreSQL*/
/******************************************/
DROP database if exists internet_market;
CREATE DATABASE if not exists internet_market CHARACTER SET UTF8 COLLATE utf8_unicode_ci;

use internet_market;

/*****************************/
/*Creates the table of Product */
/*****************************/
CREATE TABLE if not exists `product` (
`id` INT NOT NULL auto_increment,
`name` VARCHAR(200) NOT NULL,
`price` DOUBLE NOT NULL,
`description` VARCHAR(500) NOT NULL,
`product_group_id` INT,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Product Group */
/*****************************/
CREATE TABLE if not exists `product_group` (
`id` INT NOT NULL auto_increment,
`group_name` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Users*/
/*****************************/
CREATE TABLE if not exists `users` (
`id` INT NOT NULL auto_increment,
`name` VARCHAR(100) NOT NULL,
`surname` VARCHAR(100) NOT NULL,
`email` VARCHAR(100) NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Orders */
/*****************************/
CREATE TABLE if not exists `orders` (
`id` INT NOT NULL auto_increment,
`status` VARCHAR (10) NOT NULL,
`data` VARCHAR (30),
`user_id` INT NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Orders info and relation between Products and Orders*/
/*****************************/
CREATE TABLE if not exists `order_info` (
`id` INT NOT NULL auto_increment,
`order_id` INT NOT NULL,
`product_id` INT NOT NULL,
`quantity` INT NOT NULL,
FOREIGN KEY(order_id)REFERENCES orders(id) ON DELETE CASCADE,
FOREIGN KEY(product_id)REFERENCES product(id) ON DELETE CASCADE,
PRIMARY KEY (`id`));

insert into product(name,price,description,product_group_id) values("Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)",70,"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.",1);
insert into product_group(group_name) values("Masks");
